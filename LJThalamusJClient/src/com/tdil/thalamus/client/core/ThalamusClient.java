package com.tdil.thalamus.client.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.thalamus.client.core.method.PostMethodCreator;
import com.tdil.thalamus.client.core.method.PutMethodCreator;
import com.tdil.thalamus.client.core.method.ThalamusMethodCreator;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;

public class ThalamusClient {

	private static int TIMEOUT = 2000;
	private static String THALAMUS_SERVER = "http://ec2-23-23-84-70.compute-1.amazonaws.com:80/thalamus";

	private static String THALAMUS_HOST = "ec2-23-23-84-70.compute-1.amazonaws.com";
	private static String THALAMUS_JSESSIONID_COOKIE_PATH = "/thalamus";

	private static String THALAMUS_TOUCHPOINT_CODE = "test";
	private static String THALAMUS_TOUCHPOINT_TOKEN = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
	
	private static ProxyConfiguration PROXY;

	private static final Logger LOG = LoggerProvider.getLogger(ThalamusClient.class);

	public static JSON login(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		HttpClient client = new HttpClient();
		configureTimeout(client);
		HttpState state = new HttpState();
		String authStr = username + ":" + password;
        String authEncoded = Base64.encodeBase64String(authStr.getBytes());
		PostMethod httpMethod = new PostMethod(THALAMUS_SERVER + ThalamusServices.LOGIN);
		httpMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpMethod.setRequestHeader("Authorization", "Basic " + authEncoded);
		addTouchpoint(httpMethod, (NameValuePair[])null);
		String data = "j_username="+username+"&j_password=" + password;
		httpMethod.setRequestHeader("Content-Length", String.valueOf(data.length()));
		RequestEntity requestEntity = new StringRequestEntity(data);
		httpMethod.setRequestEntity(requestEntity);
		try {
			client.executeMethod(null, httpMethod, state);
			int statusCode = httpMethod.getStatusCode();
			String response = getResponseString(httpMethod);
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, null);
	            response = getResponseString(redirect);
	            statusCode = redirect.getStatusCode();
			}
			if (!isValidStatusCode(statusCode)) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			// TODO volar este metodo addCookieTo(client, tokenHolder);
			return extractJSONObjectResponse(response);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}

	public static String getResponseString(HttpMethod httpMethod) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(httpMethod.getResponseBodyAsStream());
			return IOUtils.toString(inputStream, "UTF-8");
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	public static ThalamusResponse executeGet(String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(null, service, true, (NameValuePair[])null);
	}

	public static ThalamusResponse executeGet(TokenHolder tokenHolder, String service, boolean useTouchpoint, NameValuePair...nvp) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute GET to: " + service);
		}
		GetMethod httpMethod = new GetMethod(THALAMUS_SERVER + service);
		return executeBase(tokenHolder, service, useTouchpoint, httpMethod, nvp);
	}

	public static ThalamusResponse executeBase(TokenHolder tokenHolder,
			String service, boolean useTouchpoint,
			HttpMethodBase httpMethod, NameValuePair...nvp) throws HttpStatusException,
			InvalidResponseException, UnauthorizedException,
			CommunicationException {
		HttpClient client = new HttpClient();
		configureTimeout(client);
		if (tokenHolder != null && tokenHolder.hasToken()) {
			addAuthentication(client, httpMethod, tokenHolder, nvp);
		} else {
			if (useTouchpoint) {
				addTouchpoint(httpMethod, nvp);
			} else {
				httpMethod.setQueryString(nvp);
			}
		}
		long start = System.currentTimeMillis();
		try {
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = getResponseString(httpMethod);
			if (LOG.isDebugEnabled()) {
				LOG.debug("Execute result for " + service + "  is: " + response + " status " + statusCode);
			}
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, tokenHolder);
	            response = getResponseString(redirect);
	            statusCode = redirect.getStatusCode();
			}
			if (!isValidStatusCode(statusCode)) {
				LOG.error("Remote result for " + (service) + " status: " + statusCode + " and body: " + (response == null ? "null" : response));
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode) + " - URL: " + THALAMUS_SERVER + service);
			}
			addCookieTo(client, tokenHolder);
			JSON result = extractJSONObjectResponse(response);
			return new ThalamusResponse(result, statusCode);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			if (LOG.isInfoEnabled()) {
				long end = System.currentTimeMillis();
				LOG.info("Execute: " + service + " took " + (end - start) + " millis");
			}
		}
	}

	private static void configureTimeout(HttpClient client) {
		HttpConnectionManager connectionManager = client.getHttpConnectionManager();
		connectionManager.getParams().setSoTimeout(getTIMEOUT());
		if (getPROXY() != null) {
			client.getHostConfiguration().setProxy(getPROXY().getServer(), getPROXY().getPort());
		}
	}

	public static ThalamusResponse executeDelete(TokenHolder tokenHolder, String service, boolean useTouchpoint, NameValuePair nvp) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		DeleteMethod httpMethod = new DeleteMethod(THALAMUS_SERVER + service);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute DELETE to: " + service);
		}
		return executeBase(tokenHolder, service, useTouchpoint, httpMethod);
	}

	private static void addCookieTo(HttpClient client, TokenHolder tokenHolder) {
		if (tokenHolder != null && !tokenHolder.hasToken()) {
			for (Cookie cookie : client.getState().getCookies()) {
				tokenHolder.addCookie(cookie);
			}
		}
	}

	private static boolean isValidStatusCode(int statusCode) {
		if (statusCode == HttpStatus.SC_OK) {
			return true;
		}
		if (statusCode == HttpStatus.SC_CREATED) {
			return true;
		}
		if (statusCode == HttpStatus.SC_BAD_REQUEST) {
			return true;
		}
		return false;
	}

	private static boolean isUnauthorizedAccessResponse(Object rawResponseMessage) {
		if (rawResponseMessage instanceof JSONObject) {
			JSONObject response = (JSONObject)rawResponseMessage;
			JSONObject errors = response.getJSONObject("errors");
			if (errors != null && !errors.isNullObject()) {
				Object unauthorized = errors.get("unathorized");
				return unauthorized != null;
			}
		}
		return false;
	}

	public static ThalamusResponse executePost(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return execute(json, null, service, PostMethodCreator.INSTANCE);
	}

	public static ThalamusResponse executePut(TokenHolder tokenHolder, JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return execute(json, tokenHolder, service, PutMethodCreator.INSTANCE);
	}

	public static ThalamusResponse executePut(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return execute(json, null, service, PutMethodCreator.INSTANCE);
	}

	public static ThalamusResponse execute(JSON json, TokenHolder tokenHolder, String service, ThalamusMethodCreator methodCreator) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute " + methodCreator.getDescription() + " to: " + service + " json " + (json == null ? "null" : json));
		}
		HttpClient client = new HttpClient();
		configureTimeout(client);
		EntityEnclosingMethod httpMethod = methodCreator.createMethod(THALAMUS_SERVER + service);
		if (tokenHolder != null && tokenHolder.hasToken()) {
			addAuthentication(client, httpMethod, tokenHolder, (NameValuePair[])null);
		} else {
			addTouchpoint(httpMethod, (NameValuePair[])null);
		}
		long start = System.currentTimeMillis();
		try {
			httpMethod.setRequestHeader("Content-type", "application/json");
			if (json != null) {
				RequestEntity requestEntity = new StringRequestEntity(json.toString(), "application/json", "UTF-8");
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = getResponseString(httpMethod);
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, tokenHolder);
	            response = getResponseString(redirect);
	            statusCode = redirect.getStatusCode();
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("Execute result is: " + response + " status " + statusCode);
			}
			if (!isValidStatusCode(statusCode)) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			addCookieTo(client, tokenHolder);
			JSON result = extractJSONObjectResponse(response);
			if (LOG.isDebugEnabled()) {
				LOG.debug("POST result is: " + result);
			}
			return new ThalamusResponse(result, statusCode);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			if (LOG.isInfoEnabled()) {
				long end = System.currentTimeMillis();
				LOG.info("Execute: " + service + " took " + (end - start) + " millis");
			}
		}
	}

	public static JSON extractJSONObjectResponse(String response)
			throws InvalidResponseException, UnauthorizedException {
		JSONTokener tokener = new JSONTokener(response);
		Object rawResponseMessage = tokener.nextValue();
		if (rawResponseMessage == null) {
			throw new InvalidResponseException();
		}
		if (isUnauthorizedAccessResponse(rawResponseMessage)) {
			throw new UnauthorizedException((JSON)rawResponseMessage);
		}
		return (JSON)rawResponseMessage;
	}

	private static void addAuthentication(HttpClient client, HttpMethod method, TokenHolder tokenHolder, NameValuePair...nvp) {
//		String authStr = username + ":" + password;
//		String authEncoded = Base64.encodeBase64String(authStr.getBytes());
//		method.setRequestHeader("Authorization", "Basic " + authEncoded);
		for (Cookie cookie : tokenHolder.getCookies()) {
			client.getState().addCookie(cookie);
		}
		addTouchpoint(method, nvp);
//		method.setRequestHeader("thalamus-header-touchpoint", getTHALAMUS_TOUCHPOINT());
	}

	private static void addTouchpoint(HttpMethod method, NameValuePair...nvp) {
		if (nvp == null || nvp.length == 0) {
			method.setQueryString(new NameValuePair[] {new NameValuePair("touchpoint", getTHALAMUS_TOUCHPOINT_CODE()), new NameValuePair("token", getTHALAMUS_TOUCHPOINT_TOKEN())});
		} else {
			NameValuePair allParams[] = new NameValuePair[nvp.length + 2];
			System.arraycopy(nvp, 0, allParams, 0, nvp.length);
			allParams[nvp.length] = new NameValuePair("touchpoint", getTHALAMUS_TOUCHPOINT_CODE());
			allParams[nvp.length + 1] = new NameValuePair("token", getTHALAMUS_TOUCHPOINT_TOKEN());
			method.setQueryString(allParams);
		}
	}

	private static HttpMethod handleRedirect(HttpMethod method, HttpClient client, TokenHolder tokenHolder) throws HttpException, IOException {
		Header redirectHeader = method.getResponseHeader("location");
		String redirectLocation = redirectHeader.getValue();
		GetMethod redirect = new GetMethod(redirectLocation);
		if (tokenHolder != null && tokenHolder.hasToken()) {
			addAuthentication(client, redirect, tokenHolder, (NameValuePair[])null);
		} else {
			addTouchpoint(redirect, (NameValuePair[])null);
		}
        client.executeMethod(redirect);
        return redirect;
	}

	private static boolean isRedirection(int statusCode) {
		return HttpStatus.SC_MOVED_PERMANENTLY == statusCode ||
		HttpStatus.SC_MOVED_TEMPORARILY == statusCode;
	}

	public static String getTHALAMUS_SERVER() {
		return THALAMUS_SERVER;
	}

	public static void setTHALAMUS_SERVER(String server) {
		ThalamusClient.THALAMUS_SERVER = server;
	}

	public static String getTHALAMUS_TOUCHPOINT_CODE() {
		return THALAMUS_TOUCHPOINT_CODE;
	}

	public static void setTHALAMUS_TOUCHPOINT_CODE(String tOUCHPOINTCODE) {
		THALAMUS_TOUCHPOINT_CODE = tOUCHPOINTCODE;
	}

	public static String getTHALAMUS_TOUCHPOINT_TOKEN() {
		return THALAMUS_TOUCHPOINT_TOKEN;
	}

	public static void setTHALAMUS_TOUCHPOINT_TOKEN(
			String tHALAMUS_TOUCHPOINT_TOKEN) {
		THALAMUS_TOUCHPOINT_TOKEN = tHALAMUS_TOUCHPOINT_TOKEN;
	}

	public static String getTHALAMUS_HOST() {
		return THALAMUS_HOST;
	}

	public static void setTHALAMUS_HOST(String tHALAMUS_HOST) {
		THALAMUS_HOST = tHALAMUS_HOST;
	}

	public static String getTHALAMUS_JSESSIONID_COOKIE_PATH() {
		return THALAMUS_JSESSIONID_COOKIE_PATH;
	}

	public static void setTHALAMUS_JSESSIONID_COOKIE_PATH(
			String tHALAMUS_JSESSIONID_COOKIE_PATH) {
		THALAMUS_JSESSIONID_COOKIE_PATH = tHALAMUS_JSESSIONID_COOKIE_PATH;
	}

	public static int getTIMEOUT() {
		return TIMEOUT;
	}

	public static void setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
	}

	public static ProxyConfiguration getPROXY() {
		return PROXY;
	}

	public static void setPROXY(ProxyConfiguration pROXY) {
		PROXY = pROXY;
	}

}

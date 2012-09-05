package com.tdil.thalamus.client.core;

import java.io.IOException;
import java.net.URLEncoder;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class ThalamusClient {
	
	private static String server = "http://ec2-23-23-84-70.compute-1.amazonaws.com:9080";

	public static JSON login(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		HttpClient client = new HttpClient();
		HttpState state = new HttpState();
		String authStr = username + ":" + password;
        String authEncoded = Base64.encodeBase64String(authStr.getBytes());
		PostMethod httpMethod = new PostMethod(server + ThalamusServices.LOGIN);
		httpMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpMethod.setRequestHeader("Authorization", "Basic " + authEncoded);
		String data = "j_username="+URLEncoder.encode(username)+"&j_password=" + URLEncoder.encode(password);
		httpMethod.setRequestHeader("Content-Length", String.valueOf(data.length()));
		RequestEntity requestEntity = new StringRequestEntity(data);
		httpMethod.setRequestEntity(requestEntity);
		try {
			client.executeMethod(null, httpMethod, state);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, username, password);
	            response = redirect.getResponseBodyAsString();
	            statusCode = redirect.getStatusCode();
			}
			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			JSONTokener tokener = new JSONTokener(response);
			Object rawResponseMessage = tokener.nextValue();
			if (rawResponseMessage == null) {
				throw new InvalidResponseException();
			}
			if (isUnauthorizedAccessResponse(rawResponseMessage)) {
				throw new UnauthorizedException((JSON)rawResponseMessage);
			}
			return (JSON)rawResponseMessage;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}
	
	public static JSON executeGet(String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(null, null, service);
	}
	
	
	public static JSON executeGet(String username, String password, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod(server + service);
		if (username != null) {
			addAuthentication(httpMethod, username, password);
		}
		try {
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, username, password);
	            response = redirect.getResponseBodyAsString();
	            statusCode = redirect.getStatusCode();
			}
			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			JSONTokener tokener = new JSONTokener(response);
			Object rawResponseMessage = tokener.nextValue();
			if (rawResponseMessage == null) {
				throw new InvalidResponseException();
			}
			if (isUnauthorizedAccessResponse(rawResponseMessage)) {
				throw new UnauthorizedException((JSON)rawResponseMessage);
			}
			return (JSON)rawResponseMessage;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}
	
	private static boolean isUnauthorizedAccessResponse(Object rawResponseMessage) {
		if (rawResponseMessage instanceof JSONObject) {
			JSONObject response = (JSONObject)rawResponseMessage;
			JSONObject errors = response.getJSONObject("errors");
			if (errors != null) {
				Object unauthorized = errors.get("unathorized");
				return unauthorized != null;
			}
		}
		return false;
	}
	
	public static JSON executePost(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executePost(json, null, null, service);
	}

	public static JSON executePost(JSON json, String username, String password, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		HttpClient client = new HttpClient();
		PostMethod httpMethod = new PostMethod(server + service);
		if (username != null) {
			addAuthentication(httpMethod, username, password);
		}
		try {
			httpMethod.setRequestHeader("Content-type", "application/json");
			RequestEntity requestEntity = new StringRequestEntity(json.toString());
			httpMethod.setRequestEntity(requestEntity);
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, username, password);
	            response = redirect.getResponseBodyAsString();
	            statusCode = redirect.getStatusCode();
			}
			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			JSONTokener tokener = new JSONTokener(response);
			Object rawResponseMessage = tokener.nextValue();
			if (rawResponseMessage == null) {
				throw new InvalidResponseException();
			}
			if (isUnauthorizedAccessResponse(rawResponseMessage)) {
				throw new UnauthorizedException((JSON)rawResponseMessage);
			}
			return (JSON)rawResponseMessage;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}
	
	private static void addAuthentication(HttpMethod method, String username, String password) {
		String authStr = username + ":" + password;
		String authEncoded = Base64.encodeBase64String(authStr.getBytes());
		method.setRequestHeader("Authorization", "Basic " + authEncoded);
	}
	
	private static HttpMethod handleRedirect(HttpMethod method, HttpClient client, String username, String password) throws HttpException, IOException {
		Header redirectHeader = method.getResponseHeader("location");
		String redirectLocation = redirectHeader.getValue();
		GetMethod redirect = new GetMethod(redirectLocation);
		if (username != null) {
			addAuthentication(redirect, username, password);
		}
        client.executeMethod(redirect);
        return redirect;
	}

	private static boolean isRedirection(int statusCode) {
		return HttpStatus.SC_MOVED_PERMANENTLY == statusCode ||
		HttpStatus.SC_MOVED_TEMPORARILY == statusCode;
	}

	public static String getServer() {
		return server;
	}

	public static void setServer(String server) {
		ThalamusClient.server = server;
	}
}

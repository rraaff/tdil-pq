package com.tdil.ljpeugeot.lojack;

import static com.tdil.thalamus.client.core.ThalamusClient.getResponseString;

import java.io.IOException;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.utils.LJPeugeotConfig;
import com.tdil.log4j.LoggerProvider;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ProxyConfiguration;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;
import com.tdil.utils.CryptoUtils;

public class LoJackConnector {
	
	public static final Logger LOG = LoggerProvider.getLogger(LoJackConnector.class);
	public static String lojackServerUrl = "";
	public static ProxyConfiguration lojackServerProxy;
	
	public static final String ADD_TO_CACHE = "/rest/lojack/peugeot/add";
	
	public static void addToRemoteCache(JSON json) {
		try {
			JSONObject jsonObject = (JSONObject)json;
			String JSESSIONID = jsonObject.getString("JSESSIONID");
			String AWSELB = jsonObject.getString("AWSELB");
			String toSign = JSESSIONID + AWSELB + LJPeugeotConfig.getPeugeotSign();
			String signHeader = CryptoUtils.getHashedValue(toSign);
			execute(json, ADD_TO_CACHE, signHeader);
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(),e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(),e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(),e);
		} catch (UnauthorizedException e) {
			LOG.error(e.getMessage(),e);
		}
	}

	public static JSONResponse execute(JSON json, String service, String signHeader) throws HttpStatusException, InvalidResponseException,
			CommunicationException, UnauthorizedException {
		
		String server = getLojackServerUrl();
		long start = System.currentTimeMillis();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute: " + service + " json: " + (json == null ? "null" : json));
		}
		HttpClient client = new HttpClient();
		configureTimeout(client);
		if (server.endsWith("/")) {
			server = server + (service.substring(1));
		} else {
			server = server + service;
		}
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server);
		try {
			httpMethod.setRequestHeader("Content-type", "application/json");
			if (!StringUtils.isEmpty(signHeader)) {
				httpMethod.setRequestHeader("signHeader", signHeader);
			}
			if (json != null) {
				RequestEntity requestEntity = new StringRequestEntity(json.toString());
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = getResponseString(httpMethod);
			if (LOG.isDebugEnabled()) {
				LOG.debug("Remote result is: " + response + " with status: " + statusCode);
			}
			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED) {
				LOG.error("Remote result for " + (server + service) + " status: " + statusCode + " and body: " + (response == null ? "null" : response));
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			JSON result = extractJSONObjectResponse(response);
			return new JSONResponse(result, statusCode);
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
	
	public static void configureTimeout(HttpClient client) {
		HttpConnectionManager connectionManager = client.getHttpConnectionManager();
		connectionManager.getParams().setSoTimeout(getTIMEOUT());
		if (getLojackServerProxy() != null) {
			client.getHostConfiguration().setProxy(getLojackServerProxy().getServer(), getLojackServerProxy().getPort());
		}
	}
	
	private static int getTIMEOUT() {
		return 30000;
	}

	public static JSON extractJSONObjectResponse(String response) throws InvalidResponseException, UnauthorizedException {
		JSONTokener tokener = new JSONTokener(response);
		Object rawResponseMessage = tokener.nextValue();
		if (rawResponseMessage == null) {
			throw new InvalidResponseException();
		}
		return (JSON)rawResponseMessage;
	}
	
	public static String getLojackServerUrl() {
		return LoJackConnector.lojackServerUrl;
	}

	public static void setLojackServerUrl(String lojackServerUrl) {
		LoJackConnector.lojackServerUrl = lojackServerUrl;
	}

	public static ProxyConfiguration getLojackServerProxy() {
		return LoJackConnector.lojackServerProxy;
	}

	public static void setLojackServerProxy(ProxyConfiguration lojackServerProxy) {
		LoJackConnector.lojackServerProxy = lojackServerProxy;
	}
}

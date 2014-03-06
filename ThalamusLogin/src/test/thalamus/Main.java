package test.thalamus;


import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class Main {
	
	private static String touchpoint;
	private static String token;
		
	public static void main(String[] args)  {
		System.out.println("Example usage getcookies.jar https://testa-lojack-rest.thalamuslive.com/lojack/api/v1/signin?touchpoint=REAL-LIFE-SMARTPHONE-APP&token=k8exyct1v6edf9q50fryuq3r02upn0m98twst4etlbjgc9cnsq585879gxlcbu11 username password");
		String parsed = args[0].substring(args[0].indexOf('?') + 1);
		String splitted[] = parsed.split("&");
		for (String split : splitted) {
			if (split.startsWith("token")) {
				token = split.substring(split.indexOf('=') + 1);
			}
			if (split.startsWith("touchpoint")) {
				touchpoint = split.substring(split.indexOf('=') + 1);
			} 
		}
		executePost(args[0], "", "{\"password\":\""+args[2]+"\",\"principal\":\"1:"+args[1]+"\"}");
	}

	private static void executePost(String server, String service, String param)  {
		long start = System.currentTimeMillis();
		String xml = param;
		String url = service;
		/*
		if (urlParams != null) {
			for (Map.Entry<String, String> entries : urlParams.getParams().entrySet()) {
				url = url.replace(entries.getKey(), entries.getValue());
			}
		}*/
		HttpClient client = new HttpClient();
		EntityEnclosingMethod httpMethod = new PostMethod(server + url);
		try {
			httpMethod.setRequestHeader("Content-type", "application/json");
			if (xml != null) {
				RequestEntity requestEntity = new StringRequestEntity(xml);
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			for (Cookie cookie : client.getState().getCookies()) {
				System.out.println(cookie.getName() + ":" + cookie.getValue());
			}
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client);
	            response = redirect.getResponseBodyAsString();
	            statusCode = redirect.getStatusCode();
			}
			System.out.println(response);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private static HttpMethod handleRedirect(HttpMethod method, HttpClient client) throws HttpException, IOException {
		Header redirectHeader = method.getResponseHeader("location");
		String redirectLocation = redirectHeader.getValue();
		GetMethod redirect = new GetMethod(redirectLocation);
		addAuthentication(client, redirect, null);
        client.executeMethod(redirect);
        return redirect;
	}
	
	private static void addAuthentication(HttpClient client, HttpMethod method, NameValuePair...nvp) {
//		String authStr = username + ":" + password;
//		String authEncoded = Base64.encodeBase64String(authStr.getBytes());
//		method.setRequestHeader("Authorization", "Basic " + authEncoded);
		for (Cookie cookie : client.getState().getCookies()) {
			client.getState().addCookie(cookie);
		}
		addTouchpoint(method, nvp);
//		method.setRequestHeader("thalamus-header-touchpoint", getTHALAMUS_TOUCHPOINT());
	}
	
	
	private static void addTouchpoint(HttpMethod method, NameValuePair...nvp) {
		if (nvp == null || nvp.length == 0) {
			method.setQueryString(new NameValuePair[] {new NameValuePair("touchpoint", touchpoint), new NameValuePair("token", token)});
		} else {
			NameValuePair allParams[] = new NameValuePair[nvp.length + 2];
			System.arraycopy(nvp, 0, allParams, 0, nvp.length);
			allParams[nvp.length] = new NameValuePair("touchpoint", touchpoint);
			allParams[nvp.length + 1] = new NameValuePair("token", token);
			method.setQueryString(allParams);
		}
	}
	
	private static boolean isRedirection(int statusCode) {
		return HttpStatus.SC_MOVED_PERMANENTLY == statusCode ||
		HttpStatus.SC_MOVED_TEMPORARILY == statusCode;
	}
}

package com.tdil.thalamus.delete;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Calendar;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;

public class TestToDelete {
	
	private static String server = "http://ec2-23-23-84-70.compute-1.amazonaws.com:9080";

	public static void main(String[] args) throws Exception {
		Calendar start = Calendar.getInstance();
		start.set(1970, 0, 1, 0,0,0);
		start.set(Calendar.MILLISECOND, 0);
		System.out.println(new Date(714070884661l - start.getTimeInMillis()));
	
//		Cookie jsession = login("user1@mail.com", "1234");
//		System.out.println(jsession);
//		System.out.println(get(jsession));
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("attr", "el valor");
//		JSONObject result = sendAndReceive(jsonObject);
//		System.out.println(result);
//		System.out.println(result.get("DEBUG"));
//		testGetCountries();
	}
	
	public static JSON executeGet(String username, String password, String service) throws HttpStatusException, InvalidResponseException, CommunicationException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod(server + service);
		addAuthentication(httpMethod, username, password);
		try {
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (isRedirection(statusCode)) {
				HttpMethod redirect = handleRedirect(httpMethod, client, username, password);
	            response = redirect.getResponseBodyAsString();
	            statusCode = redirect.getStatusCode();
			}
			if (statusCode != HttpStatus.SC_OK)
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			JSONTokener tokener = new JSONTokener(response);
			Object rawResponseMessage = tokener.nextValue();
			if (rawResponseMessage == null)
				throw new InvalidResponseException();
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
		addAuthentication(redirect, username, password);
        client.executeMethod(redirect);
        return redirect;
	}

	private static boolean isRedirection(int statusCode) {
		return HttpStatus.SC_MOVED_PERMANENTLY == statusCode ||
		HttpStatus.SC_MOVED_TEMPORARILY == statusCode;
	}

	private static void testGetCountries() throws HttpStatusException, InvalidResponseException, CommunicationException {
		HttpClient client = new HttpClient();
		GetMethod postMethod = new GetMethod(server + "/thalamus/api/countries");
		 String authStr = "user1@mail.com" + ":" + "1234";
		String authEncoded = Base64.encodeBase64String(authStr.getBytes());
		postMethod.setRequestHeader("Authorization", "Basic " + authEncoded);
		try {
			client.executeMethod(postMethod);
				int statusCode = postMethod.getStatusCode();
			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_MOVED_TEMPORARILY)
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			JSONTokener tokener = new JSONTokener(postMethod.getResponseBodyAsString());
			Object rawResponseMessage = tokener.nextValue();
			Object responseMessage = rawResponseMessage;
			if (responseMessage == null)
				throw new InvalidResponseException();
			System.out.println(responseMessage);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}

	public static Cookie login(String username, String password) throws Exception {
		Cookie jsessionid = null;
		HttpClient client = new HttpClient();
		HttpState state = new HttpState();
		String authStr = "user1@mail.com" + ":" + "1234";
        String authEncoded = Base64.encodeBase64String(authStr.getBytes());
		PostMethod postMethod = new PostMethod(server + "/thalamus/api/thalamus_security_check");
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		postMethod.setRequestHeader("Authorization", "Basic " + authEncoded);
		String data = "j_username="+URLEncoder.encode(username)+"&j_password=" + URLEncoder.encode(password);
		System.out.println(data);
		postMethod.setRequestHeader("Content-Length", String.valueOf(data.length()));
		RequestEntity requestEntity = new StringRequestEntity(data);
		postMethod.setRequestEntity(requestEntity);
		try {
			client.executeMethod(null, postMethod, state);
			int statusCode = postMethod.getStatusCode();
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || 
					statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				String redirectLocation;
		        Header locationHeader = postMethod.getResponseHeader("location");
		        if (locationHeader != null) {
		            redirectLocation = locationHeader.getValue();
		            System.out.println(redirectLocation);
		            GetMethod red = new GetMethod(redirectLocation);
		            red.setRequestHeader("Authorization", "Basic " + authEncoded);
		            client.executeMethod(red);
		            System.out.println(postMethod.getStatusCode());
		            System.out.println(locationHeader.getValue());
		            System.out.println(red.getResponseBodyAsString());
		            System.out.println(postMethod.getResponseHeader("location"));
		        } else {
		            // The response is invalid and did not provide the new location for
		            // the resource.  Report an error or possibly handle the response
		            // like a 404 Not Found error.
		        }
			}
//			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_MOVED_TEMPORARILY)
//				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
//			System.out.println(postMethod.getResponseBodyAsString());
//			JSONTokener tokener = new JSONTokener(postMethod.getResponseBodyAsString());
//			Object rawResponseMessage = tokener.nextValue();
//			JSONObject responseMessage = (JSONObject) rawResponseMessage;
//			if (responseMessage == null)
//				throw new InvalidResponseException();
//			return responseMessage;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
//		System.out.println(get(client, jsessionid));
		return jsessionid;
	}
	
	public static JSONObject get(HttpClient client, Cookie jsessionID) throws Exception {
//		HttpClient client = new HttpClient();
//		client.getState().addCookie(jsessionID);
		GetMethod postMethod = new GetMethod(server + "/thalamus/api/countries");
//		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			client.executeMethod(postMethod);
				int statusCode = postMethod.getStatusCode();
			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_MOVED_TEMPORARILY)
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			JSONTokener tokener = new JSONTokener(postMethod.getResponseBodyAsString());
			Object rawResponseMessage = tokener.nextValue();
			JSONObject responseMessage = (JSONObject) rawResponseMessage;
			if (responseMessage == null)
				throw new InvalidResponseException();
			return responseMessage;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}

	public static JSONObject sendAndReceive(JSONObject message) throws Exception {
		HttpClient client = new HttpClient();
//		client.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("user1@mail.com", "1234");
		client.getState().setCredentials(AuthScope.ANY, defaultcreds);
		client.getState().setProxyCredentials(AuthScope.ANY, defaultcreds);
		GetMethod getMethod = new GetMethod(server + "/thalamus/api/countries");
		//getMethod.setRequestHeader("Content-Type", "application/json");
//		RequestEntity requestEntity = new StringRequestEntity(message.toString());
//		postMethod.setRequestEntity(requestEntity);
		try {
			client.executeMethod(null, getMethod, new HttpState());
			int statusCode = getMethod.getStatusCode();
			if (statusCode != HttpStatus.SC_OK)
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			System.out.println(getMethod.getResponseBodyAsString());
			JSONTokener tokener = new JSONTokener(getMethod.getResponseBodyAsString());
			Object rawResponseMessage = tokener.nextValue();
			JSONObject responseMessage = (JSONObject) rawResponseMessage;
			if (responseMessage == null)
				throw new InvalidResponseException();
			return responseMessage;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}

	public static String getServer() {
		return server;
	}

	public static void setServer(String server) {
		TestToDelete.server = server;
	}
}

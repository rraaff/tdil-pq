package com.facebook.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;


public class CreateTestUser {

	public static void main(String[] args) throws HttpException, IOException {
		create(FacebookContants.APP_ID, FacebookContants.ACCESS_TOKEN, "Juan%20Perez");
	}

	public static void create(String appId, String accessToken, String name) throws IOException, HttpException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/" + appId + 
					"/accounts/test-users?installed=false" +
					"&name=" + name +
					"&locale=en_US" +
					"&permissions=read_stream" +
					"&method=post&access_token=" + accessToken);
		System.out.println(httpMethod.getQueryString());
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		JSONObject array = JSONObject.fromObject(response);
		System.out.println(array.toString(2));
	}
}

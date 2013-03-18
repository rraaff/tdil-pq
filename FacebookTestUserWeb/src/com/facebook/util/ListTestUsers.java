package com.facebook.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class ListTestUsers {

	public static void main(String[] args) throws HttpException, IOException {
		JSONObject array = list(FacebookContants.APP_ID, FacebookContants.ACCESS_TOKEN);
	}

	public static JSONObject list(String appId, String accessToken) throws IOException, HttpException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/"+appId+"/accounts/test-users?access_token=" + accessToken);
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		JSONObject array = JSONObject.fromObject(response);
		return array;
	}
}

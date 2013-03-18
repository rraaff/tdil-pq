package com.facebook.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class DeleteTestUser {

	public static void main(String[] args) throws HttpException, IOException {
		String test_user_id = "";
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/"+test_user_id+"" +
				"?method=delete" +
				"&access_token=" + FacebookContants.ACCESS_TOKEN);
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		System.out.println(response);
	}
}

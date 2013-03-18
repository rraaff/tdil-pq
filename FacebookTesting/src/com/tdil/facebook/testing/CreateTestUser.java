package com.tdil.facebook.testing;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;


public class CreateTestUser {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/" + FacebookContants.APP_ID + 
					"/accounts/test-users?installed=false" +
					"&name=Juan%20Perez" +
					"&locale=en_US" +
					"&permissions=read_stream" +
					"&method=post&access_token=" + FacebookContants.ACCESS_TOKEN);
		System.out.println(httpMethod.getQueryString());
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		JSONObject array = JSONObject.fromObject(response);
		System.out.println(array.toString(2));
	}
}

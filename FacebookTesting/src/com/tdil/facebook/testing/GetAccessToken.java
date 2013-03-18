package com.tdil.facebook.testing;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;


public class GetAccessToken {
	
	public static String ACCESS_TOKEN = "";

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/oauth/access_token?" +
				"grant_type=client_credentials&client_id=" +FacebookContants.APP_ID+ 
				"&client_secret=" + FacebookContants.CLIENT_SECRET);
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		ACCESS_TOKEN = response.substring(response.indexOf('=') + 1);
		System.out.println(response);
	}
}

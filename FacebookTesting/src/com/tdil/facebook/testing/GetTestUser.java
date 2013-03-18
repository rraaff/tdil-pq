package com.tdil.facebook.testing;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class GetTestUser {

	public static void main(String[] args) throws HttpException, IOException {
		String test_user_id = "100005455842995";
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/"+test_user_id);
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		JSONObject array = JSONObject.fromObject(response);
		System.out.println(array.toString(2));
	}
}

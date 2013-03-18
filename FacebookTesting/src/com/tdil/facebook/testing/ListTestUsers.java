package com.tdil.facebook.testing;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class ListTestUsers {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod("https://graph.facebook.com/"+FacebookContants.APP_ID+"/accounts/test-users?access_token=" + FacebookContants.ACCESS_TOKEN);
		client.executeMethod(httpMethod);
		int statusCode = httpMethod.getStatusCode();
		String response = httpMethod.getResponseBodyAsString();
		JSONObject array = JSONObject.fromObject(response);
		System.out.println(array.toString(2));
	}
}

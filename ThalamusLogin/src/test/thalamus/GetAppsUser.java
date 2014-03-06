package test.thalamus;


import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class GetAppsUser {
	
	private static String touchpoint;
	private static String token;
		
	public static void main(String[] args)  {
		System.out.println("Example usage getappsuser.jar http://server/ server [TJSESSIONID] [AWSELB]");
		String url = args[0];
		String domain = args[1];
		String tsession = args[2];
		String aws = args[3];
		System.out.println(url + "rest/lojack/api/v1/person/profile/homeUser");
		executeGet(url + "rest/lojack/api/v1/person/profile/homeUser", domain, tsession, aws);
		System.out.println(url + "rest/lojack/api/v1/person/profile/petUser");
		executeGet(url + "rest/lojack/api/v1/person/profile/petUser", domain, tsession, aws);
		System.out.println(url + "rest/lojack/api/v1/person/profile/preventUser");
		executeGet(url + "rest/lojack/api/v1/person/profile/preventUser", domain, tsession, aws);
	}

	private static void executeGet(String server, String domain, String tsession, String aws)  {
				/*
		if (urlParams != null) {
			for (Map.Entry<String, String> entries : urlParams.getParams().entrySet()) {
				url = url.replace(entries.getKey(), entries.getValue());
			}
		}*/
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod(server);
		try {
			Cookie cookie = new Cookie(domain, "TJSESSIONID", tsession, "/", 1000000, false);
			Cookie cookie1 = new Cookie(domain, "AWSELB", aws, "/", 1000000, false);
			HttpState state = client.getState();
			state.addCookies(new Cookie[] {cookie, cookie1});
			client.setState(state);
			httpMethod.setRequestHeader("Content-type", "application/json");
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			System.out.println(response);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
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

package com.facebook;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import net.sf.json.JSONObject;

import com.facebook.util.ListTestUsers;

public class FacebookTestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4472604870712273805L;
	
	private String appName;
	private String appId;
	private String appSecret;
	private String accessToken;
	
	private List<FacebookTestUser> testUsers = new ArrayList<FacebookTestUser>();

	public void refreshTestUsers() throws HttpException, IOException {
		JSONObject object = ListTestUsers.list(this.getAppId(), this.getAccessToken());
		
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public List<FacebookTestUser> getTestUsers() {
		return testUsers;
	}

	public void setTestUsers(List<FacebookTestUser> testUsers) {
		this.testUsers = testUsers;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}

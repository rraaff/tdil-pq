package com.tdil.lojack.camera;

import java.io.ByteArrayInputStream;

import org.apache.commons.codec.binary.Base64;

public abstract class IPCamera {
	
	private String url;
	private String username;
	private String password;
	
	private String basicAuth;
	
	public IPCamera(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
		this.basicAuth = "Basic " + Base64.encodeBase64String((this.getUsername() + ":" + this.getPassword()).getBytes());
	}

	public abstract ByteArrayInputStream nextFrame();
	
	public abstract void left();
	public abstract void right();
	public abstract void up();
	public abstract void down();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBasicAuth() {
		return basicAuth;
	}

	public void setBasicAuth(String basicAuth) {
		this.basicAuth = basicAuth;
	}
}

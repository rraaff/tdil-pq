package com.tdil.lojack.camera;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
	public abstract String getMimeType();
	
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

	protected void readFully(String urlString) {
		HttpURLConnection conn = null;
		BufferedInputStream httpIn = null;
		URL url;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
			return;
		}
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", this.getBasicAuth());
			configureTimeout(conn);
			httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
			try {
				while (httpIn != null && (httpIn.read()) >= 0) {
				}
				return;
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("I/O Error: " + e.getMessage());
				return;
			}
		} catch (IOException e) {
			System.err.println("Unable to connect: " + e.getMessage());
			return;
		} finally {
			if (httpIn != null) {
				try {
					httpIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	protected void configureTimeout(HttpURLConnection conn) {
		conn.setConnectTimeout(1000);
		conn.setReadTimeout(1000);
	}

}

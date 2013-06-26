package com.tdil.lojack.camera;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public abstract class IPCamera implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9085636428871057362L;
	private String url;
	private String username;
	private String password;
	
	private String basicAuth;
	
	private int connectTimeOut = 5000;
	private int readTimeOut = 5000;
	
	private static ProxyConfiguration proxyConfiguration;
	private static ProxyConfiguration proxyConfigurationHttps;
	
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
		ProxyConfiguration proxyConfiguration = null;
		try {
			if (urlString.toLowerCase().startsWith("https")) {
				proxyConfiguration = getProxyConfigurationHttps();
			} else {
				proxyConfiguration = getProxyConfiguration();
			}
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
			return;
		}
		try {
			if (proxyConfiguration != null) {
				conn = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfiguration.getServer(), proxyConfiguration.getPort())));
			} else {
				conn = (HttpURLConnection) url.openConnection();
			}
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
		conn.setConnectTimeout(this.getConnectTimeOut());
		conn.setReadTimeout(this.getReadTimeOut());
	}

	public static ProxyConfiguration getProxyConfiguration() {
		return proxyConfiguration;
	}

	public static void setProxyConfiguration(ProxyConfiguration proxyConfiguration) {
		IPCamera.proxyConfiguration = proxyConfiguration;
	}

	public static ProxyConfiguration getProxyConfigurationHttps() {
		return proxyConfigurationHttps;
	}

	public static void setProxyConfigurationHttps(ProxyConfiguration proxyConfigurationHttps) {
		IPCamera.proxyConfigurationHttps = proxyConfigurationHttps;
	}

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

}

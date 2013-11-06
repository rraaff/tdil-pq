package com.tdil.lojack.camera;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class TrendnetTVIP851 extends IPCamera {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7542741455885976217L;
	private static final String IMAGE = "/image.jpg";

	private static final String MOVE = "/pantiltcontrol.cgi";

	private static final String LEFT = "PanTiltSingleMove=3&PanSingleMoveDegree=5&TiltSingleMoveDegree=5";
	private static final String RIGHT = "PanTiltSingleMove=5&PanSingleMoveDegree=5&TiltSingleMoveDegree=5";
	private static final String UP = "PanTiltSingleMove=1&PanSingleMoveDegree=5&TiltSingleMoveDegree=5";
	private static final String DOWN = "PanTiltSingleMove=7&PanSingleMoveDegree=5&TiltSingleMoveDegree=5";
	public static final String TrendnetTVIP851 = "3";

	private HttpURLConnection conn;
	private BufferedInputStream httpIn;

	protected TrendnetTVIP851(String url, String username, String password) {
		super(url, username, password);
	}

	@Override
	public String getMimeType() {
		return "image/jpeg";
	}

	@Override
	public void cancelDownload() {

	}

	@Override
	public ByteArrayInputStream nextFrame() {
		try {
			HttpClient client = configureHttpClient();
			GetMethod get = new GetMethod(this.getUrl() + IMAGE);
			get.setDoAuthentication(true);
			client.executeMethod(get);
			InputStream result = get.getResponseBodyAsStream();
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			copy(result, byteOut);
			return new ByteArrayInputStream(byteOut.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("I/O Error: " + e.getMessage());
			return null;
		}
	}

	private HttpClient configureHttpClient() throws MalformedURLException {
		ProxyConfiguration proxyConfiguration = null;
		if (getUrl().toLowerCase().startsWith("https")) {
			proxyConfiguration = getProxyConfigurationHttps();
		} else {
			proxyConfiguration = getProxyConfiguration();
		}
		HttpClient client = new HttpClient();
		configureTimeout(client, proxyConfiguration);
		Credentials creds = new UsernamePasswordCredentials(getUsername(), getPassword());
		URL urlParse = new URL(this.getUrl());
		client.getState().setCredentials(new AuthScope(urlParse.getHost(), urlParse.getPort(), "TV-IP851WIC"), creds);
		client.getParams().setAuthenticationPreemptive(true); // seems to be necessary in most cases
		client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, Collections.singleton(AuthPolicy.DIGEST));//need to register DIGEST scheme not the basic
		List authPrefs = new ArrayList(2);
		authPrefs.add(AuthPolicy.DIGEST);
		client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);
		return client;
	}
	
	private  static void copy(InputStream in, OutputStream out) {
	    try {
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void down() {
		try {
			HttpClient client = configureHttpClient();
			PostMethod post = new PostMethod(this.getUrl() + MOVE);
			post.setDoAuthentication(true);
			post.setRequestEntity(new StringRequestEntity(DOWN));
			client.executeMethod(post);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("I/O Error: " + e.getMessage());
		}
	}

	@Override
	public void left() {
		try {
			HttpClient client = configureHttpClient();
			PostMethod post = new PostMethod(this.getUrl() + MOVE);
			post.setDoAuthentication(true);
			post.setRequestEntity(new StringRequestEntity(LEFT));
			client.executeMethod(post);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("I/O Error: " + e.getMessage());
		}
	}

	@Override
	public void right() {
		try {
			HttpClient client = configureHttpClient();
			PostMethod post = new PostMethod(this.getUrl() + MOVE);
			post.setDoAuthentication(true);
			post.setRequestEntity(new StringRequestEntity(RIGHT));
			client.executeMethod(post);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("I/O Error: " + e.getMessage());
		}
	}

	@Override
	public void up() {
		try {
			HttpClient client = configureHttpClient();
			PostMethod post = new PostMethod(this.getUrl() + MOVE);
			post.setDoAuthentication(true);
			post.setRequestEntity(new StringRequestEntity(UP));
			client.executeMethod(post);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("I/O Error: " + e.getMessage());
		}
	}

}

package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;

public class URLHolder implements Serializable {

	private static final long serialVersionUID = 7281299731818330887L;

	private String url;
	private List<Cookie> cookies = new ArrayList<Cookie>();

	public URLHolder() {
	}
	
	public URLHolder(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Cookie> getCookies() {
		return cookies;
	}
	
	public Cookie getCookie(String string) {
		for (Cookie cookie : this.cookies) {
			if (cookie.getName().equals(string)) {
				return cookie;
			}
		}
		return null;
	}

	public void addCookie(Cookie cookie) {
		this.cookies.add(cookie);
	}
	
	public void addCookies(List<Cookie> cookies) {
		this.cookies.addAll(cookies);
	}

}

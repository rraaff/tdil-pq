package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;

public class TokenHolder implements Serializable {

	private List<Cookie> cookies = new ArrayList<Cookie>();

	public List<Cookie> getCookies() {
		return cookies;
	}

	public void addCookie(Cookie cookie) {
		this.cookies.add(cookie);
	}

	public boolean hasToken() {
		if (this.getCookies().isEmpty()) {
			return false;
		}
		for (Cookie cookie : this.getCookies()) {
			if (cookie.isExpired()) {
				return false;
			}
		}
		return true;
	}

	public Cookie getCookie(String string) {
		for (Cookie cookie : this.cookies) {
			if (cookie.getName().equals(string)) {
				return cookie;
			}
		}
		return null;
	}
		
}

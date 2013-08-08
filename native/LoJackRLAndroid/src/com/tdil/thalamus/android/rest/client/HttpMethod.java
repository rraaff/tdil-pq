package com.tdil.thalamus.android.rest.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

public enum HttpMethod {

	GET() {
		@Override
		public HttpRequestBase create(String url) {
			return new HttpGet(url);
		}
		
	}, POST() {
		@Override
		public HttpRequestBase create(String url) {
			return new HttpPost(url);
		}
	};
	
	public abstract HttpRequestBase create(String url);
}

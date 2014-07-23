package com.tdil.thalamus.android.rest.client;

import java.util.HashMap;
import java.util.Map;

public class RestParams {

	private Map<String, String> params = new HashMap<String, String>();
	
	public Map<String, String> getParams() {
		return params;
	}

	public RestParams() {
		// TODO Auto-generated constructor stub
	}
	
	public RestParams(String key, String value) {
		params.put(key, value);
	}
	
	public RestParams put(String key, String value) {
		params.put(key, value);
		return this;
	}
}

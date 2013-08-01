package com.tdil.thalamus.android.rest.client;

import java.util.Map;

public class RESTClient {
	
	public static final String getUrl(String url, Map<String, String> replacements) {
		String result = url;
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			result = result.replace("{" + entry.getKey() + "}", entry.getValue());
		}
		return result;
	}
}

package com.tdil.peugeotservice.android.rest.client;

import java.util.HashMap;
import java.util.Map;


public class RESTClient {
	
	/*public static final String getUrl(String url, Map<String, String> replacements) {
		String result = url;
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			result = result.replace("{" + entry.getKey() + "}", entry.getValue());
		}
		return result;
	}*/
	
	public static Map<String, String> params(String ...params) {
		Map<String, String> result = new HashMap<String, String>();
		for (int i = 0; i< params.length; i+= 2) {
			result.put(params[i], params[i + 1]);
		}
		return result;
	}
}

package com.tdil.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class SystemPropertyCache {

	public static final String MAIL_SERVER = "prop.mailserver";
	public static final String SERVER_NAME = "prop.server.name";
	public static final String SERVER_URL = "prop.server.url";
	public static final String TEMP_PATH = "prop.tmp.path";
	
	private static Map<String, String> cache = new ConcurrentHashMap<String, String>();
	
	public static void put(String key, String value) {
		cache.put(key, value);
	}
	
	public static String get(String key) {
		return cache.get(key);
	}
	
	public static String get(String key, String defValue) {
		if (cache.containsKey(key)) {
			return cache.get(key);
		} else {
			return defValue;
		}
	}
	
	public static String getMailServer() {
		return cache.get(MAIL_SERVER);
	}

	public static String getServerName() {
		return cache.get(SERVER_NAME);
	}
	
	public static String getServerUrl() {
		return cache.get(SERVER_URL);
	}
	
	public static String getTempPath() {
		String result = cache.get(TEMP_PATH);
		if (result == null) {
			return System.getProperty("java.io.tmpdir");
		}
		return cache.get(TEMP_PATH);
	}
	
}

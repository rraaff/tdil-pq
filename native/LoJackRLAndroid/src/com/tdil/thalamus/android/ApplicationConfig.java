package com.tdil.thalamus.android;

public class ApplicationConfig {

	public static final String URL_ANDROID_VERSION = ApplicationConfig.URL_WEBSITE
	+ "android_version.txt";
	
	/*public static final String URL_WEBSITE = "http://192.168.0.123:8180/LoJackWeb/";*/
	public static final String URL_WEBSITE = "http://10.0.2.2:8180/LoJackWeb/";
	

	public static int default_timeout = 10000;
	
	public static int thalamus_timeout = 10000;
	public static int job_client_refresh_time = 1000;
	public static int job_abort_time = 60000;
	
	
}

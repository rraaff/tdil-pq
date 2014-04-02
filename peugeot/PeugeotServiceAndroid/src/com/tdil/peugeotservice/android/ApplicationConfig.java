package com.tdil.peugeotservice.android;

public class ApplicationConfig {


	public static final String URL_ANDROID_VERSION = ApplicationConfig.URL_WEBSITE
	+ "android_version_native.txt";
	
//	public static final String APP_DOMAIN = "www.lojack-app.com.ar";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/";
	public static final String APP_DOMAIN = "www.lojack-app.com.ar:8180";
	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/";
//	public static final String APP_DOMAIN = "192.168.252.100:8380";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/PeugeotServiceWeb/";
//	public static final String APP_DOMAIN = "10.0.2.2:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";
	
	public static final String URL_PARKINGS = URL_WEBSITE + "productoPoiAndroid.jsp";
	public static final String URL_PREVENT = URL_WEBSITE + "goToPreventLoginAndroid.do?timezone=-180&USING_APK=true";

	public static int default_timeout = 10000;
	
	public static int thalamus_timeout = 10000;
	public static int job_client_refresh_time = 1000;
	public static int job_abort_time = 60000;
	
	
}

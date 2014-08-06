package com.tdil.thalamus.android;

public class ApplicationConfig {


	public static final String URL_ANDROID_VERSION = ApplicationConfig.URL_WEBSITE
	+ "getNativeAppVersion.st?code=android";

//PRODA
	public static final String APP_DOMAIN = "www.lojack-app.com.ar";
	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/";

//PREPRO
//	public static final String APP_DOMAIN = "www.lojack-app.com.ar:81";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/";

//PABLO
//	public static final String APP_DOMAIN = "192.168.252.109:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";

//MARCOS
//public static final String APP_DOMAIN = "192.168.0.122:8180";
//public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";

//	public static final String APP_DOMAIN = "10.0.2.2:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";
	
	public static final String URL_PARKINGS = URL_WEBSITE + "productoParkingsAndroid.jsp";
	public static final String URL_PREVENT = URL_WEBSITE + "goToPreventLoginAndroid.do?timezone=-180&USING_APK=true";

	public static int default_timeout = 10000;
	
	public static int thalamus_timeout = 10000;
	public static int job_client_refresh_time = 1000;
	public static int job_abort_time = 60000;
	
	
}
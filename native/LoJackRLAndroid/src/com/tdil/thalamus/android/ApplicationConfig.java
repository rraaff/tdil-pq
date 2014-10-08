package com.tdil.thalamus.android;

public class ApplicationConfig {
	public static final String URL_ANDROID_VERSION = ApplicationConfig.URL_WEBSITE
	+ "getNativeAppVersion.st?code=android";

//PRODA
//	public static final String APP_DOMAIN = "www.lojack-app.com.ar";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/";

//PREPRO
	public static final String APP_DOMAIN = "www.lojack-app.com.ar:82";
	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/";

//PABLO
//	public static final String APP_DOMAIN = "192.168.252.104:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";
	
//MARCOS
//	public static final String APP_DOMAIN = "192.168.0.134:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";
	
//RODRIGO
//	public static final String APP_DOMAIN = "192.168.1.106:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";

//	public static final String APP_DOMAIN = "10.0.2.2:8180";
//	public static final String URL_WEBSITE = "http://" + APP_DOMAIN + "/LoJackWeb/";

	/* DEPRECATED */
	public static final String URL_PARKINGS = URL_WEBSITE + "productoParkingsAndroid.jsp";
	public static final String URL_PREVENT = URL_WEBSITE + "goToPreventLoginAndroid.do?timezone=-180&USING_APK=true";
	
//	public static final String URL_DEMO_CAR = URL_WEBSITE + "goToPreventLoginAndroid.do?timezone=-180&USING_APK=true";
	public static final String URL_HOME_NOT_CLIENT =		URL_WEBSITE + "lohome_notclient.jsp";
	public static final String URL_CARS_NOT_CLIENT =		URL_WEBSITE + "locar_notclient.jsp";
	public static final String URL_PETS_NOT_CLIENT =		URL_WEBSITE + "lopet_notclient.jsp";
	public static final String URL_PARKED_MODE_NOT_CLIENT =	URL_WEBSITE + "lopet_notclient.jsp";
	
	public static final String URL_NOT_CLIENT = URL_WEBSITE + "loapp_notclient.jsp";
	
	public static final String URL_LEGALES_NOT_LOGGED = URL_WEBSITE + "legales_webview.jsp";
	
	public static final String ALERT_CENTER_PHONE = "08003330911";
	public static final String HOME_ASSIST_CENTER_PHONE = "08003330911";
	public static final String CAR_ASSIST_CENTER_PHONE = "08003330911";

	public static int default_timeout = 10000;
	
	public static int thalamus_timeout = 10000;
	public static int job_client_refresh_time = 1000;
	public static int job_abort_time = 60000;
}
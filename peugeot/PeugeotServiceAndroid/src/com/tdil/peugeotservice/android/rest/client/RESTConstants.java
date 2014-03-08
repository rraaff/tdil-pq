package com.tdil.peugeotservice.android.rest.client;

import com.tdil.peugeotservice.android.ApplicationConfig;

public interface RESTConstants {

	public static final String P_PASSWORD = "{password}";

	public static final String P_DOCUMENT_NUMBER = "{documentNumber}";

	public static final String P_DOCUMENT_TYPE = "{documentType}";

	public static final String REST_URL = ApplicationConfig.URL_WEBSITE + "rest";
	
	public static final String LOGIN = "/users/login?documentType=" +P_DOCUMENT_TYPE+"&documentNumber="+P_DOCUMENT_NUMBER+"&password="+P_PASSWORD;
	public static final String DOCUMENT_TYPES = "/users/documentTypes";
	public static final String STATES = "/users/states";
	public static final String ADDRESS_TYPES = "/users/addressTypes";
	
	public static final String LOGOUT = "/users/logout";
	
	public static final String GET_USER = "/users/get";
	public static final String CREATE_USER = "/users/create";
	public static final String SAVE_USER = "/users/update";
	public static final String CHANGE_PASSWORD = "/users/password";
	
	public static final String LOG_ERROR_APK = "/users/logApkError";
	
	public static final String REQUEST_RESET_PASSWORD = "/users/requestResetPassword?documentType=" +P_DOCUMENT_TYPE+"&documentNumber="+P_DOCUMENT_NUMBER;
	
	public static final String MY_VEHICLES = "/vehicles/list";
}

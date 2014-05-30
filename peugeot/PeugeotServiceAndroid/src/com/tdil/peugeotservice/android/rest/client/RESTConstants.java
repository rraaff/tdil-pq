package com.tdil.peugeotservice.android.rest.client;

import com.tdil.peugeotservice.android.ApplicationConfig;

public interface RESTConstants {


	public static final String P_PASSWORD = "{password}";

	public static final String P_DOCUMENT_NUMBER = "{documentNumber}";

	public static final String P_DOCUMENT_TYPE = "{documentType}";
	
	public static final String P_DEALER_STATE = "{stateId}";
	public static final String P_DEALER_CITY = "{cityId}";
	
	public static final String P_VEHICLE = "{vehicleId}";
	public static final String P_DEALER = "{dealerId}";
	public static final String P_EMAIL = "{email}";
	public static final String P_DATE = "{date}";
	public static final String P_KM = "{km}";
	public static final String P_LAT = "{lat}";
	public static final String P_LON = "{lon}";
	public static final String P_PHONE = "{phone}";
	public static final String P_ALERTID = "{alertId}";

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
	
	public static final String DEALERS_STATES = "/dealers/states";
	public static final String DEALERS_CITIES = "/dealers/cities/{stateId}";
	public static final String DEALERS = "/dealers/list/{cityId}";
	
	public static final String CHANGE_DEALER = "/vehicles/{vehicleId}/changeDealer/{dealerId}/{email}";
	
	public static final String GET_EMAIL_FOR_ADVICE = "/vehicles/emailForAdvice";
	public static final String ADD_SERVICE = "/vehicles/{vehicleId}/addservice/{date}/{km}";
	public static final String GET_ADVICES = "/vehicles/advices";
	public static final String DISMISS_ADVICES = "/vehicles/dismissAdvices";
	
	public static final String ADD_ALERT = "/vehicles/addAlert/" + P_PHONE + "/" + P_LAT + "/" + P_LON;
	public static final String UPDATE_ALERT = "/vehicles/updateAlert/" + P_ALERTID + "/" + P_LAT + "/" + P_LON;
	public static final String GET_CURRENT_MONTH_ALERTS = "/vehicles/alerts/currentMonth";
	
	public static final String GET_CONTACT_DATA = "/users/contactData";
	public static final String POST_CONTACT_DATA = "/users/contactData";
	
}

package com.tdil.thalamus.android.rest.client;

import com.tdil.thalamus.android.ApplicationConfig;

public interface RESTConstants {

	public static final String ID_ENTIDAD = "{idEntidad}";

	public static final String P_PASSWORD = "{password}";

	public static final String P_DOCUMENT_NUMBER = "{documentNumber}";

	public static final String P_DOCUMENT_TYPE = "{documentType}";

	public static final String REST_URL = ApplicationConfig.URL_WEBSITE + "rest";

	public static final String LOGIN = "/users/login?documentType=" +P_DOCUMENT_TYPE+"&documentNumber="+P_DOCUMENT_NUMBER+"&password="+P_PASSWORD;
	public static final String DOCUMENT_TYPES = "/users/documentTypes";
	
	
	public static final String ALARMS = "/alarms/list";
	public static final String ACTIVATE_ALARM =  "/alarms/" + ID_ENTIDAD + "/activate";
	public static final String DEACTIVATE_ALARM =  "/alarms/" + ID_ENTIDAD + "/deactivate";
}

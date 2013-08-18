package com.tdil.thalamus.android.rest.client;

public interface RESTConstants {

	public static final String P_PASSWORD = "{password}";

	public static final String P_DOCUMENT_NUMBER = "{documentNumber}";

	public static final String P_DOCUMENT_TYPE = "{documentType}";

	public static final String REST_URL = "http://192.168.0.110:8180/LoJackWeb/rest";

	public static final String LOGIN = "/users/login?documentType=" +P_DOCUMENT_TYPE+"&documentNumber="+P_DOCUMENT_NUMBER+"&password="+P_PASSWORD;
}

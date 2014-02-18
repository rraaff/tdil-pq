package com.tdil.peugeotservice.android.rest.client;

import com.tdil.peugeotservice.android.ApplicationConfig;

public interface RESTConstants {

	public static final String ID_ENTIDAD = "{idEntidad}";
	public static final String ID_LUZ = "{idluz}";

	public static final String P_PASSWORD = "{password}";

	public static final String P_DOCUMENT_NUMBER = "{documentNumber}";

	public static final String P_DOCUMENT_TYPE = "{documentType}";

	public static final String REST_URL = ApplicationConfig.URL_WEBSITE + "rest";
	
	public static final String CAMERA_URL = ApplicationConfig.URL_WEBSITE + "viewCameraService?cameraUrl=";
	public static final String CAMERA_MOVE_URL = ApplicationConfig.URL_WEBSITE + "moveCameraService?cameraUrl=";
	
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
	
	public static final String LOGIN_PETS = "/users/loginPets";
	
	public static final String REQUEST_RESET_PASSWORD = "/users/requestResetPassword?documentType=" +P_DOCUMENT_TYPE+"&documentNumber="+P_DOCUMENT_NUMBER;
	
	public static final String LIGHT_STATUS = "/jobs/lights/list";
	public static final String ALARM_STATUS = "/jobs/alarms/list";
	
	public static final String ALARMS = "/alarms/list";
	public static final String ACTIVATE_ALARM =  "/alarms/" + ID_ENTIDAD + "/activate";
	public static final String DEACTIVATE_ALARM =  "/alarms/" + ID_ENTIDAD + "/deactivate";
	
	public static final String SEND_PANIC_ALARM =  "/alarms/" + ID_ENTIDAD + "/sendPanic";
	
	public static final String LOG_ALARM =  "/alarms/" + ID_ENTIDAD + "/log";
	
	public static final String LIGHTS = "/lights/list";
	public static final String ACTIVATE_LIGHT =  "/lights/" + ID_ENTIDAD + "/" + ID_LUZ + "/activate";
	public static final String DEACTIVATE_LIGHT =  "/lights/" + ID_ENTIDAD + "/" + ID_LUZ + "/deactivate";
	
	public static final String ACTIVATE_RANDOM_LIGHT =  "/lights/" + ID_ENTIDAD + "/" + ID_LUZ + "/randomOn";
	public static final String DEACTIVATE_RANDOM_LIGHT =  "/lights/" + ID_ENTIDAD + "/" + ID_LUZ + "/randomOff";
	
	
	public static final String LOG_LIGHT =  "/lights/" + ID_ENTIDAD + "/" + ID_LUZ + "/log";
	
	public static final String CAMERAS = "/cameras/list";
	
	public static final String VLU_MESSAGES = "/cars/vluMessagesNoPrevent";
}

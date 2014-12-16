package com.tdil.thalamus.android.rest.client;

import com.tdil.thalamus.android.ApplicationConfig;

public interface RESTConstants {

	public static final String ID_ENTIDAD = "{idEntidad}";
	public static final String ID_LUZ = "{idLuz}";
	public static final String ID_AGENDA = "{idAgenda}";
	
	public static final String P_PASSWORD = "{password}";

	public static final String P_DOCUMENT_NUMBER = "{documentNumber}";

	public static final String P_DOCUMENT_TYPE = "{documentType}";
	
	public static final String P_POI_TYPES = "{types}";
	
	public static final String P_VEHICLE = "{vehicleId}";
	public static final String P_SECURE_ZONE_ID = "{secureZoneId}";
	public static final String P_SPEED_LIMIT_ID = "{speedLimitId}";
	public static final String P_DATE_START = "{dateStart}";
	public static final String P_DATE_END = "{dateEnd}";
	
	public static final String P_NOTIFICATION_ID = "{notificationId}";
	public static final String P_DOMAIN = "{domain}";

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
	
	// Agendas
	public static final String GET_ALARM_AGENDAS =  "/alarmAgendas/" + ID_ENTIDAD + "/list";
	public static final String POST_ACTIVATE_ALARM_AGENDA =  "/alarmAgendas/" + ID_ENTIDAD + "/" + ID_AGENDA + "/activate";
	public static final String POST_DELETE_ALARM_AGENDA =  "/alarmAgendas/" + ID_ENTIDAD + "/" + ID_AGENDA + "/delete";
	public static final String POST_CREATE_ALARM_AGENDA =  "/alarmAgendas/" + ID_ENTIDAD + "/create";
	public static final String POST_MODIFY_ALARM_AGENDA =  "/alarmAgendas/" + ID_ENTIDAD + "/modify";
	public static final String GET_LIGHT_AGENDAS =  "/lightAgendas/" + ID_ENTIDAD + "/" + ID_LUZ + "/list";
	public static final String POST_ACTIVATE_LIGHT_AGENDA =  "/lightAgendas/" + ID_ENTIDAD + "/" + ID_LUZ + "/" + ID_AGENDA + "/activate";
	public static final String POST_DELETE_LIGHT_AGENDA =  "/lightAgendas/" + ID_ENTIDAD + "/" + ID_LUZ + "/" + ID_AGENDA + "/delete";
	public static final String POST_CREATE_LIGHT_AGENDA =  "/lightAgendas/" + ID_ENTIDAD + "/" + ID_LUZ + "/create";
	public static final String POST_MODIFY_LIGHT_AGENDA =  "/lightAgendas/" + ID_ENTIDAD + "/" + ID_LUZ + "/modify";
	
	public static final String CAMERAS = "/cameras/list";
	
	public static final String VLU_MESSAGES = "/cars/vluMessagesNoPrevent";
	
	public static final String POIS = "/pois/search/" + P_POI_TYPES;
	
	/** Prevent */
	public static final String GET_VEHICLES = "/prevent/vehicles";
	public static final String GET_VEHICLE_SECURE_ZONES = "/prevent/" + P_VEHICLE + "/secureZones";
	public static final String POST_VEHICLE_SECURE_ZONE = "/prevent/" + P_VEHICLE + "/secureZone/" + P_SECURE_ZONE_ID;
	
	public static final String GET_VEHICLE_SPEED_LIMITS = "/prevent/" + P_VEHICLE + "/speedLimits";
	public static final String POST_VEHICLE_SPEED_LIMITS = "/prevent/" + P_VEHICLE + "/speedLimit/" + P_SPEED_LIMIT_ID;
	
	public static final String GET_VEHICLE_PHONES = "/prevent/" + P_VEHICLE + "/phoneNumbers";
	public static final String POST_VEHICLE_PHONES = "/prevent/" + P_VEHICLE + "/phoneNumber";
	
	public static final String GET_VEHICLE_PATH = "/prevent/" + P_VEHICLE + "/path/" + P_DATE_START + "/" + P_DATE_END;
	
	/** Parked Mode */
	public static String P_PM_PHONE = "{phoneNumber}";
	public static String P_PM_TYPE = "{type}";
	public static String P_PM_RECORDS = "{records}";
	public static final String GET_PM_VEHICLES = "/parkedmode/vehicles";
	public static final String POST_ACTIVATE_PM = "/parkedmode/activateParkedMode/" + P_VEHICLE;
	public static final String POST_DEACTIVATE_PM = "/parkedmode/deactivateParkedMode/" + P_VEHICLE;
	public static final String GET_PM_VEHICLE_CONF = "/parkedmode/getConfiguration/" + P_VEHICLE + "/" + P_DOMAIN;
	public static final String POST_PM_VEHICLE_CONF = "/parkedmode/setConfiguration/" + P_VEHICLE + "/" + P_PM_PHONE + "/" + P_PM_TYPE;
	public static final String GET_PM_VEHICLE_LOG = "/parkedmode/history/" + P_VEHICLE + "/" + P_PM_RECORDS;
	
	/** Notifications */
	public static final String GET_NOTIFICATIONS = "/notifications/list";
	public static final String GET_NOTIFICATIONS_SUMMARY = "/notifications/summary";
	public static final String POST_DISMISS_NOTIFICATION = "/notifications/dismiss/" + P_NOTIFICATION_ID;
	public static final String POST_DISMISS_ALL_NOTIFICATIONs = "/notifications/dismissAll";
	public static final String POST_REG_ID = "/notifications/registerAndroidId";
	
	public static final String GET_NOTIFICATION_CONF = "/notifications/configuration";
	public static final String POST_NOTIFICATION_CONF = "/notifications/configuration";
}

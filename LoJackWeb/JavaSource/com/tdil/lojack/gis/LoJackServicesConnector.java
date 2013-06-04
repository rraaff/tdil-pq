package com.tdil.lojack.gis;

import java.util.Collection;
import java.util.Date;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.gis.model.AlarmAlertConfiguration;
import com.tdil.lojack.gis.model.AsyncJobConstants.AsyncJobActions;
import com.tdil.lojack.gis.model.AsyncJobConstants.AsyncJobStatus;
import com.tdil.lojack.gis.model.AsyncJobResponse;
import com.tdil.lojack.gis.model.Camera;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.gis.model.JobStatus;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.gis.model.LightAgenda;
import com.tdil.lojack.gis.model.LightAlertConfiguration;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.lojack.utils.LoJackConfig;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

// TODO ver que datos de authenticacion hay que proveer
public class LoJackServicesConnector {

	private static final String GUID = "guid";
	private static final String HOME_USER_ID = "lojackUserId";
	
	private static int TIMEOUT = 2000;
	
	//private static final String LOJACK_USER_ID = "lojackUserId";
	private static final String TZ_OFFSET = "timezoneOffset";
	private static final String TZ_NAME = "timezoneName";
	private static final String PASSWORD = "password";
	private static final String ID_ENTIDAD = "idEntidad";
	private static final String ID_LUZ = "idLuz";
	private static final String JOB_ID = "jobId";
	private static final String AGENDA_ID = "agendaId";
	
	private static final String INT_PAGE_SIZE = "intPageSize";

	private static final String RECEIVE_NOTIFICATION = "receiveNotification";

	public static final Logger LOG = LoggerProvider.getLogger(LoJackServicesConnector.class);

	private static MiddlewareProtocol protocol = new JSONProtocol();
	
	private static String gisServer = "http://localhost:8180/GISWeb/";
	private static String servicesServer = "http://localhost:8180/GISWeb/";

	// Jobs
	private static final String get_History_Job_Status = "getHistoryJobStatus.json";

	// Alarmas GIS
	private static final String GET_ALARMS = "getAlarms.json";
	private static final String SEND_PANIC = "sendPanic.json";
	private static final String ACTIVATE_ALARM = "activateAlarm.json";
	private static final String DEACTIVATE_ALARM = "deactivateAlarm.json";
	private static final String GET_ALARM_LOG = "getAlarmLog.json";

	// Alarmas Services
	private static final String GET_ALARM_AGENDAS = "getAlarmAgendas.json";
	private static final String DELETE_ALARM_AGENDA = "deleteAlarmAgenda.json";
	private static final String ACTIVATE_ALARM_AGENDA = "activateAlarmAgenda.json";
	private static final String ADD_ALARM_AGENDA = "addAlarmAgenda.json";
	private static final String SAVE_ALARM_AGENDA = "saveAlarmAgenda.json";

	private static final String GET_ALARM_ALERT_CONFIGURATION = "getAlarmAlertConfiguration.json";
	private static final String SAVE_ALARM_ALERT_CONFIGURATION = "saveAlarmAlertConfiguration.json";

	// Camara
	private static final String GET_CAMERA = "getCamera.json";

	// Luces GIS
	private static final String GET_LIGHTS = "getLights.json";
	private static final String GET_LIGHT_LOG = "getLightLog.json";

	private static final String TURN_ON_LIGHT = "turnOnLight.json";
	private static final String TURN_OFF_LIGHT = "turnOffLight.json";


	private static final String ACTIVATE_LIGHT_RANDOM_SEQUENCE = "activateLightRandomSequence.json";
	private static final String DEACTIVATE_LIGHT_RANDOM_SEQUENCE = "deactivateLightRandomSequence.json";

	// Luces Services
	private static final String GET_LIGHT_AGENDAS = "getLightAgendas.json";
	private static final String DELETE_LIGHT_AGENDA = "deleteLightAgenda.json";
	private static final String ACTIVATE_LIGHT_AGENDA = "activateLightAgenda.json";
	private static final String ADD_LIGHT_AGENDA = "addLightAgenda.json";
	private static final String SAVE_LIGHT_AGENDA = "saveLightAgenda.json";

	private static final String GET_LIGHT_ALERT_CONFIGURATION = "getLightAlertConfiguration.json";
	private static final String SAVE_LIGHT_ALERT_CONFIGURATION = "saveLightAlertConfiguration.json";

	public static Collection<Alarm> getAlarms(WebsiteUser user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		try {
			//JSONResponse response = executeTranslation("", jsonObject, GET_ALARMS);
			JSONResponse response = executeGIS(jsonObject, GET_ALARMS);
			Collection<Alarm> resultObj = (Collection<Alarm>)JSONArray.toCollection((JSONArray)response.getResult(), Alarm.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	public static AsyncJobResponse sendPanicSignal(WebsiteUser user, int idEntidad) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(RECEIVE_NOTIFICATION, WebsiteUserUtils.wantsNotification(user.getModelUser(), idEntidad));
		try {
			JSONResponse response = executeGIS(jsonObject, SEND_PANIC);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, AsyncJobActions.PANIC_ALARM, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}
	}

	public static AsyncJobResponse activateAlarm(WebsiteUser user, int idEntidad) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(RECEIVE_NOTIFICATION, WebsiteUserUtils.wantsNotification(user.getModelUser(), idEntidad));
		try {
			JSONResponse response = executeGIS(jsonObject, ACTIVATE_ALARM);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, AsyncJobActions.ACTIVATE_ALARM, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}
	}
	public static AsyncJobResponse deactivateAlarm(WebsiteUser user, int idEntidad) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(RECEIVE_NOTIFICATION, WebsiteUserUtils.wantsNotification(user.getModelUser(), idEntidad));
		try {
			JSONResponse response = executeGIS(jsonObject, DEACTIVATE_ALARM);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, AsyncJobActions.DEACTIVATE_ALARM, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}

	}

	public static Collection<ChangeLog> getAlarmLog(WebsiteUser user, int idEntidad) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(INT_PAGE_SIZE, 10);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_ALARM_LOG);
			Collection<ChangeLog> resultObj = (Collection<ChangeLog>)JSONArray.toCollection((JSONArray)response.getResult(), ChangeLog.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@Deprecated
	public static AlarmAlertConfiguration getAlarmAlertConfiguration(String userId, String alarmId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, userId);
		try {
			JSONResponse response = executeService(jsonObject, GET_ALARM_ALERT_CONFIGURATION);
			JSONObject object = (JSONObject)response.getResult();
			AlarmAlertConfiguration configuration = (AlarmAlertConfiguration)JSONObject.toBean(object, AlarmAlertConfiguration.class);
			return configuration;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	@Deprecated
	public static boolean saveAlarmAlertConfiguration(String userId, AlarmAlertConfiguration conf) {
		JSONObject jsonObject = JSONObject.fromObject(conf);
		jsonObject.put(GUID, userId);
		try {
			JSONResponse response = executeService(jsonObject, SAVE_ALARM_ALERT_CONFIGURATION);
			JSONObject object = (JSONObject)response.getResult();
			if (object != null) {
				if (object.containsKey("result")) {
					return object.getBoolean("result");
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static Collection<AlarmAgenda> getAlarmAgendas(WebsiteUser user, int idEntidad) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		try {
			JSONResponse response = executeService(jsonObject, GET_ALARM_AGENDAS);
			Collection<AlarmAgenda> resultObj = (Collection<AlarmAgenda>)JSONArray.toCollection((JSONArray)response.getResult(), AlarmAgenda.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static boolean deleteAlarmAgenda(WebsiteUser user, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(AGENDA_ID, agendaId);
		try {
			JSONResponse response = executeService(jsonObject, DELETE_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean activateAlarmAgenda(WebsiteUser user, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(AGENDA_ID, agendaId);
		try {
			JSONResponse response = executeService(jsonObject, ACTIVATE_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean addAlarmAgenda(WebsiteUser user, int idEntidad, AlarmAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		try {
			JSONResponse response = executeService(jsonObject, ADD_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean saveAlarmAgenda(WebsiteUser user, AlarmAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		try {
			JSONResponse response = executeService(jsonObject, SAVE_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static Collection<Camera> getCameras(WebsiteUser user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		try {
			JSONResponse response = executeGIS(jsonObject, GET_CAMERA);
			Collection<Camera> resultObj = (Collection<Camera>)JSONArray.toCollection((JSONArray)response.getResult(), Camera.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static Collection<Light> getLights(WebsiteUser user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		try {
			JSONResponse response = executeGIS(jsonObject, GET_LIGHTS);
			Collection<Light> resultObj = (Collection<Light>)JSONArray.toCollection((JSONArray)response.getResult(), Light.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static AsyncJobResponse activateLightRandomSequence(WebsiteUser user, int idEntidad, int idLuz) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(ID_LUZ, idLuz);
		try {
			JSONResponse response = executeGIS(jsonObject, ACTIVATE_LIGHT_RANDOM_SEQUENCE);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, idLuz, AsyncJobActions.ACTIVATE_RANDOM_LIGHT, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}
	}

	public static AsyncJobResponse deactivateLightRandomSequence(WebsiteUser user, int idEntidad, int idLuz) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(ID_LUZ, idLuz);
		try {
			JSONResponse response = executeGIS(jsonObject, DEACTIVATE_LIGHT_RANDOM_SEQUENCE);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, idLuz, AsyncJobActions.DEACTIVATE_RANDOM_LIGHT, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}
	}

	public static AsyncJobResponse activateLight(WebsiteUser user, int idEntidad, int idLuz) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(ID_LUZ, idLuz);
		try {
			JSONResponse response = executeGIS(jsonObject, TURN_ON_LIGHT);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, idLuz, AsyncJobActions.TURN_ON_LIGHT, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}
	}
	public static AsyncJobResponse deactivateLight(WebsiteUser user, int idEntidad, int idLuz) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(ID_LUZ, idLuz);
		try {
			JSONResponse response = executeGIS(jsonObject, TURN_OFF_LIGHT);
			AsyncJobResponse result = new AsyncJobResponse((JSONObject)response.getResult());
			registerNewJob(user, idEntidad, idLuz, AsyncJobActions.TURN_OFF_LIGHT, result);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AsyncJobResponse.ERROR;
		}
	}

	public static Collection<ChangeLog> getLightLog(WebsiteUser user, int idEntidad, int idLuz) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(ID_LUZ, idLuz);
		jsonObject.put(INT_PAGE_SIZE, 10);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_LIGHT_LOG);
			Collection<ChangeLog> resultObj = (Collection<ChangeLog>)JSONArray.toCollection((JSONArray)response.getResult(), ChangeLog.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@Deprecated
	public static LightAlertConfiguration getLightAlertConfiguration(String userId, String lightId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, userId);
		jsonObject.put(ID_LUZ, lightId);
		try {
			JSONResponse response = executeService(jsonObject, GET_LIGHT_ALERT_CONFIGURATION);
			JSONObject object = (JSONObject)response.getResult();
			LightAlertConfiguration configuration = (LightAlertConfiguration)JSONObject.toBean(object, LightAlertConfiguration.class);
			return configuration;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@Deprecated
	public static boolean saveLightAlertConfiguration(String userId, LightAlertConfiguration conf) {
		JSONObject jsonObject = JSONObject.fromObject(conf);
		jsonObject.put(GUID, userId);
		try {
			JSONResponse response = executeService(jsonObject, SAVE_LIGHT_ALERT_CONFIGURATION);
			JSONObject object = (JSONObject)response.getResult();
			if (object != null) {
				if (object.containsKey("result")) {
					return object.getBoolean("result");
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static Collection<LightAgenda> getLightAgendas(WebsiteUser user, int idEntidad, int idLuz) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_ENTIDAD, idEntidad);
		jsonObject.put(ID_LUZ, idLuz);
		try {
			JSONResponse response = executeService(jsonObject, GET_LIGHT_AGENDAS);
			Collection<LightAgenda> resultObj = (Collection<LightAgenda>)JSONArray.toCollection((JSONArray)response.getResult(), LightAgenda.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static boolean deleteLightAgenda(WebsiteUser user, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(AGENDA_ID, agendaId);
		try {
			JSONResponse response = executeService(jsonObject, DELETE_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean activateLightAgenda(WebsiteUser user, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(AGENDA_ID, agendaId);
		try {
			JSONResponse response = executeService(jsonObject, ACTIVATE_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean addLightAgenda(WebsiteUser user, int idLuz, LightAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(ID_LUZ, idLuz);
		try {
			JSONResponse response = executeService(jsonObject, ADD_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean saveLightAgenda(WebsiteUser user, LightAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		try {
			JSONResponse response = executeService(jsonObject, SAVE_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static Collection<JobStatus> getHistoryJobStatus(WebsiteUser user, int jobId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GUID, LoJackConfig.getGUID());
		jsonObject.put(HOME_USER_ID, user.getHomeUserId());
		jsonObject.put(JOB_ID, jobId);
		try {
			JSONResponse response = executeGIS(jsonObject, get_History_Job_Status);
			Collection<JobStatus> resultObj = (Collection<JobStatus>)JSONArray.toCollection((JSONArray)response.getResult(), JobStatus.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private static void registerNewJob(WebsiteUser user, int idEntidad, int action, AsyncJobResponse result) {
		if (result.getJobId() != 0) {
			AsyncJob asyncJob = new AsyncJob();
			asyncJob.setIdwebsiteuser(user.getModelUser().getId());
			asyncJob.setAction(action);
			asyncJob.setIdjob(result.getJobId());
			asyncJob.setIdentidad(idEntidad);
			asyncJob.setIdluz(0);
			asyncJob.setPostdate(new Date());
			asyncJob.setStatus(AsyncJobStatus.INITIAL);
			asyncJob.setDeleted(0);
			user.addAsyncJob(asyncJob);
			/*
			try {
				// TODO no persisto si no hace falta AsyncJobUtils.registerNewJob(user.getModelUser().getId(), action, result.getJobId(), idEntidad, 0);
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e);
			}*/
		}
	}

	private static void registerNewJob(WebsiteUser user, int idEntidad, int idLuz, int action, AsyncJobResponse result) {
		if (result.getJobId() != 0) {
			AsyncJob asyncJob = new AsyncJob();
			asyncJob.setIdwebsiteuser(user.getModelUser().getId());
			asyncJob.setAction(action);
			asyncJob.setIdjob(result.getJobId());
			asyncJob.setIdentidad(idEntidad);
			asyncJob.setIdluz(idLuz);
			asyncJob.setPostdate(new Date());
			asyncJob.setStatus(AsyncJobStatus.INITIAL);
			asyncJob.setDeleted(0);
			user.addAsyncJob(asyncJob);
			/*
			try {
				// TODO no persisto si no hace falta AsyncJobUtils.registerNewJob(user.getModelUser().getId(), action, result.getJobId(), idEntidad, 0);
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e);
			}*/
		}
	}

	public static String getGisServer() {
		return gisServer;
	}
	public static void setGisServer(String gisServer) {
		LoJackServicesConnector.gisServer = gisServer;
	}

	public static JSONResponse executeGIS(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return getProtocol().execute(gisServer, json, service);
	}

	public static JSONResponse executeService(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return getProtocol().execute(servicesServer, json, service);
	}

	public static void configureTimeout(HttpClient client) {
		HttpConnectionManager connectionManager = client.getHttpConnectionManager();
		connectionManager.getParams().setSoTimeout(getTIMEOUT());
	}
	public static JSON extractJSONObjectResponse(String response)
			throws InvalidResponseException, UnauthorizedException {
		JSONTokener tokener = new JSONTokener(response);
		Object rawResponseMessage = tokener.nextValue();
		if (rawResponseMessage == null) {
			throw new InvalidResponseException();
		}
		return (JSON)rawResponseMessage;
	}
	public static String getServicesServer() {
		return servicesServer;
	}
	public static void setServicesServer(String servicesServer) {
		LoJackServicesConnector.servicesServer = servicesServer;
	}
	public static int getTIMEOUT() {
		return TIMEOUT;
	}
	public static void setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
	}
	public static MiddlewareProtocol getProtocol() {
		return protocol;
	}
	public static void setProtocol(MiddlewareProtocol protocol) {
		LoJackServicesConnector.protocol = protocol;
	}

}

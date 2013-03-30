package com.tdil.lojack.gis;

import java.io.IOException;
import java.util.Collection;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.gis.model.AlarmAlertConfiguration;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.gis.model.LightAgenda;
import com.tdil.lojack.gis.model.LightAlertConfiguration;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;

// TODO ver que datos de autheticacion hay que proveer
public class LoJackServicesConnector {
	
	private static final Logger LOG = LoggerProvider.getLogger(LoJackServicesConnector.class);
	
	private static String gisServer = "http://localhost:8180/GISWeb/";
	private static String servicesServer = "http://localhost:8180/GISWeb/";
	
	// Alarmas GIS
	private static final String GET_ALARMS = "getAlarms.json";
	private static final String SEND_PANIC = "sendPanic.json";
	private static final String ACTIVATE_ALARM = "activateAlarm.json";
	private static final String DEACTIVATE_ALARM = "deactivateAlarm.json";
	private static final String GET_ALARM_LOG = "getAlarmLog.json";
	private static final String CHANGE_ALARM_PASSWORD = "changeAlarmPassword.json";

	// Alarmas Services
	private static final String GET_ALARM_AGENDAS = "getAlarmAgendas.json";
	private static final String DELETE_ALARM_AGENDA = "deleteAlarmAgenda.json";
	private static final String ACTIVATE_ALARM_AGENDA = "activateAlarmAgenda.json";
	private static final String ADD_ALARM_AGENDA = "addAlarmAgenda.json";
	private static final String SAVE_ALARM_AGENDA = "saveAlarmAgenda.json";
	
	private static final String GET_ALARM_ALERT_CONFIGURATION = "getAlarmAlertConfiguration.json";
	private static final String SAVE_ALARM_ALERT_CONFIGURATION = "saveAlarmAlertConfiguration.json";
	
	// Luces GIS
	private static final String GET_LIGHTS = "getLights.json";
	private static final String GET_LIGHT_LOG = "getLightLog.json";
	
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
	
	public static Collection<Alarm> getAlarms(String userId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_ALARMS);
			Collection<Alarm> resultObj = (Collection<Alarm>)JSONArray.toCollection((JSONArray)response.getResult(), Alarm.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	public static boolean sendPanicSignal(String userId, String alarmId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("alarmId", alarmId);
		try {
			JSONResponse response = executeGIS(jsonObject, SEND_PANIC);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	public static boolean activateAlarm(String userId, String alarmId, String password) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("alarmId", alarmId);
		jsonObject.put("password", password);
		try {
			JSONResponse response = executeGIS(jsonObject, ACTIVATE_ALARM);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	public static boolean deactivateAlarm(String userId,String alarmId, String password) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("alarmId", alarmId);
		jsonObject.put("password", password);
		try {
			JSONResponse response = executeGIS(jsonObject, DEACTIVATE_ALARM);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
		
	}
	public static Collection<ChangeLog> getAlarmLog(String userId, String alarmId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("alarmId", alarmId);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_ALARM_LOG);
			Collection<ChangeLog> resultObj = (Collection<ChangeLog>)JSONArray.toCollection((JSONArray)response.getResult(), ChangeLog.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static AlarmAlertConfiguration getAlarmAlertConfiguration(String userId, String alarmId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("alarmId", alarmId);
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
	
	public static boolean saveAlarmAlertConfiguration(String userId, AlarmAlertConfiguration conf) {
		JSONObject jsonObject = JSONObject.fromObject(conf);
		jsonObject.put("guid", userId);
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
	
	public static boolean changeAlarmPassword(String userId, String alarmId, String newPassword) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("alarmId", alarmId);
		jsonObject.put("newPassword", newPassword);
		try {
			JSONResponse response = executeGIS(jsonObject, CHANGE_ALARM_PASSWORD);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static Collection<AlarmAgenda> getAlarmAgendas(String userId, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("agendaId", agendaId);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_ALARM_AGENDAS);
			Collection<AlarmAgenda> resultObj = (Collection<AlarmAgenda>)JSONArray.toCollection((JSONArray)response.getResult(), AlarmAgenda.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static boolean deleteAlarmAgenda(String userId, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("agendaId", agendaId);
		try {
			JSONResponse response = executeGIS(jsonObject, DELETE_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean activateAlarmAgenda(String userId, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("agendaId", agendaId);
		try {
			JSONResponse response = executeGIS(jsonObject, ACTIVATE_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean addAlarmAgenda(String userId, AlarmAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put("guid", userId);
		try {
			JSONResponse response = executeService(jsonObject, ADD_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean saveAlarmAgenda(String userId, AlarmAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put("guid", userId);
		try {
			JSONResponse response = executeService(jsonObject, SAVE_ALARM_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static Collection<Light> getLights(String userId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_LIGHTS);
			Collection<Light> resultObj = (Collection<Light>)JSONArray.toCollection((JSONArray)response.getResult(), Light.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static boolean activateLightRandomSequence(String userId, String lightId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("lightId", lightId);
		try {
			JSONResponse response = executeGIS(jsonObject, ACTIVATE_LIGHT_RANDOM_SEQUENCE);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean deactivateLightRandomSequence(String userId, String lightId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("lightId", lightId);
		try {
			JSONResponse response = executeGIS(jsonObject, DEACTIVATE_LIGHT_RANDOM_SEQUENCE);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean activateLight(Light light, String password) {
		return false;
	}
	public static boolean deactivateLight(Light light, String password) {
		return false;
	}
	public static Collection<ChangeLog> getLightLog(String userId, String lightId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("lightId", lightId);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_LIGHT_LOG);
			Collection<ChangeLog> resultObj = (Collection<ChangeLog>)JSONArray.toCollection((JSONArray)response.getResult(), ChangeLog.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static LightAlertConfiguration getLightAlertConfiguration(String userId, String lightId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("lightId", lightId);
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
	
	public static boolean saveLightAlertConfiguration(String userId, LightAlertConfiguration conf) {
		JSONObject jsonObject = JSONObject.fromObject(conf);
		jsonObject.put("guid", userId);
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
	
	public static boolean changeLightPassword(Light light, String newPassword) {
		return false;
	}
	
	public static Collection<LightAgenda> getLightAgendas(String userId, String lightId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("lightId", lightId);
		try {
			JSONResponse response = executeGIS(jsonObject, GET_LIGHT_AGENDAS);
			Collection<LightAgenda> resultObj = (Collection<LightAgenda>)JSONArray.toCollection((JSONArray)response.getResult(), LightAgenda.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static boolean deleteLightAgenda(String userId, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("agendaId", agendaId);
		try {
			JSONResponse response = executeGIS(jsonObject, DELETE_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean activateLightAgenda(String userId, String agendaId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guid", userId);
		jsonObject.put("agendaId", agendaId);
		try {
			JSONResponse response = executeGIS(jsonObject, ACTIVATE_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean addLightAgenda(String userId, LightAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put("guid", userId);
		try {
			JSONResponse response = executeService(jsonObject, ADD_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean saveLightAgenda(String userId, LightAgenda alarmAgenda) {
		JSONObject jsonObject = JSONObject.fromObject(alarmAgenda);
		jsonObject.put("guid", userId);
		try {
			JSONResponse response = executeService(jsonObject, SAVE_LIGHT_AGENDA);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static String getGisServer() {
		return gisServer;
	}
	public static void setGisServer(String gisServer) {
		LoJackServicesConnector.gisServer = gisServer;
	}
	
	public static JSONResponse executeGIS(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return execute(gisServer, json, service);
	}
	
	public static JSONResponse executeService(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return execute(servicesServer, json, service);
	}
	
	private static JSONResponse execute(String server, JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute POST to: " + service + " json " + (json == null ? "null" : json));
		}
		HttpClient client = new HttpClient();
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server + service);
		try {
			httpMethod.setRequestHeader("Content-type", "application/json");
			if (json != null) {
				RequestEntity requestEntity = new StringRequestEntity(json.toString());
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			JSON result = extractJSONObjectResponse(response);
			if (LOG.isDebugEnabled()) {
				LOG.debug("POST result is: " + result);
			}
			return new JSONResponse(result, statusCode);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}

	private static JSON extractJSONObjectResponse(String response)
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
	
}

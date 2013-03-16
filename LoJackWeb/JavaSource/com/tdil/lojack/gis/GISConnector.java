package com.tdil.lojack.gis;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

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
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.gis.model.Light;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;
import com.tdil.thalamus.client.facade.json.beans.BrandBean;

// TODO ver que datos de autheticacion hay que proveer
public class GISConnector {
	
	private static final Logger LOG = LoggerProvider.getLogger(GISConnector.class);
	
	private static String gisServer = "http://localhost:8180/GISWeb/";
	
	private static final String GET_ALARMS = "getAlarms.json";
	private static final String SEND_PANC = "sendPanic.json";
	private static final String ACTIVATE_ALARM = "activateAlarm.json";
	private static final String DEACTIVATE_ALARM = "deactivateAlarm.json";
	private static final String GET_ALARM_LOG = "getAlarmLog.json";
	private static final String CHANGE_ALARM_PASSWORD = "changeAlarmPassword.json";

	public static Collection<Alarm> getAlarms(String userId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", userId);
		try {
			GISResponse response = execute(jsonObject, GET_ALARMS);
			Collection<Alarm> resultObj = (Collection<Alarm>)JSONArray.toCollection((JSONArray)response.getResult(), Alarm.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	public static boolean sendPanicSignal(String alarmId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("alarmId", alarmId);
		try {
			GISResponse response = execute(jsonObject, SEND_PANC);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	public static boolean activateAlarm(String alarmId, String password) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("alarmId", alarmId);
		jsonObject.put("password", password);
		try {
			GISResponse response = execute(jsonObject, ACTIVATE_ALARM);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	public static boolean deactivateAlarm(String alarmId, String password) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("alarmId", alarmId);
		jsonObject.put("password", password);
		try {
			GISResponse response = execute(jsonObject, DEACTIVATE_ALARM);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
		
	}
	public static Collection<ChangeLog> getAlarmLog(String alarmId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("alarmId", alarmId);
		try {
			GISResponse response = execute(jsonObject, GET_ALARM_LOG);
			Collection<ChangeLog> resultObj = (Collection<ChangeLog>)JSONArray.toCollection((JSONArray)response.getResult(), ChangeLog.class);
			return resultObj;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	public static boolean changeAlarmPassword(String alarmId, String newPassword) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("alarmId", alarmId);
		jsonObject.put("newPassword", newPassword);
		try {
			GISResponse response = execute(jsonObject, CHANGE_ALARM_PASSWORD);
			return ((JSONObject)response.getResult()).getBoolean("result");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static List<Light> getLights(String userId) {
		return null;
	}
	public static boolean activateLight(Light light, String password) {
		return false;
	}
	public static boolean deactivateLight(Light light, String password) {
		return false;
	}
	public static Collection<ChangeLog> getLightLog(Light light) {
		return null;
	}
	public static boolean changeLightPassword(Light light, String newPassword) {
		return false;
	}
	public static String getGisServer() {
		return gisServer;
	}
	public static void setGisServer(String gisServer) {
		GISConnector.gisServer = gisServer;
	}
	
	public static GISResponse execute(JSON json, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute POST to: " + service + " json " + (json == null ? "null" : json));
		}
		HttpClient client = new HttpClient();
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(gisServer + service);
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
			return new GISResponse(result, statusCode);
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
	
}

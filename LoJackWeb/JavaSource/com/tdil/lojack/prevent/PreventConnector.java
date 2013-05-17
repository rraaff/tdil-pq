package com.tdil.lojack.prevent;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.lojack.prevent.model.SecureZone;
import com.tdil.lojack.prevent.model.SpeedLimit;
import com.tdil.lojack.prevent.model.UpdatePhoneNumbers;
import com.tdil.lojack.prevent.model.UserLogin;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;

public class PreventConnector {



	// POST
	private static final String LOGIN = "/Users/Login";

	// GET
	private static final String VEHICLES = "/Vehicles/?index={index}&userToken={userToken}";
	private static final String VEHICLE_GET_SECURE_ZONES = "/Vehicles/{vehicleID}/SecureZones/?userToken={userToken}";
	private static final String VEHICLE_SET_SECURE_ZONE = "/SecureZone/?secureZoneID={secureZoneID}&vehicleID={vehicleID}&userToken={userToken}";

	private static final String VEHICLE_GET_SPEED_LIMIT = "/Vehicles/{vehicleID}/SpeedLimit/?userToken={userToken}";
	private static final String VEHICLE_SET_SPEED_LIMIT = "/SpeedLimit/?SpeedLimitID={speedLimitID}&VehicleID={vehicleID}&userToken={userToken}";
	private static final String VEHICLE_GET_PHONES = "/Vehicles/{vehicleID}/PhoneNumbers/?userToken={userToken}";
	// POST
	private static final String VEHICLE_SET_PHONES = "/{userToken}/Vehicles/{vehicleID}/PhoneNumbers";
	// get
	private static final String VEHICLE_GET_SAT_POSITION = "/Vehicles/{vehicleID}/SatellitePosition/?userToken={userToken}";
	private static final String FLOT_GET_SAT_POSITION = "/Vehicles/SatellitePositions/?userToken={userToken}";


	private static String preventServer = "http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc";

	private static final Logger LOG = LoggerProvider.getLogger(PreventConnector.class);

	public static final String index = "{index}";
	public static final String userToken = "{userToken}";
	public static final String vehicleID = "{vehicleID}";
	public static final String secureZoneID = "{secureZoneID}";
	public static final String speedLimitID = "{speedLimitID}";

	@Deprecated
	public static LoginResponse getLogin() {
		try {
			UserLogin userLogin = new UserLogin();
			userLogin.setUser("Prevent2");
			userLogin.setPassword("1234");
			XMLResponse resp = PreventConnector.login(userLogin);
			return(LoginResponse)resp.getResult();
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static XMLResponse login(UserLogin userLogin) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executePost(getPreventServer(), LOGIN, userLogin, null);
	}

	public static XMLResponse getVehicles(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLES, params);
	}

	public static XMLResponse getVehicleSecureZones(LoginResponse loginResponse, Vehicle vehicle) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_SECURE_ZONES, new URLParams(loginResponse).vehicleID(vehicle.getId()));
	}

	public static XMLResponse getVehicleSecureZones(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_SECURE_ZONES, params);
	}

	public static XMLResponse setVehicleSecureZone(LoginResponse loginResponse, Vehicle vehicle, SecureZone sl) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_SET_SECURE_ZONE, new URLParams(loginResponse).vehicleID(vehicle.getId()).secureZoneID(sl == null ? "0" : sl.getId()));
	}

	public static XMLResponse getVehicleSpeedLimit(LoginResponse loginResponse, Vehicle vehicle) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_SPEED_LIMIT, new URLParams(loginResponse).vehicleID(vehicle.getId()));
	}

	public static XMLResponse getVehicleSpeedLimit(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_SPEED_LIMIT, params);
	}

	public static XMLResponse setVehicleSpeedLimit(LoginResponse loginResponse, Vehicle vehicle, SpeedLimit sl) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_SET_SPEED_LIMIT, new URLParams(loginResponse).vehicleID(vehicle.getId()).speedLimitID(sl == null ? "0" : sl.getId()));
	}

	public static XMLResponse setVehicleSpeedLimit(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_SET_SPEED_LIMIT, params);
	}

	public static XMLResponse getVehiclePhones(LoginResponse loginResponse, Vehicle vehicle) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_PHONES, new URLParams(loginResponse).vehicleID(vehicle.getId()));
	}

	public static XMLResponse getVehiclePhones(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_PHONES, params);
	}

	public static XMLResponse setVehiclePhones(LoginResponse loginResponse, UpdatePhoneNumbers phoneNumbers) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executePost(getPreventServer(), VEHICLE_SET_PHONES, PreventXMLUtils.asXMLUpdatePhone(phoneNumbers), new URLParams(loginResponse).vehicleID(phoneNumbers.getVehicleID()));
	}

	public static XMLResponse setVehiclePhones(Object body, URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executePost(getPreventServer(), VEHICLE_SET_PHONES, body, params);
	}

	public static XMLResponse getVehicleSatPosition(LoginResponse loginResponse, Vehicle vehicle) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_SAT_POSITION, new URLParams(loginResponse).vehicleID(vehicle.getId()));
	}

	public static XMLResponse getVehicleSatPosition(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), VEHICLE_GET_SAT_POSITION, params);
	}

	public static XMLResponse getFlotSatPosition(LoginResponse loginResponse) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), FLOT_GET_SAT_POSITION, new URLParams(loginResponse));
	}

	public static XMLResponse getFlotSatPosition(URLParams params) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return executeGet(getPreventServer(), FLOT_GET_SAT_POSITION, params);
	}

	private static XMLResponse executeGet(String server, String service, URLParams replacements) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		long start = System.currentTimeMillis();
		String url = service;
		for (Map.Entry<String, String> entries : replacements.getParams().entrySet()) {
			url = url.replace(entries.getKey(), entries.getValue());
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute: " + server + url);
		}
		HttpClient client = new HttpClient();
		GetMethod httpMethod = new GetMethod(server + url);
		try {
			httpMethod.setRequestHeader("Content-type", "text/xml");
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Remote result is: " + response + " with status: " + statusCode);
			}
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("Remote result for " + (service) + " status: " + statusCode + " and body: " + (response == null ? "null" : response));
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			Object result = PreventXMLUtils.fromXML(response);
			return new XMLResponse(result, statusCode);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			if (LOG.isInfoEnabled()) {
				long end = System.currentTimeMillis();
				LOG.info("Execute: " + url + " took " + (end - start) + " millis");
			}
		}
	}

	private static XMLResponse executePost(String server, String service, Object param, URLParams urlParams) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		long start = System.currentTimeMillis();
		String xml = null;
		if (param != null) {
			if (param instanceof String) {
				xml = (String)param;
			} else {
				xml = PreventXMLUtils.asXML(param);
			}
		}
		String url = service;
		if (urlParams != null) {
			for (Map.Entry<String, String> entries : urlParams.getParams().entrySet()) {
				url = url.replace(entries.getKey(), entries.getValue());
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute: " + server + url + " xml: " + (xml == null ? "null" : xml));
		}
		HttpClient client = new HttpClient();
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server + url);
		try {
			httpMethod.setRequestHeader("Content-type", "text/xml");
			if (xml != null) {
				RequestEntity requestEntity = new StringRequestEntity(xml);
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Remote result is: " + response + " with status: " + statusCode);
			}
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("Remote result for " + (service) + " status: " + statusCode + " and body: " + (response == null ? "null" : response));
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			Object result = PreventXMLUtils.fromXML(response);
			return new XMLResponse(result, statusCode);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			if (LOG.isInfoEnabled()) {
				long end = System.currentTimeMillis();
				LOG.info("Execute: " + service + " took " + (end - start) + " millis");
			}
		}
	}

	public static String getPreventServer() {
		return preventServer;
	}

	public static void setPreventServer(String preventServer) {
		PreventConnector.preventServer = preventServer;
	}
}

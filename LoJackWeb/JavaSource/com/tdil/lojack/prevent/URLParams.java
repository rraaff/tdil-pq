package com.tdil.lojack.prevent;

import java.util.HashMap;
import java.util.Map;

import com.tdil.lojack.prevent.model.LoginResponse;

public class URLParams {

	private Map<String, String> params = new HashMap<String, String>();
	
	public URLParams(LoginResponse loginResponse) {
		params.put(PreventConnector.userToken, loginResponse.getUserToken());
	}
	
	public URLParams index(String param) {
		params.put(PreventConnector.index, param);
		return this;
	}
	
	public URLParams secureZoneID(String param) {
		params.put(PreventConnector.secureZoneID, param);
		return this;
	}
	
	public URLParams speedLimitID(String param) {
		params.put(PreventConnector.speedLimitID, param);
		return this;
	}
	
	public URLParams vehicleID(String param) {
		params.put(PreventConnector.vehicleID, param);
		return this;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
}

package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class RequestResetPasswordResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374425721153632854L;

	private JSONObject result;

	public RequestResetPasswordResult(JSONObject result) {
		super();
		this.result = result;
	}

	private JSONObject getResult() {
		return result;
	}
	
	public String getTokenDev() {
		if (getResult().containsKey("token-development")) {
			JSONObject tokenDev = getResult().getJSONObject("token-development");
			return tokenDev.getString("token");
		} else {
			return null;
		}
	}
}

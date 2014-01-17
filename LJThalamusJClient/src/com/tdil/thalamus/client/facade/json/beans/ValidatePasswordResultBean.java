package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.ThalamusResponse;

public class ValidatePasswordResultBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6791203736911205827L;
	
	private ThalamusResponse result;
	private boolean unauthorized = false;
	
	public ValidatePasswordResultBean() {
	}
	
	public ValidatePasswordResultBean(ThalamusResponse result) {
		this.result = result;
	}
	
	public boolean isOk() {
		if (unauthorized) {
			return false;
		}
		if (result.isBadRequest()) {
			return false;
		}
		if (result.isUnauthorized()) {
			return false;
		}
		boolean result = false;
		JSONObject jsonObject = (JSONObject)this.result.getResult();
		if (jsonObject.containsKey("Result")) {
			if (jsonObject.get("Result") != JSONNull.getInstance()) {
				result = jsonObject.getBoolean("Result");
			}
		}
		return result;
	}

	public void setUnauthorized(boolean unauthorized) {
		this.unauthorized = unauthorized;
	}
	
}

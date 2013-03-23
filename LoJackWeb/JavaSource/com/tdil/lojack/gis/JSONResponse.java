package com.tdil.lojack.gis;

import net.sf.json.JSON;

import org.apache.commons.httpclient.HttpStatus;

public class JSONResponse {

	private JSON result;
	private int statusCode;
	
	public JSONResponse(JSON result, int statusCode) {
		super();
		this.result = result;
		this.statusCode = statusCode;
	}

	public JSON getResult() {
		return result;
	}
	public void setResult(JSON result) {
		this.result = result;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isBadRequest() {
		return this.getStatusCode() == HttpStatus.SC_BAD_REQUEST;
	}
}

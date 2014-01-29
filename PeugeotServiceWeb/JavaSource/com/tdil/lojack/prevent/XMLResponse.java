package com.tdil.lojack.prevent;

import org.apache.commons.httpclient.HttpStatus;

public class XMLResponse {

	private Object result;
	private int statusCode;
	
	public XMLResponse(Object result, int statusCode) {
		super();
		this.result = result;
		this.statusCode = statusCode;
	}

	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
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

package com.tdil.peugeotservice.android.rest.client;

public class CachedRestClientTask implements IRestClientTask {
	
	private String result;
	private int statusCode;
	
	public CachedRestClientTask(String result, int statusCode) {
		super();
		this.result = result;
		this.statusCode = statusCode;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}

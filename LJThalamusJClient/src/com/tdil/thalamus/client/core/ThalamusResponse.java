package com.tdil.thalamus.client.core;

import java.io.Serializable;

import org.apache.commons.httpclient.HttpStatus;

import com.tdil.thalamus.client.facade.json.beans.TokenHolder;

import net.sf.json.JSON;

public class ThalamusResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3577997708828821997L;
	private JSON result;
	private int statusCode;
	private TokenHolder tokenHolder;
	
	public ThalamusResponse(JSON result, int statusCode) {
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

	public TokenHolder getTokenHolder() {
		return tokenHolder;
	}

	public void setTokenHolder(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	public boolean isUnauthorized() {
		return this.getStatusCode() == HttpStatus.SC_UNAUTHORIZED;
	}
}

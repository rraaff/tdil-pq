package com.tdil.thalamus.client.facade.json.beans;

import com.tdil.thalamus.client.core.ThalamusResponse;

import net.sf.json.JSON;

public class LoginResult {

	private TokenHolder tokenHolder;
	private JSON result;
	private ThalamusResponse response;
	
	public TokenHolder getTokenHolder() {
		return tokenHolder;
	}
	public void setTokenHolder(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}
	public JSON getResult() {
		return result;
	}
	public void setResult(JSON result) {
		this.result = result;
	}
	public ThalamusResponse getResponse() {
		return response;
	}
	public void setResponse(ThalamusResponse response) {
		this.response = response;
	}
	
}
 
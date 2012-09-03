package com.tdil.thalamus.client.core;

public class HttpStatusException extends InvocationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8747548645219538996L;

	private int status;
	private String text;

	public HttpStatusException(int status, String text) {
		super();
		this.status = status;
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}
	
	
}

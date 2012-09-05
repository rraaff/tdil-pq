package com.tdil.thalamus.client.core;

import net.sf.json.JSON;

public class UnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6724115155316831546L;
	
	private JSON result;
	
	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(JSON result) {
		super();
		this.result = result;
	}

	public JSON getResult() {
		return result;
	}
	

}

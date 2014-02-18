package com.tdil.peugeotservice.android.rest.model;

import java.io.Serializable;

public class VLUDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2262804156192674675L;

	private String dni;
	private String domain;
	private String message;
	
	public VLUDataDTO() {
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

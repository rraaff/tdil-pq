package com.tdil.lojack.vlu.model;

import java.io.Serializable;

import com.tdil.lojack.model.VLUData;

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
	
	public VLUDataDTO(VLUData vluData) {
		setDni(vluData.getDni());
		setDomain(vluData.getDomain());
		setMessage(vluData.getMessage());
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

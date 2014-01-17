package com.tdil.thalamus.client.facade.json.beans;

public class ValidatePasswordBean {

	private String password;
	
	public ValidatePasswordBean() {
	}
	
	public ValidatePasswordBean(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

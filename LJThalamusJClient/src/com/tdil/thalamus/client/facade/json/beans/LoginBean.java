package com.tdil.thalamus.client.facade.json.beans;

public class LoginBean {

	private String principal;
	private String password;
	
	public LoginBean() {
		super();
	}
	
	public LoginBean(String principal, String password) {
		super();
		this.principal = principal;
		this.password = password;
	}
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

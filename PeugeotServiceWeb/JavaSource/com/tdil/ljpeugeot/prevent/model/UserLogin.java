package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="UserLogin")
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 2302439744441021092L;

	@XStreamAlias("user")
	private String user;
	
	@XStreamAlias("password")
	private String password;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
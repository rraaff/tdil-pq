package com.facebook;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class FacebookTestUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5173934544870100099L;
	
	private JSONObject object;
	private String name;
	private String email;
	private String password;
	
	public JSONObject getObject() {
		return object;
	}
	public void setObject(JSONObject object) {
		this.object = object;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}

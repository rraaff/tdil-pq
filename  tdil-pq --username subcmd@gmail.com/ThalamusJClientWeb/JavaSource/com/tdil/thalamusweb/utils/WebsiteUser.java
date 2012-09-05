package com.tdil.thalamusweb.utils;

import com.tdil.users.User;

public class WebsiteUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6066615316635634331L;

	private String username;
	private String password;
	
	private String name;
	
	public WebsiteUser(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	
}

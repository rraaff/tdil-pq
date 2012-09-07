package com.tdil.thalamusweb.utils;

import java.util.HashSet;
import java.util.Set;

import com.tdil.users.User;

public class WebsiteUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6066615316635634331L;

	private String username;
	private String password;
	
	private String name;
	
	private Set<Integer> appliedActivities = new HashSet<Integer>();
	
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

	public Set<Integer> getAppliedActivities() {
		return appliedActivities;
	}

	public void setAppliedActivities(Set<Integer> appliedActivities) {
		this.appliedActivities = appliedActivities;
	}
	
	public boolean appliesToActivity(int activityId) {
		return appliedActivities.contains(activityId);
	}
	
	
}

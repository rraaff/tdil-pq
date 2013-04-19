package com.tdil.lojack.utils;

import java.util.HashSet;
import java.util.Set;

import com.tdil.thalamus.client.facade.json.beans.TokenHolder;
import com.tdil.users.User;

public class WebsiteUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6066615316635634331L;

	private TokenHolder token;
	private String name;
	private String lojackUserId = "ase001"; // TODO XXX
	private String timezoneOffset;
	private String timezoneName;
	
	private boolean isHomeUser;
	private boolean isPreventUser;
	private boolean isPetUser;
	
	private com.tdil.lojack.model.WebsiteUser modelUser;
	
	private Set<String> appliedActivities = new HashSet<String>();
	
	public WebsiteUser(String name, TokenHolder tokenHolder, String timezoneOffset, String timezoneName) {
		super();
		this.name = name;
		this.token = tokenHolder;
		this.timezoneOffset = timezoneOffset;
		this.timezoneName = timezoneName;
	}
	
	@Override
	public Integer getId() {
		return 1;
	}
	
	public String getGuid() {
		return String.valueOf(this.getToken().getCookie().getValue());
	}
	
	public boolean isLogged() {
		return this.getToken() != null && this.getToken().hasToken();
	}

	public String getName() {
		return name;
	}

	public Set<String> getAppliedActivities() {
		return appliedActivities;
	}

	public void setAppliedActivities(Set<String> appliedActivities) {
		this.appliedActivities = appliedActivities;
	}
	
	public boolean appliesToActivity(String code) {
		return appliedActivities.contains(code);
	}

	public TokenHolder getToken() {
		return token;
	}
	
	@Override
	public Set<String> getRoles() {
		Set<String> roles = new HashSet<String>();
		roles.add(com.tdil.lojack.roles.WebsiteUser.INSTANCE.getName());
		return roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(String timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	public String getLojackUserId() {
		return lojackUserId;
	}

	public void setLojackUserId(String lojackUserId) {
		this.lojackUserId = lojackUserId;
	}

	public com.tdil.lojack.model.WebsiteUser getModelUser() {
		return modelUser;
	}

	public void setModelUser(com.tdil.lojack.model.WebsiteUser modelUser) {
		this.modelUser = modelUser;
	}

	public boolean isHomeUser() {
		return isHomeUser;
	}

	public void setHomeUser(boolean isHomeUser) {
		this.isHomeUser = isHomeUser;
	}

	public boolean isPreventUser() {
		return isPreventUser;
	}

	public void setPreventUser(boolean isPreventUser) {
		this.isPreventUser = isPreventUser;
	}

	public boolean isPetUser() {
		return isPetUser;
	}

	public void setPetUser(boolean isPetUser) {
		this.isPetUser = isPetUser;
	}
	
}

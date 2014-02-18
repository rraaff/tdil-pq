package com.tdil.peugeotservice.android.rest.model;

import java.io.Serializable;

public class ChangeLog implements Serializable {

	private static final long serialVersionUID = 4135669633103106386L;
	private String date;
	private String hour;
	private String action;
	private String user;
	private String lojackUserId;
	
	public ChangeLog() {
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLojackUserId() {
		return lojackUserId;
	}

	public void setLojackUserId(String lojackUserId) {
		this.lojackUserId = lojackUserId;
	}

}

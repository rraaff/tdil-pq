package com.tdil.lojack.gis.model;

public abstract class GISManageableObject {

	private String id;
	private String description;
	private boolean on;
	private String lastChangeDate;
	private String lastChangeHour;
	private String lastChangeAction;
	private String lastChangeUser;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	public String getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public String getLastChangeHour() {
		return lastChangeHour;
	}
	public void setLastChangeHour(String lastChangeHour) {
		this.lastChangeHour = lastChangeHour;
	}
	public String getLastChangeAction() {
		return lastChangeAction;
	}
	public void setLastChangeAction(String lastChangeAction) {
		this.lastChangeAction = lastChangeAction;
	}
	public String getLastChangeUser() {
		return lastChangeUser;
	}
	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}
	
}

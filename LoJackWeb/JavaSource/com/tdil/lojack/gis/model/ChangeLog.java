package com.tdil.lojack.gis.model;

public class ChangeLog {

	private String lastChangeDate;
	private String lastChangeHour;
	private String lastChangeAction;
	private String lastChangeUser;
	
	public ChangeLog() {
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

package com.tdil.thalamus.android.rest.model;

import java.io.Serializable;


public class NotificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7361807877137402005L;
	
	private int id;
	private String title;
	private String message;
	private Integer notificationtype;
	private Integer notificationlevel;
	private String creationdate;
	private boolean unread;
	
	public NotificationBean() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getNotificationtype() {
		return notificationtype;
	}
	public void setNotificationtype(Integer notificationtype) {
		this.notificationtype = notificationtype;
	}
	public Integer getNotificationlevel() {
		return notificationlevel;
	}
	public void setNotificationlevel(Integer notificationlevel) {
		this.notificationlevel = notificationlevel;
	}
	public String getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public boolean getUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}
}

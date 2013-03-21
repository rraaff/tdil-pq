package com.tdil.lojack.gis.model;

import java.io.Serializable;

public class LightAgenda implements Serializable {

	private static final long serialVersionUID = -822422216180257981L;
	
	private String id;
	private String description;
	
	// Tipos validos seran ALL_DAYS, BUSINESS_DAYS, WEEKENDS
	private String type;
	
	// Hora en formato HH en 00-24:MM:SS, ejemplo 10:30:00 18:30:00
	private String activateTime;
	private String deactivateTime;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getActivateTime() {
		return activateTime;
	}
	public void setActivateTime(String activateTime) {
		this.activateTime = activateTime;
	}
	public String getDeactivateTime() {
		return deactivateTime;
	}
	public void setDeactivateTime(String deactivateTime) {
		this.deactivateTime = deactivateTime;
	}
}

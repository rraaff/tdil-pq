package com.tdil.thalamus.android.rest.model.prevent;

import java.io.Serializable;


public class PositionHistoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387311661650589003L;
	private String fecha;
	private String longitude;
	private String latitude;
	private String street;
	private String number;
	private String direction;
	private String speed;
	
	public PositionHistoryBean() {
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}

}

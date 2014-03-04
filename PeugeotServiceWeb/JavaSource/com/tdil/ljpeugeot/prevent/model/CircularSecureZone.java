package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias(value="CircularSecureZone")
public class CircularSecureZone implements Serializable {

	private static final long serialVersionUID = 594597535382996379L;

	@XStreamAlias(value="description")
	private String description;
	@XStreamAlias(value="longitude")
	private String longitude;
	@XStreamAlias(value="latitude")
	private String latitude;
	@XStreamAlias(value="radio")
	private String radio;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}

	
	
}

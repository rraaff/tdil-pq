package com.tdil.lojack.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias(value="SatellitePosition")
public class SatellitePosition implements Serializable {

	private static final long serialVersionUID = 594597535382996379L;

	@XStreamAlias(value="Longitude")
	private String longitude;
	@XStreamAlias(value="Latitude")
	private String latitude;
	@XStreamAlias(value="Street")
	private String street;
	@XStreamAlias(value="Number")
	private String number;
	@XStreamAlias(value="Zoom")
	private String zoom;
	
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
	public String getZoom() {
		return zoom;
	}
	public void setZoom(String zoom) {
		this.zoom = zoom;
	}
}

package com.tdil.thalamus.android.rest.model.parkedmode;

import java.io.Serializable;

public class ParkedModeStatus implements Serializable {

	private static final long serialVersionUID = 457280774604652610L;

	private String vehicleID;
	private String domain;
	private String latitude;
	private String longitude;
	private String geoRef;
	
	// ON/OFF
	private String status;


	public ParkedModeStatus() {
	}
	
	public ParkedModeStatus(String vehicleID, String domain, String status) {
		super();
		this.vehicleID = vehicleID;
		this.domain = domain;
		this.status = status;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getGeoRef() {
		return geoRef;
	}

	public void setGeoRef(String geoRef) {
		this.geoRef = geoRef;
	}

}

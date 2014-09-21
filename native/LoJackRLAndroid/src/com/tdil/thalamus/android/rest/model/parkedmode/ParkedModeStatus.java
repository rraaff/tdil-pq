package com.tdil.thalamus.android.rest.model.parkedmode;

import java.io.Serializable;

public class ParkedModeStatus implements Serializable {

	private static final long serialVersionUID = 457280774604652610L;

	private String vehicleID;
	private String domain;
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

}

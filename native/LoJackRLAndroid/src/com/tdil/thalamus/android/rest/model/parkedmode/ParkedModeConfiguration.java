package com.tdil.thalamus.android.rest.model.parkedmode;

import java.io.Serializable;

public class ParkedModeConfiguration implements Serializable {

	private static final long serialVersionUID = 457280774604652610L;

	private String vehicleID;
	private String domain;
	// ON/OFF
	private String status;
	private String phone;
	private boolean always;

	public ParkedModeConfiguration() {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAlways() {
		return always;
	}

	public void setAlways(boolean always) {
		this.always = always;
	}
	
}

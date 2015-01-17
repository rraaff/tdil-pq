package com.tdil.peugeotservice.android.rest.model.prevent;

import java.io.Serializable;

public class PhoneNumbersBean implements Serializable {

	private static final long serialVersionUID = 457280774604652610L;

	private String userToken;

	private String vehicleID;

	private String alert;

	private String crash;

	private String other;

	private String status;

	public PhoneNumbersBean() {
	}
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhoneNumbers [");
		if (userToken != null) {
			builder.append("userToken=");
			builder.append(userToken);
			builder.append(", ");
		}
		if (vehicleID != null) {
			builder.append("vehicleID=");
			builder.append(vehicleID);
			builder.append(", ");
		}
		if (alert != null) {
			builder.append("alerts=");
			builder.append(alert);
			builder.append(", ");
		}
		if (crash != null) {
			builder.append("crash=");
			builder.append(crash);
			builder.append(", ");
		}
		if (other != null) {
			builder.append("other=");
			builder.append(other);
		}
		builder.append(']');
		return builder.toString();
	}


	public String getUserToken() {
		return userToken;
	}


	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}


	public String getVehicleID() {
		return vehicleID;
	}


	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}


	public String getAlert() {
		return alert;
	}


	public void setAlert(String alert) {
		this.alert = alert;
	}


	public String getCrash() {
		return crash;
	}


	public void setCrash(String crash) {
		this.crash = crash;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

}

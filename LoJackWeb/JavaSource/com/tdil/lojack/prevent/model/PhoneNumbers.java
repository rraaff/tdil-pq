package com.tdil.lojack.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="PhoneNumbers")
public class PhoneNumbers implements Serializable {

	private static final long serialVersionUID = 457280774604652610L;
	
	@XStreamAlias(value="userToken")
	private String userToken;
	
	@XStreamAlias(value="vehicleID")
	private String vehicleID;
	
	@XStreamAlias(value="Alerts")
	private String alerts;
	
	@XStreamAlias(value="Crash")
	private String crash;
	
	@XStreamAlias(value="Other")
	private String other;

	public String getAlerts() {
		return alerts;
	}

	public void setAlerts(String alerts) {
		this.alerts = alerts;
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
		if (alerts != null) {
			builder.append("alerts=");
			builder.append(alerts);
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
		builder.append("]");
		return builder.toString();
	}

	
}

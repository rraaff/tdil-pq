package com.tdil.lojack.struts.forms.beans;

import java.io.Serializable;

import com.tdil.lojack.prevent.model.SecureZone;
import com.tdil.lojack.prevent.model.SecureZones;
import com.tdil.lojack.prevent.model.SpeedLimit;
import com.tdil.lojack.prevent.model.SpeedLimits;
import com.tdil.lojack.prevent.model.Vehicle;

public class SecureZoneSelectionBean implements Serializable {

	private static final long serialVersionUID = -3155274099808036525L;

	private Vehicle vehicle;
	private String secureZoneId;

	private SecureZones zones;

	public SecureZoneSelectionBean(Vehicle vehicleId, SecureZones zones) {
		super();
		this.vehicle = vehicleId;
		this.zones = zones;
		this.secureZoneId = getSelected(zones);
	}

	private String getSelected(SecureZones zones) {
		SecureZone zone = zones.getActiveZone();
		if (zone == null) {
			return null;
		}
		return zone.getId();
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicleId) {
		this.vehicle = vehicleId;
	}
	public String getSecureZoneId() {
		return secureZoneId;
	}
	public void setSecureZoneId(String secureZoneId) {
		this.secureZoneId = secureZoneId;
	}

	public SecureZones getZones() {
		return zones;
	}

	public void setZones(SecureZones zones) {
		this.zones = zones;
	}

	public SecureZone getSelectedSecureZone() {
		for (SecureZone speedLimit : zones.getSecureZones()) {
			if (speedLimit.getId().equals(this.getSecureZoneId())) {
				return speedLimit;
			}
		}
		return null;
	}
}

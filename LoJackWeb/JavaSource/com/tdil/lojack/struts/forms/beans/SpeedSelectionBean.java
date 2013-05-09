package com.tdil.lojack.struts.forms.beans;

import java.io.Serializable;

import com.tdil.lojack.prevent.model.SpeedLimit;
import com.tdil.lojack.prevent.model.SpeedLimits;
import com.tdil.lojack.prevent.model.Vehicle;

public class SpeedSelectionBean implements Serializable {

	private static final long serialVersionUID = -3155274099808036525L;

	private Vehicle vehicle;
	private String speedLimitId;

	private SpeedLimits limits;

	public SpeedSelectionBean(Vehicle vehicleId, SpeedLimits speedLimits) {
		super();
		this.vehicle = vehicleId;
		this.limits = speedLimits;
		this.speedLimitId = getSelected(speedLimits);
	}

	private String getSelected(SpeedLimits speedLimits) {
		SpeedLimit speedLimit = speedLimits.getActiveSpeedLimit();
		if (speedLimit == null) {
			return null;
		}
		return speedLimit.getId();
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicleId) {
		this.vehicle = vehicleId;
	}
	public String getSpeedLimitId() {
		return speedLimitId;
	}
	public void setSpeedLimitId(String speedLimitId) {
		this.speedLimitId = speedLimitId;
	}

	public SpeedLimits getLimits() {
		return limits;
	}

	public void setLimits(SpeedLimits limits) {
		this.limits = limits;
	}
}

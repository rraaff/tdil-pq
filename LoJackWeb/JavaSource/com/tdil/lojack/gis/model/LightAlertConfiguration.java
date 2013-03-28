package com.tdil.lojack.gis.model;

import java.io.Serializable;

public class LightAlertConfiguration implements Serializable {

	private static final long serialVersionUID = -4926983680150408995L;

	private String userId;
	private String lightId;
	private boolean activateDeactivate;
	private boolean activateDeactivateByOther;
	private boolean powerSupplyLost;
	
	public LightAlertConfiguration() {
	}
	
	public String getLightId() {
		return lightId;
	}
	public void setLightId(String alarmId) {
		this.lightId = alarmId;
	}
	public boolean isActivateDeactivate() {
		return activateDeactivate;
	}
	public void setActivateDeactivate(boolean activateDeactivate) {
		this.activateDeactivate = activateDeactivate;
	}
	public boolean isActivateDeactivateByOther() {
		return activateDeactivateByOther;
	}
	public void setActivateDeactivateByOther(boolean activateDeactivateByOther) {
		this.activateDeactivateByOther = activateDeactivateByOther;
	}
	public boolean isPowerSupplyLost() {
		return powerSupplyLost;
	}
	public void setPowerSupplyLost(boolean powerSupplyLost) {
		this.powerSupplyLost = powerSupplyLost;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}
}

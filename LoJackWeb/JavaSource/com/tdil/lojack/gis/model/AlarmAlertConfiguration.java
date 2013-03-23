package com.tdil.lojack.gis.model;

import java.io.Serializable;

public class AlarmAlertConfiguration implements Serializable {

	private static final long serialVersionUID = -4926983680150408995L;

	private String userId;
	private String alarmId;
	private boolean activateDeactivate;
	private boolean activateDeactivateByOther;
	private boolean powerSupplyLost;
	private boolean alarmActivation;
	
	public AlarmAlertConfiguration() {
	}
	
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
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
	public boolean isAlarmActivation() {
		return alarmActivation;
	}
	public void setAlarmActivation(boolean alarmActivation) {
		this.alarmActivation = alarmActivation;
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

package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.ljpeugeot.model.Alert;

@XmlRootElement
public class AlertResponseBean {

	private int alertId;
	
	public AlertResponseBean(Alert alert) {
		this.alertId = alert.getId();
	}

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}



}

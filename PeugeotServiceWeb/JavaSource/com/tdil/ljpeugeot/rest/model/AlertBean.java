package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.ljpeugeot.model.Alert;

@XmlRootElement
public class AlertBean {

	private String alertDate;
	private String modificationDate;
	private String status;
	private String lastResponsible;
	private String phone;
	private long lat;
	private long lon;
	
	public AlertBean(Alert alert) {
		
	}

	public String getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastResponsible() {
		return lastResponsible;
	}

	public void setLastResponsible(String lastResponsible) {
		this.lastResponsible = lastResponsible;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public long getLon() {
		return lon;
	}

	public void setLon(long lon) {
		this.lon = lon;
	}
	

}

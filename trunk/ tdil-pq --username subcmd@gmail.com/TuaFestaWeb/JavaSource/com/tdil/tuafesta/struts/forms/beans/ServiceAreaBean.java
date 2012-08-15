package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;

public class ServiceAreaBean implements Serializable {

	// TODO a esto le falta considerar que puede ser una edicion
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5478327470170295601L;

	private int index;
	
	private int geoLevel4Id;
	private String serviceAreaText;
	

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getGeoLevel4Id() {
		return geoLevel4Id;
	}
	public void setGeoLevel4Id(int geoLevel4Id) {
		this.geoLevel4Id = geoLevel4Id;
	}
	public String getServiceAreaText() {
		return serviceAreaText;
	}
	public void setServiceAreaText(String serviceAreaText) {
		this.serviceAreaText = serviceAreaText;
	}
	
}

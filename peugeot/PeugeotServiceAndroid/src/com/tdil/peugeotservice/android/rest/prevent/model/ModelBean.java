package com.tdil.peugeotservice.android.rest.prevent.model;

import java.io.Serializable;


public class ModelBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7529184442931160637L;
	private String name;
	private String description;
	private Integer monthwarranty;
	private Integer kmwarranty;
	private String warrantyDescription;
	
	public ModelBean() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMonthwarranty() {
		return monthwarranty;
	}
	public void setMonthwarranty(Integer monthwarranty) {
		this.monthwarranty = monthwarranty;
	}

	public Integer getKmwarranty() {
		return kmwarranty;
	}

	public void setKmwarranty(Integer kmwarranty) {
		this.kmwarranty = kmwarranty;
	}

	public String getWarrantyDescription() {
		return warrantyDescription;
	}

	public void setWarrantyDescription(String warrantyDescription) {
		this.warrantyDescription = warrantyDescription;
	}
	
}

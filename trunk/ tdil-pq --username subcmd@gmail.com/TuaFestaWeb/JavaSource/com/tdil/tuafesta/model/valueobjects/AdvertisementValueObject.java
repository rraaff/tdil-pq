package com.tdil.tuafesta.model.valueobjects;

import com.tdil.tuafesta.model.Advertisement;

public class AdvertisementValueObject extends Advertisement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2849791323318107750L;
	private String profesionalBusinessName;
	private String sellName;

	public String getProfesionalBusinessName() {
		return profesionalBusinessName;
	}

	public void setProfesionalBusinessName(String profesionalBusinessName) {
		this.profesionalBusinessName = profesionalBusinessName;
	}
	
	public String getFormatedfromdate() {
		return com.tdil.utils.DateUtils.formatDate(this.getFromdate());
	}
	
	public String getFormatedtodate() {
		return com.tdil.utils.DateUtils.formatDate(this.getTodate());
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
}

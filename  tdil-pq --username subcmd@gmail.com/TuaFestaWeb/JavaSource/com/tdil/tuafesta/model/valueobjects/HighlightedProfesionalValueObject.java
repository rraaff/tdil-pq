package com.tdil.tuafesta.model.valueobjects;

import com.tdil.tuafesta.model.HighlightedProfesional;
import com.tdil.tuafesta.utils.DateUtils;

public class HighlightedProfesionalValueObject extends HighlightedProfesional {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2722825197570225856L;

	private String profesionalBusinessName;

	public String getProfesionalBusinessName() {
		return profesionalBusinessName;
	}

	public void setProfesionalBusinessName(String profesionalBusinessName) {
		this.profesionalBusinessName = profesionalBusinessName;
	}
	
	public String getFormatedfromdate() {
		return DateUtils.formatDate(this.getFromdate());
	}
	
	public String getFormatedtodate() {
		return DateUtils.formatDate(this.getTodate());
	}
	
}

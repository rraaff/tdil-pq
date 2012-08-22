package com.tdil.tuafesta.model.valueobjects;

import com.tdil.tuafesta.model.Sell;

public class SellValueObject extends Sell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7335582850975800212L;
	
	private String name;
	private String description;
	
	private String profesionalbusinessname;

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

	public String getProfesionalbusinessname() {
		return profesionalbusinessname;
	}

	public void setProfesionalbusinessname(String profesionalbusinessname) {
		this.profesionalbusinessname = profesionalbusinessname;
	}
	
}

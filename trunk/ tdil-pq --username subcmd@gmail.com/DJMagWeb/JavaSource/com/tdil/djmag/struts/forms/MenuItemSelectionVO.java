package com.tdil.djmag.struts.forms;

import java.io.Serializable;

import com.tdil.djmag.model.Country;

public class MenuItemSelectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656910179080344260L;
	private Integer countryId;
	private String countryName;
	private String sectionName;
	private boolean selected;
	private Integer ownerId;
	
	public MenuItemSelectionVO(Country country) {
		super();
		this.setCountryId(country.getId());
		this.setCountryName(country.getName());
	}
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer id) {
		this.countryId = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String name) {
		this.countryName = name;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
}

package com.tdil.djmag.struts.forms;

import java.io.Serializable;

import com.tdil.djmag.model.Country;

public class CountrySelectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656910179080344260L;
	private Integer id;
	private String name;
	private boolean selected;
	private Integer ownerId;
	
	public CountrySelectionVO(Country country) {
		super();
		this.setId(country.getId());
		this.setName(country.getName());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}

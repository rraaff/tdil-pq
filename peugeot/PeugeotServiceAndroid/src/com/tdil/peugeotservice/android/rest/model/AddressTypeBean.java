package com.tdil.peugeotservice.android.rest.model;

public class AddressTypeBean {

	private String name;
	private String description;
	
	public AddressTypeBean(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		if (description == null || description.length() == 0) {
			description = this.name;
		}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

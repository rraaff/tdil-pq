package com.tdil.peugeotservice.android.rest.model;

public class StateBean {

	private int id;
	private String name;
	private String description;
	private int countryId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	@Override
	public String toString() {
		return "StateBean [id=" + id + ", "
				+ (name != null ? "name=" + name + ", " : "") + "countryId="
				+ countryId + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

package com.tdil.thalamus.client.facade.json.beans;

public class DocumentTypeBean {

	private int id;
	private String name;
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
		return "DocumentTypeBean [id=" + id + ", "
				+ (name != null ? "name=" + name + ", " : "") + "countryId="
				+ countryId + "]";
	}
	
	
}

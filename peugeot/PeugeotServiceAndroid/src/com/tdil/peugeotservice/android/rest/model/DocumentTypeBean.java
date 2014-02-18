package com.tdil.peugeotservice.android.rest.model;

public class DocumentTypeBean {

	private int id;
	private String name;
	private int countryId;
	
	public DocumentTypeBean() {
		// TODO Auto-generated constructor stub
	}
	
	public DocumentTypeBean(int id, String name, int countryId) {
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}

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

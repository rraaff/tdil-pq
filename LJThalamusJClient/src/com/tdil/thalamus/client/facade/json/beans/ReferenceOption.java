package com.tdil.thalamus.client.facade.json.beans;

public class ReferenceOption {

	private int id;
	private String name;
	
	public ReferenceOption() {
	}
	
	public ReferenceOption(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	@Override
	public String toString() {
		return "CountryBean [id=" + id + ", "
				+ (name != null ? "name=" + name : "") + "]";
	}
	
}

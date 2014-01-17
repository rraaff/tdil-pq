package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;

public class BrandBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2110281771498392641L;
	
	private int id;
	private String name;
	private String description;
	
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
		return "BrandBean [id=" + id + ", "
				+ (name != null ? "name=" + name : "") + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}

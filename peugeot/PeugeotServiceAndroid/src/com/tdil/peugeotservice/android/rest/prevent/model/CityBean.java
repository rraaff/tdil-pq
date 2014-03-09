package com.tdil.peugeotservice.android.rest.prevent.model;

import java.io.Serializable;


public class CityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8640164363064069561L;
	private int id;
	private String name;
	
	public CityBean() {
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
}

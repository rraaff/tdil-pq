package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.ljpeugeot.model.City;

@XmlRootElement
public class CityBean {

	private int id;
	private String name;
	
	public CityBean(City city) {
		this.id = city.getId();
		this.name = city.getName();
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

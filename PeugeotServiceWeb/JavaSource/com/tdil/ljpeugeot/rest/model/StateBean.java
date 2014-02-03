package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.ljpeugeot.model.State;

@XmlRootElement
public class StateBean {

	private int id;
	private String name;
	
	public StateBean(State state) {
		this.id = state.getId();
		this.name = state.getName();
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

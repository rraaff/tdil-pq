package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RelationBean {

	private String name;
	private String description;
	
	public RelationBean(String name, String description) {
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
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

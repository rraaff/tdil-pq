package com.tdil.lojack.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="SL")
public class SpeedLimit implements Serializable {

	private static final long serialVersionUID = 8350465465200249803L;
	
	@XStreamAlias(value="ID")
	private String id;
	
	@XStreamAlias(value="Description")
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

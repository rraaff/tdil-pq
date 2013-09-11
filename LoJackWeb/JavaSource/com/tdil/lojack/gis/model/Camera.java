package com.tdil.lojack.gis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement
public class Camera implements Serializable {

	private static final long serialVersionUID = -6917436244277188931L;

	private String description;
	private String username;
	private String password;
	private String url;
	private String model;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		if (StringUtils.isEmpty(description)) {
			description = this.url;
		}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

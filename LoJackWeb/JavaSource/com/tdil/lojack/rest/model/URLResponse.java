package com.tdil.lojack.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class URLResponse {

	private String url;
	
	public URLResponse(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

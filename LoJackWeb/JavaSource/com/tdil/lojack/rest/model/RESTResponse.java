package com.tdil.lojack.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RESTResponse {

	private boolean ok;
	
	public static final RESTResponse OK_RESPONSE = new RESTResponse(true);
	public static final RESTResponse FAIL_RESPONSE = new RESTResponse(false);
	
	public RESTResponse() {
	}

	public RESTResponse(boolean accepted) {
		super();
		this.ok = accepted;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}

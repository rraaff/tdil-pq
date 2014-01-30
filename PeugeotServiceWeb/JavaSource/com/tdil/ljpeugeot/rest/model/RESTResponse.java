package com.tdil.ljpeugeot.rest.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RESTResponse {

	private Boolean ok;
	
	private Map<String, String> errors = new HashMap<String, String>();
	
	public static final RESTResponse OK_RESPONSE = new RESTResponse(true);
	public static final RESTResponse FAIL_RESPONSE = new RESTResponse(false);
	
	public RESTResponse() {
	}

	public RESTResponse(boolean accepted) {
		super();
		this.ok = accepted;
	}

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}

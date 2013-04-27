package com.tdil.lojack.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="SecureZoneResponse")
public class SecureZoneResponse implements Serializable {

	private static final long serialVersionUID = -6970006504745625049L;

	@XStreamAlias(value="Status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

package com.tdil.lojack.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="SpeedLimitsResponse")
public class SpeedLimitResponse implements Serializable {

	private static final long serialVersionUID = -6970006504745625049L;

	@XStreamAlias(value="Status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpeedLimitResponse [");
		if (status != null) {
			builder.append("status=");
			builder.append(status);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

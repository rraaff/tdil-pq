package com.tdil.thalamus.android.rest.model.prevent;

import java.io.Serializable;

public class SecureZoneBean implements Serializable {

	private static final long serialVersionUID = 46657343459626873L;

	private String id;
	private String description;
	private String status;
	
	public SecureZoneBean() {
	}
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecureZone [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
		}
		builder.append(']');
		return builder.toString();
	}
	
	public boolean isActive() {
		return this.getDescription().endsWith("ACTIVA");
	}
	

}

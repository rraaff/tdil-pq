package com.tdil.peugeotservice.android.rest.model.prevent;

import java.io.Serializable;

public class SpeedLimitBean implements Serializable {

	private static final long serialVersionUID = 8350465465200249803L;
	
	private String id;
	
	private String description;
	
	public SpeedLimitBean() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpeedLimit [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
		}
		builder.append(']');
		return builder.toString();
	}

	public boolean isActive() {
		return this.getDescription().endsWith("ACTIVA");
	}

}

package com.tdil.lojack.prevent.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="SatellitePositionDesc")
public class SatellitePositionDesc extends SatellitePosition {

	private static final long serialVersionUID = 5237392979876711115L;
	
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SatellitePositionDesc [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
		}
		builder.append("]");
		return builder.toString();
	}

	
}

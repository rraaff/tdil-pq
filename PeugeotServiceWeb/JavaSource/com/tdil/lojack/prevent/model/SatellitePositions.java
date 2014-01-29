package com.tdil.lojack.prevent.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="SatellitePositions")
public class SatellitePositions implements Serializable {

	private static final long serialVersionUID = 8760240826905090226L;
	
	@XStreamAlias(value="SatellitePositionDescCollection")
	private List<SatellitePositionDesc> satellitePositionDescCollection;

	public List<SatellitePositionDesc> getSatellitePositionDescCollection() {
		return satellitePositionDescCollection;
	}

	public void setSatellitePositionDescCollection(
			List<SatellitePositionDesc> satellitePositionDescCollection) {
		this.satellitePositionDescCollection = satellitePositionDescCollection;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SatellitePositions [");
		if (satellitePositionDescCollection != null) {
			builder.append("satellitePositionDescCollection=");
			builder.append(satellitePositionDescCollection);
		}
		builder.append(']');
		return builder.toString();
	}

	
}

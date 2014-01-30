package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value="SpeedLimits")
public class SpeedLimits implements Serializable {

	private static final long serialVersionUID = 6804676465091871046L;

	@XStreamImplicit
	private List<SpeedLimit> limits;

	public List<SpeedLimit> getLimits() {
		return limits;
	}

	public void setLimits(List<SpeedLimit> limits) {
		this.limits = limits;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpeedLimits [");
		if (limits != null) {
			builder.append("limits=");
			builder.append(limits);
		}
		builder.append(']');
		return builder.toString();
	}

	public SpeedLimit getActiveSpeedLimit() {
		for (SpeedLimit sl : limits) {
			if (sl.isActive()) {
				return sl;
			}
		}
		return null;
	}
	
	
}

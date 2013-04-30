package com.tdil.lojack.prevent.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value="SecureZones")
public class SecureZones implements Serializable {

	private static final long serialVersionUID = 2469067014177327893L;

	@XStreamImplicit
	private List<SecureZone> secureZones;

	public List<SecureZone> getSecureZones() {
		return secureZones;
	}

	public void setSecureZones(List<SecureZone> secureZones) {
		this.secureZones = secureZones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecureZones [");
		if (secureZones != null) {
			builder.append("secureZones=");
			builder.append(secureZones);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}

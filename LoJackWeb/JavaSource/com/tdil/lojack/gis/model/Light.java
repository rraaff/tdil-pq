package com.tdil.lojack.gis.model;


public class Light extends GISManageableObject {

	private static final long serialVersionUID = -8954431125700653499L;

	public boolean hasOnOffInfo;
	
	public Light() {
	}

	public boolean isHasOnOffInfo() {
		return hasOnOffInfo;
	}

	public void setHasOnOffInfo(boolean hasOnOffInfo) {
		this.hasOnOffInfo = hasOnOffInfo;
	}

}

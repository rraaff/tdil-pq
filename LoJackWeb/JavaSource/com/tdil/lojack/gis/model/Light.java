package com.tdil.lojack.gis.model;


public class Light extends GISManageableObject {

	private static final long serialVersionUID = -8954431125700653499L;

	public boolean hasOnOffInfo;
	private boolean randomSequence;
	
	public Light() {
	}

	public boolean isHasOnOffInfo() {
		return hasOnOffInfo;
	}

	public void setHasOnOffInfo(boolean hasOnOffInfo) {
		this.hasOnOffInfo = hasOnOffInfo;
	}

	public boolean isRandomSequence() {
		return randomSequence;
	}

	public void setRandomSequence(boolean randomSequence) {
		this.randomSequence = randomSequence;
	}

}

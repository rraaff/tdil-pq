package com.tdil.djmag.model.valueobjects;

import java.io.Serializable;

import com.tdil.djmag.model.Banner;

public class BannerValueObject extends Banner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622797049905594827L;
	
	private String position;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


}

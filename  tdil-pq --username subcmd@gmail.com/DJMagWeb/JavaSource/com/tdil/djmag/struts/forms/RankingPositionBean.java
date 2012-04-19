package com.tdil.djmag.struts.forms;

import java.io.Serializable;

public class RankingPositionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436118842744032161L;

	private String position;
	
	public RankingPositionBean(String position) {
		super();
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}

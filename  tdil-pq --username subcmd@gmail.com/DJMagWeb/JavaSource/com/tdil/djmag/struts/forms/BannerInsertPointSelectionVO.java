package com.tdil.djmag.struts.forms;

import java.io.Serializable;

public class BannerInsertPointSelectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656910179080344260L;
	private String insertPoint;
	private boolean selected;
	
	public BannerInsertPointSelectionVO() {
		// TODO Auto-generated constructor stub
	}
	
	public BannerInsertPointSelectionVO(String point) {
		super();
		setInsertPoint(point);
	}
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getInsertPoint() {
		return insertPoint;
	}

	public void setInsertPoint(String insertPoint) {
		this.insertPoint = insertPoint;
	}

}

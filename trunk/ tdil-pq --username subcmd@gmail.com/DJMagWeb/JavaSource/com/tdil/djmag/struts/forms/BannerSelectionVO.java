package com.tdil.djmag.struts.forms;

import java.io.Serializable;

import com.tdil.djmag.model.Banner;

public class BannerSelectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656910179080344260L;
	private Integer bannerId;
	private String description;
	private boolean selected;
	
	public BannerSelectionVO() {
		// TODO Auto-generated constructor stub
	}
	
	public BannerSelectionVO(Banner banner) {
		super();
		this.setBannerId(banner.getId());
		this.setDescription(banner.getDescription());
	}
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
}

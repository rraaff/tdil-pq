package com.tdil.djmag.model.deprecated;

import org.apache.commons.lang.StringUtils;

public class RankingPosition {

	private String position;
	private String description;
	private String imageext;
	private String imageid;
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean hasImage() {
		return this.getImageid() != null && !StringUtils.isEmpty(this.getImageid()) && !"0".equals(this.getImageid());
	}
	
	public String getImageext() {
		return imageext;
	}
	public void setImageext(String imageext) {
		this.imageext = imageext;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
}

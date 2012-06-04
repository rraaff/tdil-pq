package com.tdil.milka.model.valueobjects;

public class ExperienceValueObject {

	private int id;
	private String type;
	private String description;
	
	private int idblob;
	private String extblob;
	
	private int linkCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdblob() {
		return idblob;
	}

	public void setIdblob(int idblob) {
		this.idblob = idblob;
	}

	public String getExtblob() {
		return extblob;
	}

	public void setExtblob(String extblob) {
		this.extblob = extblob;
	}

	public int getLinkCount() {
		return linkCount;
	}

	public void setLinkCount(int linkCount) {
		this.linkCount = linkCount;
	}

}

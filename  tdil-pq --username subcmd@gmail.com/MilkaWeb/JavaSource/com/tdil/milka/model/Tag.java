package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class Tag extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TAG.description
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TAG.description
	 * @return  the value of TAG.description
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TAG.description
	 * @param description  the value for TAG.description
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}
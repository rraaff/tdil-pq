package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Section extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SECTION.name
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	private String name;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SECTION.name
	 * @return  the value of SECTION.name
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SECTION.name
	 * @param name  the value for SECTION.name
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}
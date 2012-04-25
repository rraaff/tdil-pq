package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Section extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SECTION.name
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SECTION.sectionType
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	private String sectiontype;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SECTION.name
	 * @return  the value of SECTION.name
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SECTION.name
	 * @param name  the value for SECTION.name
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SECTION.sectionType
	 * @return  the value of SECTION.sectionType
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public String getSectiontype() {
		return sectiontype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SECTION.sectionType
	 * @param sectiontype  the value for SECTION.sectionType
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public void setSectiontype(String sectiontype) {
		this.sectiontype = sectiontype == null ? null : sectiontype.trim();
	}
}
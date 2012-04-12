package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Country extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column COUNTRY.name
	 * @mbggenerated  Thu Apr 12 08:34:37 ART 2012
	 */
	private String name;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column COUNTRY.name
	 * @return  the value of COUNTRY.name
	 * @mbggenerated  Thu Apr 12 08:34:37 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column COUNTRY.name
	 * @param name  the value for COUNTRY.name
	 * @mbggenerated  Thu Apr 12 08:34:37 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/** Methods **/
	@Override
	public String toString() {
		return this.getName();
	}
	
}
package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class State extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATE.name
	 * @mbggenerated  Mon Feb 03 01:37:11 ART 2014
	 */
	private String name;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATE.name
	 * @return  the value of STATE.name
	 * @mbggenerated  Mon Feb 03 01:37:11 ART 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATE.name
	 * @param name  the value for STATE.name
	 * @mbggenerated  Mon Feb 03 01:37:11 ART 2014
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}
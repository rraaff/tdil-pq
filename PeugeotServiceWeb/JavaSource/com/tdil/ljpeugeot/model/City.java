package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class City extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CITY.name
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CITY.id_state
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	private Integer idState;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CITY.name
	 * @return  the value of CITY.name
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CITY.name
	 * @param name  the value for CITY.name
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CITY.id_state
	 * @return  the value of CITY.id_state
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public Integer getIdState() {
		return idState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CITY.id_state
	 * @param idState  the value for CITY.id_state
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public void setIdState(Integer idState) {
		this.idState = idState;
	}
}
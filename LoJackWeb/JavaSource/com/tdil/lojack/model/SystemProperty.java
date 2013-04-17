package com.tdil.lojack.model;

import com.tdil.ibatis.PersistentObject;

public class SystemProperty extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSPROPERTIES.propKey
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	private String propkey;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSPROPERTIES.propValue
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	private String propvalue;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSPROPERTIES.description
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSPROPERTIES.propKey
	 * @return  the value of SYSPROPERTIES.propKey
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	public String getPropkey() {
		return propkey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSPROPERTIES.propKey
	 * @param propkey  the value for SYSPROPERTIES.propKey
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	public void setPropkey(String propkey) {
		this.propkey = propkey == null ? null : propkey.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSPROPERTIES.propValue
	 * @return  the value of SYSPROPERTIES.propValue
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	public String getPropvalue() {
		return propvalue;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSPROPERTIES.propValue
	 * @param propvalue  the value for SYSPROPERTIES.propValue
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	public void setPropvalue(String propvalue) {
		this.propvalue = propvalue == null ? null : propvalue.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSPROPERTIES.description
	 * @return  the value of SYSPROPERTIES.description
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSPROPERTIES.description
	 * @param description  the value for SYSPROPERTIES.description
	 * @mbggenerated  Tue Apr 16 18:47:10 ART 2013
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}
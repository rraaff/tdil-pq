package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class SystemProperty extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSPROPERTIES.propKey
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	private String propkey;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSPROPERTIES.propValue
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	private String propvalue;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSPROPERTIES.description
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSPROPERTIES.propKey
	 * @return  the value of SYSPROPERTIES.propKey
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public String getPropkey() {
		return propkey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSPROPERTIES.propKey
	 * @param propkey  the value for SYSPROPERTIES.propKey
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public void setPropkey(String propkey) {
		this.propkey = propkey == null ? null : propkey.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSPROPERTIES.propValue
	 * @return  the value of SYSPROPERTIES.propValue
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public String getPropvalue() {
		return propvalue;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSPROPERTIES.propValue
	 * @param propvalue  the value for SYSPROPERTIES.propValue
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public void setPropvalue(String propvalue) {
		this.propvalue = propvalue == null ? null : propvalue.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSPROPERTIES.description
	 * @return  the value of SYSPROPERTIES.description
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSPROPERTIES.description
	 * @param description  the value for SYSPROPERTIES.description
	 * @mbggenerated  Sun May 27 12:17:04 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}
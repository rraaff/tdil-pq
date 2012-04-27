package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Country extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column COUNTRY.name
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column COUNTRY.iso_code_2
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	private String isoCode2;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column COUNTRY.name
	 * @return  the value of COUNTRY.name
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column COUNTRY.name
	 * @param name  the value for COUNTRY.name
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column COUNTRY.iso_code_2
	 * @return  the value of COUNTRY.iso_code_2
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public String getIsoCode2() {
		return isoCode2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column COUNTRY.iso_code_2
	 * @param isoCode2  the value for COUNTRY.iso_code_2
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public void setIsoCode2(String isoCode2) {
		this.isoCode2 = isoCode2 == null ? null : isoCode2.trim();
	}

	/** Methods **/
	@Override
	public String toString() {
		return this.getName();
	}
	
}
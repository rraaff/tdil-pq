package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class CacheRegion extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CACHE_REGION.version
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CACHE_REGION.name
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private String name;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CACHE_REGION.version
	 * @return  the value of CACHE_REGION.version
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CACHE_REGION.version
	 * @param version  the value for CACHE_REGION.version
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CACHE_REGION.name
	 * @return  the value of CACHE_REGION.name
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CACHE_REGION.name
	 * @param name  the value for CACHE_REGION.name
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}
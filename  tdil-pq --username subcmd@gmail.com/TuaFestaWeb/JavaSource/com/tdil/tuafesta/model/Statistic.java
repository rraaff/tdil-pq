package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Statistic extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.textData
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	private String textdata;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.statType
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	private Integer stattype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.objectId
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	private Integer objectid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.textData
	 * @return  the value of STATISTIC.textData
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public String getTextdata() {
		return textdata;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.textData
	 * @param textdata  the value for STATISTIC.textData
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public void setTextdata(String textdata) {
		this.textdata = textdata == null ? null : textdata.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.statType
	 * @return  the value of STATISTIC.statType
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public Integer getStattype() {
		return stattype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.statType
	 * @param stattype  the value for STATISTIC.statType
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public void setStattype(Integer stattype) {
		this.stattype = stattype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.objectId
	 * @return  the value of STATISTIC.objectId
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public Integer getObjectid() {
		return objectid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.objectId
	 * @param objectid  the value for STATISTIC.objectId
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}
}
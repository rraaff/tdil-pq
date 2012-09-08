package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Statistic extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.textData
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String textdata;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.statType
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private Integer stattype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.objectId
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private Integer objectid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.objectTime
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private Date objecttime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column STATISTIC.extraId
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private Integer extraid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.textData
	 * @return  the value of STATISTIC.textData
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getTextdata() {
		return textdata;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.textData
	 * @param textdata  the value for STATISTIC.textData
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setTextdata(String textdata) {
		this.textdata = textdata == null ? null : textdata.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.statType
	 * @return  the value of STATISTIC.statType
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public Integer getStattype() {
		return stattype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.statType
	 * @param stattype  the value for STATISTIC.statType
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setStattype(Integer stattype) {
		this.stattype = stattype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.objectId
	 * @return  the value of STATISTIC.objectId
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public Integer getObjectid() {
		return objectid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.objectId
	 * @param objectid  the value for STATISTIC.objectId
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.objectTime
	 * @return  the value of STATISTIC.objectTime
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public Date getObjecttime() {
		return objecttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.objectTime
	 * @param objecttime  the value for STATISTIC.objectTime
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setObjecttime(Date objecttime) {
		this.objecttime = objecttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column STATISTIC.extraId
	 * @return  the value of STATISTIC.extraId
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public Integer getExtraid() {
		return extraid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column STATISTIC.extraId
	 * @param extraid  the value for STATISTIC.extraId
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setExtraid(Integer extraid) {
		this.extraid = extraid;
	}
}
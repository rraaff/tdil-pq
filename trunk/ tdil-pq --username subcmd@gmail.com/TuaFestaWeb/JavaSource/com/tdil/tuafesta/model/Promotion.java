package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Promotion extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION.startDate
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	private Date startdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION.endDate
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	private Date enddate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION.name
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION.description
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION.startDate
	 * @return  the value of PROMOTION.startDate
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION.startDate
	 * @param startdate  the value for PROMOTION.startDate
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION.endDate
	 * @return  the value of PROMOTION.endDate
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION.endDate
	 * @param enddate  the value for PROMOTION.endDate
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION.name
	 * @return  the value of PROMOTION.name
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION.name
	 * @param name  the value for PROMOTION.name
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION.description
	 * @return  the value of PROMOTION.description
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION.description
	 * @param description  the value for PROMOTION.description
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}
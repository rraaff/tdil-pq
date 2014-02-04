package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class Model extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MODEL.name
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MODEL.description
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MODEL.monthWarranty
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer monthwarranty;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MODEL.name
	 * @return  the value of MODEL.name
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MODEL.name
	 * @param name  the value for MODEL.name
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MODEL.description
	 * @return  the value of MODEL.description
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MODEL.description
	 * @param description  the value for MODEL.description
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MODEL.monthWarranty
	 * @return  the value of MODEL.monthWarranty
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getMonthwarranty() {
		return monthwarranty;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MODEL.monthWarranty
	 * @param monthwarranty  the value for MODEL.monthWarranty
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setMonthwarranty(Integer monthwarranty) {
		this.monthwarranty = monthwarranty;
	}
}
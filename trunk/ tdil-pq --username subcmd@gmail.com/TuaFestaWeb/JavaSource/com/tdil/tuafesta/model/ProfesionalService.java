package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class ProfesionalService extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_SERVICE.name
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_SERVICE.description
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_SERVICE.average_price
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	private Integer averagePrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_SERVICE.approved
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_SERVICE.id_category
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	private Integer idCategory;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_SERVICE.name
	 * @return  the value of PROF_SERVICE.name
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_SERVICE.name
	 * @param name  the value for PROF_SERVICE.name
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_SERVICE.description
	 * @return  the value of PROF_SERVICE.description
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_SERVICE.description
	 * @param description  the value for PROF_SERVICE.description
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_SERVICE.average_price
	 * @return  the value of PROF_SERVICE.average_price
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public Integer getAveragePrice() {
		return averagePrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_SERVICE.average_price
	 * @param averagePrice  the value for PROF_SERVICE.average_price
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public void setAveragePrice(Integer averagePrice) {
		this.averagePrice = averagePrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_SERVICE.approved
	 * @return  the value of PROF_SERVICE.approved
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_SERVICE.approved
	 * @param approved  the value for PROF_SERVICE.approved
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_SERVICE.id_category
	 * @return  the value of PROF_SERVICE.id_category
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public Integer getIdCategory() {
		return idCategory;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_SERVICE.id_category
	 * @param idCategory  the value for PROF_SERVICE.id_category
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
}
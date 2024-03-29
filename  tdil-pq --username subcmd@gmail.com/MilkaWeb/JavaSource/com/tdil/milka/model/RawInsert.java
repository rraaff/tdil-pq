package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class RawInsert extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RAW_INSERT.insertType
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String inserttype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RAW_INSERT.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RAW_INSERT.htmlContent
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String htmlcontent;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RAW_INSERT.insertType
	 * @return  the value of RAW_INSERT.insertType
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getInserttype() {
		return inserttype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RAW_INSERT.insertType
	 * @param inserttype  the value for RAW_INSERT.insertType
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setInserttype(String inserttype) {
		this.inserttype = inserttype == null ? null : inserttype.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RAW_INSERT.description
	 * @return  the value of RAW_INSERT.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RAW_INSERT.description
	 * @param description  the value for RAW_INSERT.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RAW_INSERT.htmlContent
	 * @return  the value of RAW_INSERT.htmlContent
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getHtmlcontent() {
		return htmlcontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RAW_INSERT.htmlContent
	 * @param htmlcontent  the value for RAW_INSERT.htmlContent
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
	}
}
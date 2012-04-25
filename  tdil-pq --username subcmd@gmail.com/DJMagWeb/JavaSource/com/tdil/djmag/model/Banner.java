package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Banner extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BANNER.description
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BANNER.htmlContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private String htmlcontent;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BANNER.description
	 * @return  the value of BANNER.description
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BANNER.description
	 * @param description  the value for BANNER.description
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BANNER.htmlContent
	 * @return  the value of BANNER.htmlContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public String getHtmlcontent() {
		return htmlcontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BANNER.htmlContent
	 * @param htmlcontent  the value for BANNER.htmlContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
	}
}
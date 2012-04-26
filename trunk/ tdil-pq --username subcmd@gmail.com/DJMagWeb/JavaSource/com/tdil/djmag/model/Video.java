package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Video extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VIDEO.description
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VIDEO.id_country
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	private Integer idCountry;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VIDEO.frontCover
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	private Integer frontcover;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VIDEO.popular
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	private Integer popular;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VIDEO.htmlContent
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	private String htmlcontent;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VIDEO.description
	 * @return  the value of VIDEO.description
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VIDEO.description
	 * @param description  the value for VIDEO.description
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VIDEO.id_country
	 * @return  the value of VIDEO.id_country
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VIDEO.id_country
	 * @param idCountry  the value for VIDEO.id_country
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VIDEO.frontCover
	 * @return  the value of VIDEO.frontCover
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Integer getFrontcover() {
		return frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VIDEO.frontCover
	 * @param frontcover  the value for VIDEO.frontCover
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setFrontcover(Integer frontcover) {
		this.frontcover = frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VIDEO.popular
	 * @return  the value of VIDEO.popular
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Integer getPopular() {
		return popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VIDEO.popular
	 * @param popular  the value for VIDEO.popular
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VIDEO.htmlContent
	 * @return  the value of VIDEO.htmlContent
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public String getHtmlcontent() {
		return htmlcontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VIDEO.htmlContent
	 * @param htmlcontent  the value for VIDEO.htmlContent
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
	}
}
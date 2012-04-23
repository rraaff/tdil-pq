package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class TwitterFeed extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TWITTER_FEED.id_country
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer idCountry;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TWITTER_FEED.htmlContent
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String htmlcontent;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TWITTER_FEED.id_country
	 * @return  the value of TWITTER_FEED.id_country
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TWITTER_FEED.id_country
	 * @param idCountry  the value for TWITTER_FEED.id_country
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TWITTER_FEED.htmlContent
	 * @return  the value of TWITTER_FEED.htmlContent
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getHtmlcontent() {
		return htmlcontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TWITTER_FEED.htmlContent
	 * @param htmlcontent  the value for TWITTER_FEED.htmlContent
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
	}
}
package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class Footer extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column FOOTER.id_country
	 * @mbggenerated  Thu May 17 12:17:02 ART 2012
	 */
	private Integer idCountry;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column FOOTER.htmlContent
	 * @mbggenerated  Thu May 17 12:17:02 ART 2012
	 */
	private String htmlcontent;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column FOOTER.id_country
	 * @return  the value of FOOTER.id_country
	 * @mbggenerated  Thu May 17 12:17:02 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column FOOTER.id_country
	 * @param idCountry  the value for FOOTER.id_country
	 * @mbggenerated  Thu May 17 12:17:02 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column FOOTER.htmlContent
	 * @return  the value of FOOTER.htmlContent
	 * @mbggenerated  Thu May 17 12:17:02 ART 2012
	 */
	public String getHtmlcontent() {
		return htmlcontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column FOOTER.htmlContent
	 * @param htmlcontent  the value for FOOTER.htmlContent
	 * @mbggenerated  Thu May 17 12:17:02 ART 2012
	 */
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
	}
}
package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class BannerPosition extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BANNER_POSITION.id_country
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	private Integer idCountry;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BANNER_POSITION.id_banner
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	private Integer idBanner;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BANNER_POSITION.position
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	private String position;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BANNER_POSITION.id_country
	 * @return  the value of BANNER_POSITION.id_country
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BANNER_POSITION.id_country
	 * @param idCountry  the value for BANNER_POSITION.id_country
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BANNER_POSITION.id_banner
	 * @return  the value of BANNER_POSITION.id_banner
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public Integer getIdBanner() {
		return idBanner;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BANNER_POSITION.id_banner
	 * @param idBanner  the value for BANNER_POSITION.id_banner
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public void setIdBanner(Integer idBanner) {
		this.idBanner = idBanner;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BANNER_POSITION.position
	 * @return  the value of BANNER_POSITION.position
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BANNER_POSITION.position
	 * @param position  the value for BANNER_POSITION.position
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}
}
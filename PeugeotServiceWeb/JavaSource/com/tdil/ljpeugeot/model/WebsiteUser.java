package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class WebsiteUser extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.lojackUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private String lojackuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.peugeotUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private String peugeotuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.homeUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private String homeuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.petUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private String petuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.preventUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private String preventuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.id_avatar
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private Integer idAvatar;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEBSITEUSER.ext_avatar
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	private String extAvatar;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.lojackUserId
	 * @return  the value of WEBSITEUSER.lojackUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public String getLojackuserid() {
		return lojackuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.lojackUserId
	 * @param lojackuserid  the value for WEBSITEUSER.lojackUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setLojackuserid(String lojackuserid) {
		this.lojackuserid = lojackuserid == null ? null : lojackuserid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.peugeotUserId
	 * @return  the value of WEBSITEUSER.peugeotUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public String getPeugeotuserid() {
		return peugeotuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.peugeotUserId
	 * @param peugeotuserid  the value for WEBSITEUSER.peugeotUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setPeugeotuserid(String peugeotuserid) {
		this.peugeotuserid = peugeotuserid == null ? null : peugeotuserid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.homeUserId
	 * @return  the value of WEBSITEUSER.homeUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public String getHomeuserid() {
		return homeuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.homeUserId
	 * @param homeuserid  the value for WEBSITEUSER.homeUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setHomeuserid(String homeuserid) {
		this.homeuserid = homeuserid == null ? null : homeuserid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.petUserId
	 * @return  the value of WEBSITEUSER.petUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public String getPetuserid() {
		return petuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.petUserId
	 * @param petuserid  the value for WEBSITEUSER.petUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setPetuserid(String petuserid) {
		this.petuserid = petuserid == null ? null : petuserid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.preventUserId
	 * @return  the value of WEBSITEUSER.preventUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public String getPreventuserid() {
		return preventuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.preventUserId
	 * @param preventuserid  the value for WEBSITEUSER.preventUserId
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setPreventuserid(String preventuserid) {
		this.preventuserid = preventuserid == null ? null : preventuserid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.id_avatar
	 * @return  the value of WEBSITEUSER.id_avatar
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public Integer getIdAvatar() {
		return idAvatar;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.id_avatar
	 * @param idAvatar  the value for WEBSITEUSER.id_avatar
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setIdAvatar(Integer idAvatar) {
		this.idAvatar = idAvatar;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEBSITEUSER.ext_avatar
	 * @return  the value of WEBSITEUSER.ext_avatar
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public String getExtAvatar() {
		return extAvatar;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEBSITEUSER.ext_avatar
	 * @param extAvatar  the value for WEBSITEUSER.ext_avatar
	 * @mbggenerated  Fri Jan 31 19:31:54 ART 2014
	 */
	public void setExtAvatar(String extAvatar) {
		this.extAvatar = extAvatar == null ? null : extAvatar.trim();
	}
}
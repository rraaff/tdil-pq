package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class MilkaPhotoTag extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO_TAG.id_milka_photo
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idMilkaPhoto;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO_TAG.id_tag
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idTag;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO_TAG.id_milka_photo
	 * @return  the value of MILKA_PHOTO_TAG.id_milka_photo
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdMilkaPhoto() {
		return idMilkaPhoto;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO_TAG.id_milka_photo
	 * @param idMilkaPhoto  the value for MILKA_PHOTO_TAG.id_milka_photo
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdMilkaPhoto(Integer idMilkaPhoto) {
		this.idMilkaPhoto = idMilkaPhoto;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO_TAG.id_tag
	 * @return  the value of MILKA_PHOTO_TAG.id_tag
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdTag() {
		return idTag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO_TAG.id_tag
	 * @param idTag  the value for MILKA_PHOTO_TAG.id_tag
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdTag(Integer idTag) {
		this.idTag = idTag;
	}
}
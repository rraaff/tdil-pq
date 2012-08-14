package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class ProfilePicture extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFILE_PICTURE.id_blob_data
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	private Integer idBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFILE_PICTURE.ext_blob_data
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	private String extBlobData;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFILE_PICTURE.id_blob_data
	 * @return  the value of PROFILE_PICTURE.id_blob_data
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public Integer getIdBlobData() {
		return idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFILE_PICTURE.id_blob_data
	 * @param idBlobData  the value for PROFILE_PICTURE.id_blob_data
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public void setIdBlobData(Integer idBlobData) {
		this.idBlobData = idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFILE_PICTURE.ext_blob_data
	 * @return  the value of PROFILE_PICTURE.ext_blob_data
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public String getExtBlobData() {
		return extBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFILE_PICTURE.ext_blob_data
	 * @param extBlobData  the value for PROFILE_PICTURE.ext_blob_data
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public void setExtBlobData(String extBlobData) {
		this.extBlobData = extBlobData == null ? null : extBlobData.trim();
	}
}
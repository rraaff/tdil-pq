package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class PromotionPhoto extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_PHOTO.id_promotion
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer idPromotion;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_PHOTO.orderNumber
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer ordernumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_PHOTO.id_blob_data
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer idBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_PHOTO.ext_blob_data
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private String extBlobData;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_PHOTO.id_promotion
	 * @return  the value of PROMOTION_PHOTO.id_promotion
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getIdPromotion() {
		return idPromotion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_PHOTO.id_promotion
	 * @param idPromotion  the value for PROMOTION_PHOTO.id_promotion
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_PHOTO.orderNumber
	 * @return  the value of PROMOTION_PHOTO.orderNumber
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getOrdernumber() {
		return ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_PHOTO.orderNumber
	 * @param ordernumber  the value for PROMOTION_PHOTO.orderNumber
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_PHOTO.id_blob_data
	 * @return  the value of PROMOTION_PHOTO.id_blob_data
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getIdBlobData() {
		return idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_PHOTO.id_blob_data
	 * @param idBlobData  the value for PROMOTION_PHOTO.id_blob_data
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setIdBlobData(Integer idBlobData) {
		this.idBlobData = idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_PHOTO.ext_blob_data
	 * @return  the value of PROMOTION_PHOTO.ext_blob_data
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public String getExtBlobData() {
		return extBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_PHOTO.ext_blob_data
	 * @param extBlobData  the value for PROMOTION_PHOTO.ext_blob_data
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setExtBlobData(String extBlobData) {
		this.extBlobData = extBlobData == null ? null : extBlobData.trim();
	}
}
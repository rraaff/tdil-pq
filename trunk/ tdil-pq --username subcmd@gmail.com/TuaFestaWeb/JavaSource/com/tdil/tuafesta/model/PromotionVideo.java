package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class PromotionVideo extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_VIDEO.id_promotion
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	private Integer idPromotion;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_VIDEO.orderNumber
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	private Integer ordernumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_VIDEO.approved
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_VIDEO.url
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	private String url;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_VIDEO.id_promotion
	 * @return  the value of PROMOTION_VIDEO.id_promotion
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public Integer getIdPromotion() {
		return idPromotion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_VIDEO.id_promotion
	 * @param idPromotion  the value for PROMOTION_VIDEO.id_promotion
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_VIDEO.orderNumber
	 * @return  the value of PROMOTION_VIDEO.orderNumber
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public Integer getOrdernumber() {
		return ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_VIDEO.orderNumber
	 * @param ordernumber  the value for PROMOTION_VIDEO.orderNumber
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_VIDEO.approved
	 * @return  the value of PROMOTION_VIDEO.approved
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_VIDEO.approved
	 * @param approved  the value for PROMOTION_VIDEO.approved
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_VIDEO.url
	 * @return  the value of PROMOTION_VIDEO.url
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_VIDEO.url
	 * @param url  the value for PROMOTION_VIDEO.url
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}
}
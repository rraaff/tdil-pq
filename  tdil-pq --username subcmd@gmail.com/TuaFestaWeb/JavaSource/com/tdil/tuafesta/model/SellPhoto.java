package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class SellPhoto extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_PHOTO.id_sell
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer idSell;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_PHOTO.orderNumber
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer ordernumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_PHOTO.approved
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_PHOTO.id_blob_data
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer idBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_PHOTO.ext_blob_data
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private String extBlobData;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_PHOTO.id_sell
	 * @return  the value of SELL_PHOTO.id_sell
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getIdSell() {
		return idSell;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_PHOTO.id_sell
	 * @param idSell  the value for SELL_PHOTO.id_sell
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setIdSell(Integer idSell) {
		this.idSell = idSell;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_PHOTO.orderNumber
	 * @return  the value of SELL_PHOTO.orderNumber
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getOrdernumber() {
		return ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_PHOTO.orderNumber
	 * @param ordernumber  the value for SELL_PHOTO.orderNumber
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_PHOTO.approved
	 * @return  the value of SELL_PHOTO.approved
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_PHOTO.approved
	 * @param approved  the value for SELL_PHOTO.approved
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_PHOTO.id_blob_data
	 * @return  the value of SELL_PHOTO.id_blob_data
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getIdBlobData() {
		return idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_PHOTO.id_blob_data
	 * @param idBlobData  the value for SELL_PHOTO.id_blob_data
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setIdBlobData(Integer idBlobData) {
		this.idBlobData = idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_PHOTO.ext_blob_data
	 * @return  the value of SELL_PHOTO.ext_blob_data
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public String getExtBlobData() {
		return extBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_PHOTO.ext_blob_data
	 * @param extBlobData  the value for SELL_PHOTO.ext_blob_data
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setExtBlobData(String extBlobData) {
		this.extBlobData = extBlobData == null ? null : extBlobData.trim();
	}
}
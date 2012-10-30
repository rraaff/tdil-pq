package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class SellMedia extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.id_sell
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer idSell;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.video1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String video1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.video2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String video2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.video3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String video3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.video4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String video4;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.video5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String video5;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.id_blob_data1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer idBlobData1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.ext_blob_data1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String extBlobData1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.id_blob_data2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer idBlobData2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.ext_blob_data2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String extBlobData2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.id_blob_data3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer idBlobData3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.ext_blob_data3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String extBlobData3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.id_blob_data4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer idBlobData4;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.ext_blob_data4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String extBlobData4;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.id_blob_data5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer idBlobData5;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.ext_blob_data5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private String extBlobData5;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL_MEDIA.approved
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	private Integer approved;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.id_sell
	 * @return  the value of SELL_MEDIA.id_sell
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getIdSell() {
		return idSell;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.id_sell
	 * @param idSell  the value for SELL_MEDIA.id_sell
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setIdSell(Integer idSell) {
		this.idSell = idSell;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.video1
	 * @return  the value of SELL_MEDIA.video1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getVideo1() {
		return video1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.video1
	 * @param video1  the value for SELL_MEDIA.video1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setVideo1(String video1) {
		this.video1 = video1 == null ? null : video1.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.video2
	 * @return  the value of SELL_MEDIA.video2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getVideo2() {
		return video2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.video2
	 * @param video2  the value for SELL_MEDIA.video2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setVideo2(String video2) {
		this.video2 = video2 == null ? null : video2.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.video3
	 * @return  the value of SELL_MEDIA.video3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getVideo3() {
		return video3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.video3
	 * @param video3  the value for SELL_MEDIA.video3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setVideo3(String video3) {
		this.video3 = video3 == null ? null : video3.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.video4
	 * @return  the value of SELL_MEDIA.video4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getVideo4() {
		return video4;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.video4
	 * @param video4  the value for SELL_MEDIA.video4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setVideo4(String video4) {
		this.video4 = video4 == null ? null : video4.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.video5
	 * @return  the value of SELL_MEDIA.video5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getVideo5() {
		return video5;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.video5
	 * @param video5  the value for SELL_MEDIA.video5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setVideo5(String video5) {
		this.video5 = video5 == null ? null : video5.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.id_blob_data1
	 * @return  the value of SELL_MEDIA.id_blob_data1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getIdBlobData1() {
		return idBlobData1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.id_blob_data1
	 * @param idBlobData1  the value for SELL_MEDIA.id_blob_data1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setIdBlobData1(Integer idBlobData1) {
		this.idBlobData1 = idBlobData1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.ext_blob_data1
	 * @return  the value of SELL_MEDIA.ext_blob_data1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getExtBlobData1() {
		return extBlobData1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.ext_blob_data1
	 * @param extBlobData1  the value for SELL_MEDIA.ext_blob_data1
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setExtBlobData1(String extBlobData1) {
		this.extBlobData1 = extBlobData1 == null ? null : extBlobData1.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.id_blob_data2
	 * @return  the value of SELL_MEDIA.id_blob_data2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getIdBlobData2() {
		return idBlobData2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.id_blob_data2
	 * @param idBlobData2  the value for SELL_MEDIA.id_blob_data2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setIdBlobData2(Integer idBlobData2) {
		this.idBlobData2 = idBlobData2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.ext_blob_data2
	 * @return  the value of SELL_MEDIA.ext_blob_data2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getExtBlobData2() {
		return extBlobData2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.ext_blob_data2
	 * @param extBlobData2  the value for SELL_MEDIA.ext_blob_data2
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setExtBlobData2(String extBlobData2) {
		this.extBlobData2 = extBlobData2 == null ? null : extBlobData2.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.id_blob_data3
	 * @return  the value of SELL_MEDIA.id_blob_data3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getIdBlobData3() {
		return idBlobData3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.id_blob_data3
	 * @param idBlobData3  the value for SELL_MEDIA.id_blob_data3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setIdBlobData3(Integer idBlobData3) {
		this.idBlobData3 = idBlobData3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.ext_blob_data3
	 * @return  the value of SELL_MEDIA.ext_blob_data3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getExtBlobData3() {
		return extBlobData3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.ext_blob_data3
	 * @param extBlobData3  the value for SELL_MEDIA.ext_blob_data3
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setExtBlobData3(String extBlobData3) {
		this.extBlobData3 = extBlobData3 == null ? null : extBlobData3.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.id_blob_data4
	 * @return  the value of SELL_MEDIA.id_blob_data4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getIdBlobData4() {
		return idBlobData4;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.id_blob_data4
	 * @param idBlobData4  the value for SELL_MEDIA.id_blob_data4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setIdBlobData4(Integer idBlobData4) {
		this.idBlobData4 = idBlobData4;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.ext_blob_data4
	 * @return  the value of SELL_MEDIA.ext_blob_data4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getExtBlobData4() {
		return extBlobData4;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.ext_blob_data4
	 * @param extBlobData4  the value for SELL_MEDIA.ext_blob_data4
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setExtBlobData4(String extBlobData4) {
		this.extBlobData4 = extBlobData4 == null ? null : extBlobData4.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.id_blob_data5
	 * @return  the value of SELL_MEDIA.id_blob_data5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getIdBlobData5() {
		return idBlobData5;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.id_blob_data5
	 * @param idBlobData5  the value for SELL_MEDIA.id_blob_data5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setIdBlobData5(Integer idBlobData5) {
		this.idBlobData5 = idBlobData5;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.ext_blob_data5
	 * @return  the value of SELL_MEDIA.ext_blob_data5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public String getExtBlobData5() {
		return extBlobData5;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.ext_blob_data5
	 * @param extBlobData5  the value for SELL_MEDIA.ext_blob_data5
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setExtBlobData5(String extBlobData5) {
		this.extBlobData5 = extBlobData5 == null ? null : extBlobData5.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL_MEDIA.approved
	 * @return  the value of SELL_MEDIA.approved
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL_MEDIA.approved
	 * @param approved  the value for SELL_MEDIA.approved
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
}
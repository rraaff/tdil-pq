package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class RankingPositionImage extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_POS_IMG.id_ranking_pos
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private Integer idRankingPos;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_POS_IMG.imageExt
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private String imageext;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_POS_IMG.image_id
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private Integer imageId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_POS_IMG.orderNumber
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private Integer ordernumber;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_POS_IMG.id_ranking_pos
	 * @return  the value of RANKING_POS_IMG.id_ranking_pos
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer getIdRankingPos() {
		return idRankingPos;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_POS_IMG.id_ranking_pos
	 * @param idRankingPos  the value for RANKING_POS_IMG.id_ranking_pos
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setIdRankingPos(Integer idRankingPos) {
		this.idRankingPos = idRankingPos;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_POS_IMG.imageExt
	 * @return  the value of RANKING_POS_IMG.imageExt
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public String getImageext() {
		return imageext;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_POS_IMG.imageExt
	 * @param imageext  the value for RANKING_POS_IMG.imageExt
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setImageext(String imageext) {
		this.imageext = imageext == null ? null : imageext.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_POS_IMG.image_id
	 * @return  the value of RANKING_POS_IMG.image_id
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer getImageId() {
		return imageId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_POS_IMG.image_id
	 * @param imageId  the value for RANKING_POS_IMG.image_id
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_POS_IMG.orderNumber
	 * @return  the value of RANKING_POS_IMG.orderNumber
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer getOrdernumber() {
		return ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_POS_IMG.orderNumber
	 * @param ordernumber  the value for RANKING_POS_IMG.orderNumber
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}
}
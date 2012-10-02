package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.math.BigDecimal;

public class PromotionSell extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_SELL.id_promotion
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	private Integer idPromotion;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_SELL.referenceprice
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	private BigDecimal referenceprice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROMOTION_SELL.id_sell
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	private Integer idSell;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_SELL.id_promotion
	 * @return  the value of PROMOTION_SELL.id_promotion
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	public Integer getIdPromotion() {
		return idPromotion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_SELL.id_promotion
	 * @param idPromotion  the value for PROMOTION_SELL.id_promotion
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_SELL.referenceprice
	 * @return  the value of PROMOTION_SELL.referenceprice
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	public BigDecimal getReferenceprice() {
		return referenceprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_SELL.referenceprice
	 * @param referenceprice  the value for PROMOTION_SELL.referenceprice
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	public void setReferenceprice(BigDecimal referenceprice) {
		this.referenceprice = referenceprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROMOTION_SELL.id_sell
	 * @return  the value of PROMOTION_SELL.id_sell
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	public Integer getIdSell() {
		return idSell;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROMOTION_SELL.id_sell
	 * @param idSell  the value for PROMOTION_SELL.id_sell
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	public void setIdSell(Integer idSell) {
		this.idSell = idSell;
	}
}
package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.PromotionSell;
import com.tdil.tuafesta.model.PromotionSellExample;
import java.sql.SQLException;
import java.util.List;

public interface PromotionSellDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int countPromotionSellByExample(PromotionSellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int deletePromotionSellByExample(PromotionSellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int deletePromotionSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	Integer insertPromotionSell(PromotionSell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	Integer insertPromotionSellSelective(PromotionSell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	List<PromotionSell> selectPromotionSellByExample(PromotionSellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	PromotionSell selectPromotionSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int updatePromotionSellByExampleSelective(PromotionSell record, PromotionSellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int updatePromotionSellByExample(PromotionSell record, PromotionSellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int updatePromotionSellByPrimaryKeySelective(PromotionSell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	int updatePromotionSellByPrimaryKey(PromotionSell record) throws SQLException;
}
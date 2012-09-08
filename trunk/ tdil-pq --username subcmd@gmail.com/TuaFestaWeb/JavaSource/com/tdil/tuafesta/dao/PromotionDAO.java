package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionExample;
import java.sql.SQLException;
import java.util.List;

public interface PromotionDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int countPromotionByExample(PromotionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int deletePromotionByExample(PromotionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int deletePromotionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	Integer insertPromotion(Promotion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	Integer insertPromotionSelective(Promotion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	List<Promotion> selectPromotionByExample(PromotionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	Promotion selectPromotionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int updatePromotionByExampleSelective(Promotion record, PromotionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int updatePromotionByExample(Promotion record, PromotionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int updatePromotionByPrimaryKeySelective(Promotion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	int updatePromotionByPrimaryKey(Promotion record) throws SQLException;
}
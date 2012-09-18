package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.PromotionVideo;
import com.tdil.tuafesta.model.PromotionVideoExample;
import java.sql.SQLException;
import java.util.List;

public interface PromotionVideoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int countPromotionVideoByExample(PromotionVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int deletePromotionVideoByExample(PromotionVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int deletePromotionVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	Integer insertPromotionVideo(PromotionVideo record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	Integer insertPromotionVideoSelective(PromotionVideo record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	List<PromotionVideo> selectPromotionVideoByExample(PromotionVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	PromotionVideo selectPromotionVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int updatePromotionVideoByExampleSelective(PromotionVideo record, PromotionVideoExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int updatePromotionVideoByExample(PromotionVideo record, PromotionVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int updatePromotionVideoByPrimaryKeySelective(PromotionVideo record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	int updatePromotionVideoByPrimaryKey(PromotionVideo record) throws SQLException;
}
package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.SellVideo;
import com.tdil.tuafesta.model.SellVideoExample;
import java.sql.SQLException;
import java.util.List;

public interface SellVideoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int countSellVideoByExample(SellVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int deleteSellVideoByExample(SellVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int deleteSellVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	Integer insertSellVideo(SellVideo record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	Integer insertSellVideoSelective(SellVideo record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	List<SellVideo> selectSellVideoByExample(SellVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	SellVideo selectSellVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int updateSellVideoByExampleSelective(SellVideo record, SellVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int updateSellVideoByExample(SellVideo record, SellVideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int updateSellVideoByPrimaryKeySelective(SellVideo record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	int updateSellVideoByPrimaryKey(SellVideo record) throws SQLException;
}
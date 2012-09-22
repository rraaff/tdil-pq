package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.SellMedia;
import com.tdil.tuafesta.model.SellMediaExample;
import java.sql.SQLException;
import java.util.List;

public interface SellMediaDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int countSellMediaByExample(SellMediaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int deleteSellMediaByExample(SellMediaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int deleteSellMediaByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	Integer insertSellMedia(SellMedia record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	Integer insertSellMediaSelective(SellMedia record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	List<SellMedia> selectSellMediaByExample(SellMediaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	SellMedia selectSellMediaByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int updateSellMediaByExampleSelective(SellMedia record, SellMediaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int updateSellMediaByExample(SellMedia record, SellMediaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int updateSellMediaByPrimaryKeySelective(SellMedia record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	int updateSellMediaByPrimaryKey(SellMedia record) throws SQLException;
}
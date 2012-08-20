package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import java.sql.SQLException;
import java.util.List;

public interface SellDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int countSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int deleteSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int deleteSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	Integer insertSell(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	Integer insertSellSelective(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	List<Sell> selectSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	Sell selectSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateSellByExampleSelective(Sell record, SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateSellByExample(Sell record, SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateSellByPrimaryKeySelective(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateSellByPrimaryKey(Sell record) throws SQLException;
}
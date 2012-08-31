package com.tdil.tuafesta.dao;

import java.sql.SQLException;
import java.util.List;

import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

public interface SellDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int countSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int deleteSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int deleteSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	Integer insertSell(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	Integer insertSellSelective(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	List<Sell> selectSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	Sell selectSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateSellByExampleSelective(Sell record, SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateSellByExample(Sell record, SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateSellByPrimaryKeySelective(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateSellByPrimaryKey(Sell record) throws SQLException;

	public List<SellValueObject> selectSellsByGeo4(Geo4 geo4) throws SQLException;
	
	public List<SellValueObject> selectProductSellsByCategory(int catid) throws SQLException;
	public List<SellValueObject> selectProductSellsByCategories(List<Integer> catids) throws SQLException;
	
	public List<SellValueObject> selectServiceSellsByCategory(int catid) throws SQLException;
	public List<SellValueObject> selectServiceSellsByCategories(List<Integer> catids) throws SQLException;

	public SellValueObject selectSellProductValueObject(int id) throws SQLException;

	public SellValueObject selectSellServiceValueObject(int id) throws SQLException;
}
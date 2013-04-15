package com.tdil.tuafesta.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

public interface SellDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int countSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int deleteSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int deleteSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	Integer insertSell(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	Integer insertSellSelective(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	List<Sell> selectSellByExample(SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	Sell selectSellByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateSellByExampleSelective(Sell record, SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateSellByExample(Sell record, SellExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateSellByPrimaryKeySelective(Sell record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateSellByPrimaryKey(Sell record) throws SQLException;

	public List<SellValueObject> selectSellsByGeo4(Geo4 geo4) throws SQLException;
	
	public List<SellValueObject> selectProductSellsByCategory(int catid) throws SQLException;
	public List<SellValueObject> selectProductSellsByCategories(List<Integer> catids) throws SQLException;
	public List<SellValueObject> selectProductSellsByProfesional(int id) throws SQLException;
	public List<SellValueObject> selectApprovedProductSellsByProfesional(int id) throws SQLException;
	
	public List<SellValueObject> selectServiceSellsByCategory(int catid) throws SQLException;
	public List<SellValueObject> selectServiceSellsByCategories(List<Integer> catids) throws SQLException;
	public List<SellValueObject> selectServiceSellsByProfesional(int id) throws SQLException;
	public List<SellValueObject> selectApprovedServiceSellsByProfesional(int id) throws SQLException;

	public SellValueObject selectSellProductValueObject(int id) throws SQLException;
	public SellValueObject selectSellProductValueObjectNoFilter(int id) throws SQLException;

	public SellValueObject selectSellServiceValueObject(int id) throws SQLException;
	public SellValueObject selectSellServiceValueObjectNoFilter(int id) throws SQLException;

	public List<SellValueObject> searchProductsSellsBy(String name, String profesionalBusinessname) throws SQLException;

	public List<SellValueObject> searchServicesSellsBy(String name, String profesionalBusinessname) throws SQLException;

	List<SellValueObject> selectSellsForPromotion(int promotionId) throws SQLException;

	List<SellValueObject> selectSellsByTerms(List<String> terms) throws SQLException;
	
	List<SellValueObject> selectSellsByTextAndPrice(String text, String price) throws SQLException;

	Collection<? extends SellValueObject> selectSellsProductsByTextAndPrice(String product, String maxPrice) throws SQLException;

	Collection<? extends SellValueObject> selectSellsServicesByTextAndPriceAndGeoLevel(String product, String maxPrice, int geoId) throws SQLException;

	Collection<? extends SellValueObject> selectProductSellsByCategoriesAndPrice(List<Integer> categoriesIds,
			String maxPrice) throws SQLException;

	Collection<? extends SellValueObject> selectServiceSellsByCategoriesAndPrice(List<Integer> categoriesIds,
			String maxPrice) throws SQLException;

	Collection<? extends SellValueObject> selectServicesSellsByCategoriesAndPriceAndGeoLevel(
			List<Integer> categoriesIds, String maxPrice, Map<String, Object> geolevels) throws SQLException;
	
	Collection<? extends SellValueObject> selectServicesSellsByGeoLevel(
			Map<String, Object> geolevels) throws SQLException;

	Collection<? extends SellValueObject> selectProductSellsByCategoriesAndPriceAndGeoLevel(
			List<Integer> categoriesIds, String maxPrice,
			Map<String, Object> geosearch) throws SQLException;
	
	Collection<? extends SellValueObject> selectProductSellsByGeoLevel(
			Map<String, Object> geosearch) throws SQLException;

}
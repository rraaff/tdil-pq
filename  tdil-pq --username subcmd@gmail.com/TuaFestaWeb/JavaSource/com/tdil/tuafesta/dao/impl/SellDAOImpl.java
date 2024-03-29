package com.tdil.tuafesta.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

public class SellDAOImpl implements SellDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public SellDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int countSellByExample(SellExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SELL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteSellByExample(SellExample example) throws SQLException {
		int rows = sqlMapClient.delete("SELL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteSellByPrimaryKey(Integer id) throws SQLException {
		Sell _key = new Sell();
		_key.setId(id);
		int rows = sqlMapClient.delete("SELL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertSell(Sell record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertSellSelective(Sell record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Sell> selectSellByExample(SellExample example) throws SQLException {
		List<Sell> list = sqlMapClient.queryForList("SELL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Sell selectSellByPrimaryKey(Integer id) throws SQLException {
		Sell _key = new Sell();
		_key.setId(id);
		Sell record = (Sell) sqlMapClient.queryForObject("SELL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellByExampleSelective(Sell record, SellExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellByExample(Sell record, SellExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellByPrimaryKeySelective(Sell record) throws SQLException {
		int rows = sqlMapClient.update("SELL.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellByPrimaryKey(Sell record) throws SQLException {
		int rows = sqlMapClient.update("SELL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SELL
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected static class UpdateByExampleParms extends SellExample {
		private Object record;

		public UpdateByExampleParms(Object record, SellExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<SellValueObject> selectSellsByGeo4(Geo4 geo4) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("geo4id", geo4.getId());
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellProductsByGeo4", params);
		//list.addAll((List<SellValueObject>)sqlMapClient.queryForList("SELL.searchSellServicesByGeo4", params));
		return list;
	}
	
	public List<SellValueObject> selectProductSellsByCategory(int catid) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("catid", catid);
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellProductsByCategory", params);
		return list;
	}
	
	public List<SellValueObject> selectProductSellsByProfesional(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellProductsByProfesional", params);
		return list;
	}
	
	public List<SellValueObject> selectApprovedProductSellsByProfesional(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("approved", 1);
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellProductsByProfesional", params);
		return list;
	}
	
	public List<SellValueObject> selectProductSellsByCategories(List<Integer> catids) throws SQLException {
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellProductsByCategories", catids);
		return list;
	}
	
	public List<SellValueObject> selectServiceSellsByCategory(int catid) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("catid", catid);
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellServicesByCategory", params);
		return list;
	}
	
	public List<SellValueObject> selectServiceSellsByProfesional(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellServicesByProfesional", params);
		return list;
	}
	
	public List<SellValueObject> selectApprovedServiceSellsByProfesional(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("approved", 1);
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellServicesByProfesional", params);
		return list;
	}
	
	public List<SellValueObject> selectServiceSellsByCategories(List<Integer> catids) throws SQLException {
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchSellServicesByCategories", catids);
		return list;
	}
	
	public SellValueObject selectSellProductValueObject(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("approved", Boolean.TRUE);
		SellValueObject record = (SellValueObject) sqlMapClient.queryForObject("SELL.selectSellProductValueObject", params);
		return record;
	}
	
	@Override
	public SellValueObject selectSellProductValueObjectNoFilter(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		SellValueObject record = (SellValueObject) sqlMapClient.queryForObject("SELL.selectSellProductValueObject", params);
		return record;
	}

	public SellValueObject selectSellServiceValueObject(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("approved", Boolean.TRUE);
		SellValueObject record = (SellValueObject) sqlMapClient.queryForObject("SELL.selectSellServiceValueObject", params);
		return record;
	}
	@Override
	public SellValueObject selectSellServiceValueObjectNoFilter(int id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		SellValueObject record = (SellValueObject) sqlMapClient.queryForObject("SELL.selectSellServiceValueObject", params);
		return record;
	}
	
	@Override
	public List<SellValueObject> searchProductsSellsBy(String name, String profesionalBusinessname) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(name)) {
			params.put("name", "%"+name+"%");
		}
		if (!StringUtils.isEmpty(profesionalBusinessname)) {
			params.put("profesionalBusinessname", "%"+profesionalBusinessname+"%");
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchProductsSellsBy", params);
		return list;
	}
	@Override
	public List<SellValueObject> searchServicesSellsBy(String name, String profesionalBusinessname) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(name)) {
			params.put("name", "%"+name+"%");
		}
		if (!StringUtils.isEmpty(profesionalBusinessname)) {
			params.put("profesionalBusinessname", "%"+profesionalBusinessname+"%");
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.searchServicesSellsBy", params);
		return list;
	}
	
	@Override
	public List<SellValueObject> selectSellsForPromotion(int promotionId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promotionId", promotionId);
		List<SellValueObject> record = sqlMapClient.queryForList("SELL.selectSellsForPromotion", params);
		return record;
	}
	
	@Override
	public List<SellValueObject> selectSellsByTerms(List<String> terms) throws SQLException {
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectSellsByTerms", terms);
		return list;
	}
	
	@Override
	public List<SellValueObject> selectSellsByTextAndPrice(String text, String price) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", "%"+text+"%");
		if (price != null && price.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(price));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectSellsByTextAndPrice", params);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectSellsProductsByTextAndPrice(String product, String maxPrice)
			throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", "%"+product+"%");
		if (maxPrice != null && maxPrice.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(maxPrice));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectSellsProductsByTextAndPrice", params);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectSellsServicesByTextAndPriceAndGeoLevel(String product,
			String maxPrice, int geoId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", "%"+product+"%");
		params.put("geoId", geoId);
		if (maxPrice != null && maxPrice.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(maxPrice));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectSellsServicesByTextAndPriceAndGeoLevel", params);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectProductSellsByCategoriesAndPrice(List<Integer> categoriesIds,
			String maxPrice) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("categories", categoriesIds);
		if (maxPrice != null && maxPrice.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(maxPrice));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectProductSellsByCategoriesAndPrice", params);
		return list;
	}

	@Override
	public Collection<? extends SellValueObject> selectServiceSellsByCategoriesAndPrice(List<Integer> categoriesIds,
			String maxPrice) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("categories", categoriesIds);
		if (maxPrice != null && maxPrice.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(maxPrice));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectServiceSellsByCategoriesAndPrice", params);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectServicesSellsByCategoriesAndPriceAndGeoLevel(
			List<Integer> categoriesIds, String maxPrice, Map<String, Object> geolevels) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.putAll(geolevels);
		params.put("categories", categoriesIds);
		if (maxPrice != null && maxPrice.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(maxPrice));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectServicesSellsByCategoriesAndPriceAndGeoLevel", params);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectServicesSellsByGeoLevel(
			Map<String, Object> geolevels) throws SQLException {
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectServicesSellsByGeoLevel", geolevels);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectProductSellsByCategoriesAndPriceAndGeoLevel(
			List<Integer> categoriesIds, String maxPrice,
			Map<String, Object> geolevels) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.putAll(geolevels);
		params.put("categories", categoriesIds);
		if (maxPrice != null && maxPrice.trim().length() > 0) {
			params.put("maxPrice", Integer.valueOf(maxPrice));
		}
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectProductSellsByCategoriesAndPriceAndGeoLevel", params);
		return list;
	}
	
	@Override
	public Collection<? extends SellValueObject> selectProductSellsByGeoLevel(
			Map<String, Object> geolevels) throws SQLException {
		List<SellValueObject> list = sqlMapClient.queryForList("SELL.selectProductSellsByGeoLevel", geolevels);
		return list;
	}
}
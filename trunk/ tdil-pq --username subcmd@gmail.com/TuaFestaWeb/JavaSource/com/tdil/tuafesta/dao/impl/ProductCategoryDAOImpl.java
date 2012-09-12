package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProductCategoryDAO;
import com.tdil.tuafesta.model.ProductCategory;
import com.tdil.tuafesta.model.ProductCategoryExample;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCategoryDAOImpl implements ProductCategoryDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public ProductCategoryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int countProductCategoryByExample(ProductCategoryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROF_PROD_CATEGORY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int deleteProductCategoryByExample(ProductCategoryExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROF_PROD_CATEGORY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int deleteProductCategoryByPrimaryKey(Integer id) throws SQLException {
		ProductCategory _key = new ProductCategory();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROF_PROD_CATEGORY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public Integer insertProductCategory(ProductCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_PROD_CATEGORY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public Integer insertProductCategorySelective(ProductCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_PROD_CATEGORY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> selectProductCategoryByExample(ProductCategoryExample example) throws SQLException {
		List<ProductCategory> list = sqlMapClient.queryForList("PROF_PROD_CATEGORY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public ProductCategory selectProductCategoryByPrimaryKey(Integer id) throws SQLException {
		ProductCategory _key = new ProductCategory();
		_key.setId(id);
		ProductCategory record = (ProductCategory) sqlMapClient.queryForObject("PROF_PROD_CATEGORY.selectByPrimaryKey",
				_key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int updateProductCategoryByExampleSelective(ProductCategory record, ProductCategoryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_PROD_CATEGORY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int updateProductCategoryByExample(ProductCategory record, ProductCategoryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_PROD_CATEGORY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int updateProductCategoryByPrimaryKeySelective(ProductCategory record) throws SQLException {
		int rows = sqlMapClient.update("PROF_PROD_CATEGORY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public int updateProductCategoryByPrimaryKey(ProductCategory record) throws SQLException {
		int rows = sqlMapClient.update("PROF_PROD_CATEGORY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROF_PROD_CATEGORY
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProductCategoryExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProductCategoryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<CategoryValueObject> selectTopCategories() throws SQLException {
		List<CategoryValueObject> list = sqlMapClient.queryForList("PROF_PROD_CATEGORY.selectTopCategories");
		return list;
	}
	
	public List<CategoryValueObject> selectAllCategories() throws SQLException {
		List<CategoryValueObject> list = sqlMapClient.queryForList("PROF_PROD_CATEGORY.selectAllCategories");
		return list;
	}
}
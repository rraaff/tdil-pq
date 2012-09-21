package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ServiceCategoryDAO;
import com.tdil.tuafesta.model.ServiceCategory;
import com.tdil.tuafesta.model.ServiceCategoryExample;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;

import java.sql.SQLException;
import java.util.List;

public class ServiceCategoryDAOImpl implements ServiceCategoryDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public ServiceCategoryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int countServiceCategoryByExample(ServiceCategoryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROF_SERV_CATEGORY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int deleteServiceCategoryByExample(ServiceCategoryExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROF_SERV_CATEGORY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int deleteServiceCategoryByPrimaryKey(Integer id) throws SQLException {
		ServiceCategory _key = new ServiceCategory();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROF_SERV_CATEGORY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public Integer insertServiceCategory(ServiceCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_SERV_CATEGORY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public Integer insertServiceCategorySelective(ServiceCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_SERV_CATEGORY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceCategory> selectServiceCategoryByExample(ServiceCategoryExample example) throws SQLException {
		List<ServiceCategory> list = sqlMapClient.queryForList("PROF_SERV_CATEGORY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public ServiceCategory selectServiceCategoryByPrimaryKey(Integer id) throws SQLException {
		ServiceCategory _key = new ServiceCategory();
		_key.setId(id);
		ServiceCategory record = (ServiceCategory) sqlMapClient.queryForObject("PROF_SERV_CATEGORY.selectByPrimaryKey",
				_key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updateServiceCategoryByExampleSelective(ServiceCategory record, ServiceCategoryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_SERV_CATEGORY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updateServiceCategoryByExample(ServiceCategory record, ServiceCategoryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_SERV_CATEGORY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updateServiceCategoryByPrimaryKeySelective(ServiceCategory record) throws SQLException {
		int rows = sqlMapClient.update("PROF_SERV_CATEGORY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updateServiceCategoryByPrimaryKey(ServiceCategory record) throws SQLException {
		int rows = sqlMapClient.update("PROF_SERV_CATEGORY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	protected static class UpdateByExampleParms extends ServiceCategoryExample {
		private Object record;

		public UpdateByExampleParms(Object record, ServiceCategoryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<CategoryValueObject> selectTopCategories() throws SQLException {
		List<CategoryValueObject> list = sqlMapClient.queryForList("PROF_SERV_CATEGORY.selectTopCategories");
		return list;
	}
	
	public List<CategoryValueObject> selectAllCategories() throws SQLException {
		List<CategoryValueObject> list = sqlMapClient.queryForList("PROF_SERV_CATEGORY.selectAllCategories");
		return list;
	}
}
package com.tdil.ljpeugeot.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.ModelDAO;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import java.sql.SQLException;
import java.util.List;

public class ModelDAOImpl implements ModelDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public ModelDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int countModelByExample(ModelExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("MODEL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int deleteModelByExample(ModelExample example) throws SQLException {
		int rows = sqlMapClient.delete("MODEL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int deleteModelByPrimaryKey(Integer id) throws SQLException {
		Model _key = new Model();
		_key.setId(id);
		int rows = sqlMapClient.delete("MODEL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public Integer insertModel(Model record) throws SQLException {
		Object newKey = sqlMapClient.insert("MODEL.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public Integer insertModelSelective(Model record) throws SQLException {
		Object newKey = sqlMapClient.insert("MODEL.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<Model> selectModelByExample(ModelExample example) throws SQLException {
		List<Model> list = sqlMapClient.queryForList("MODEL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public Model selectModelByPrimaryKey(Integer id) throws SQLException {
		Model _key = new Model();
		_key.setId(id);
		Model record = (Model) sqlMapClient.queryForObject("MODEL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int updateModelByExampleSelective(Model record, ModelExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MODEL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int updateModelByExample(Model record, ModelExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MODEL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int updateModelByPrimaryKeySelective(Model record) throws SQLException {
		int rows = sqlMapClient.update("MODEL.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	public int updateModelByPrimaryKey(Model record) throws SQLException {
		int rows = sqlMapClient.update("MODEL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MODEL
	 * @mbggenerated  Sun Feb 02 21:47:49 ART 2014
	 */
	protected static class UpdateByExampleParms extends ModelExample {
		private Object record;

		public UpdateByExampleParms(Object record, ModelExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
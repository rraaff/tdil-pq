package com.tdil.lojack.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.lojack.dao.LightConfDAO;
import com.tdil.lojack.model.LightConf;
import com.tdil.lojack.model.LightConfExample;
import java.sql.SQLException;
import java.util.List;

public class LightConfDAOImpl implements LightConfDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public LightConfDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int countLightConfByExample(LightConfExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("LIGHT_CONF.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int deleteLightConfByExample(LightConfExample example) throws SQLException {
		int rows = sqlMapClient.delete("LIGHT_CONF.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int deleteLightConfByPrimaryKey(Integer id) throws SQLException {
		LightConf _key = new LightConf();
		_key.setId(id);
		int rows = sqlMapClient.delete("LIGHT_CONF.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public Integer insertLightConf(LightConf record) throws SQLException {
		Object newKey = sqlMapClient.insert("LIGHT_CONF.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public Integer insertLightConfSelective(LightConf record) throws SQLException {
		Object newKey = sqlMapClient.insert("LIGHT_CONF.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	@SuppressWarnings("unchecked")
	public List<LightConf> selectLightConfByExample(LightConfExample example) throws SQLException {
		List<LightConf> list = sqlMapClient.queryForList("LIGHT_CONF.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public LightConf selectLightConfByPrimaryKey(Integer id) throws SQLException {
		LightConf _key = new LightConf();
		_key.setId(id);
		LightConf record = (LightConf) sqlMapClient.queryForObject("LIGHT_CONF.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int updateLightConfByExampleSelective(LightConf record, LightConfExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("LIGHT_CONF.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int updateLightConfByExample(LightConf record, LightConfExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("LIGHT_CONF.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int updateLightConfByPrimaryKeySelective(LightConf record) throws SQLException {
		int rows = sqlMapClient.update("LIGHT_CONF.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	public int updateLightConfByPrimaryKey(LightConf record) throws SQLException {
		int rows = sqlMapClient.update("LIGHT_CONF.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table LIGHT_CONF
	 * @mbggenerated  Thu May 23 18:21:54 ART 2013
	 */
	protected static class UpdateByExampleParms extends LightConfExample {
		private Object record;

		public UpdateByExampleParms(Object record, LightConfExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.SystemPropertyDAO;
import com.tdil.djmag.model.SystemProperty;
import com.tdil.djmag.model.SystemPropertyExample;
import java.sql.SQLException;
import java.util.List;

public class SystemPropertyDAOImpl implements SystemPropertyDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public SystemPropertyDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int countSystemPropertyByExample(SystemPropertyExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SYSPROPERTIES.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int deleteSystemPropertyByExample(SystemPropertyExample example) throws SQLException {
		int rows = sqlMapClient.delete("SYSPROPERTIES.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int deleteSystemPropertyByPrimaryKey(Integer id) throws SQLException {
		SystemProperty _key = new SystemProperty();
		_key.setId(id);
		int rows = sqlMapClient.delete("SYSPROPERTIES.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Integer insertSystemProperty(SystemProperty record) throws SQLException {
		Object newKey = sqlMapClient.insert("SYSPROPERTIES.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Integer insertSystemPropertySelective(SystemProperty record) throws SQLException {
		Object newKey = sqlMapClient.insert("SYSPROPERTIES.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<SystemProperty> selectSystemPropertyByExample(SystemPropertyExample example) throws SQLException {
		List<SystemProperty> list = sqlMapClient.queryForList("SYSPROPERTIES.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public SystemProperty selectSystemPropertyByPrimaryKey(Integer id) throws SQLException {
		SystemProperty _key = new SystemProperty();
		_key.setId(id);
		SystemProperty record = (SystemProperty) sqlMapClient.queryForObject("SYSPROPERTIES.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemPropertyByExampleSelective(SystemProperty record, SystemPropertyExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SYSPROPERTIES.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemPropertyByExample(SystemProperty record, SystemPropertyExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SYSPROPERTIES.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemPropertyByPrimaryKeySelective(SystemProperty record) throws SQLException {
		int rows = sqlMapClient.update("SYSPROPERTIES.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemPropertyByPrimaryKey(SystemProperty record) throws SQLException {
		int rows = sqlMapClient.update("SYSPROPERTIES.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected static class UpdateByExampleParms extends SystemPropertyExample {
		private Object record;

		public UpdateByExampleParms(Object record, SystemPropertyExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
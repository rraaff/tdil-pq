package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.SystemUserDAO;
import com.tdil.djmag.model.SystemUser;
import com.tdil.djmag.model.SystemUserExample;
import java.sql.SQLException;
import java.util.List;

public class SystemUserDAOImpl implements SystemUserDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public SystemUserDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int countSystemUserByExample(SystemUserExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SYSTEMUSER.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int deleteSystemUserByExample(SystemUserExample example) throws SQLException {
		int rows = sqlMapClient.delete("SYSTEMUSER.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int deleteSystemUserByPrimaryKey(Integer id) throws SQLException {
		SystemUser _key = new SystemUser();
		_key.setId(id);
		int rows = sqlMapClient.delete("SYSTEMUSER.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Integer insertSystemUser(SystemUser record) throws SQLException {
		Object newKey = sqlMapClient.insert("SYSTEMUSER.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Integer insertSystemUserSelective(SystemUser record) throws SQLException {
		Object newKey = sqlMapClient.insert("SYSTEMUSER.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<SystemUser> selectSystemUserByExample(SystemUserExample example) throws SQLException {
		List<SystemUser> list = sqlMapClient.queryForList("SYSTEMUSER.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public SystemUser selectSystemUserByPrimaryKey(Integer id) throws SQLException {
		SystemUser _key = new SystemUser();
		_key.setId(id);
		SystemUser record = (SystemUser) sqlMapClient.queryForObject("SYSTEMUSER.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemUserByExampleSelective(SystemUser record, SystemUserExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SYSTEMUSER.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemUserByExample(SystemUser record, SystemUserExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SYSTEMUSER.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemUserByPrimaryKeySelective(SystemUser record) throws SQLException {
		int rows = sqlMapClient.update("SYSTEMUSER.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateSystemUserByPrimaryKey(SystemUser record) throws SQLException {
		int rows = sqlMapClient.update("SYSTEMUSER.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected static class UpdateByExampleParms extends SystemUserExample {
		private Object record;

		public UpdateByExampleParms(Object record, SystemUserExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
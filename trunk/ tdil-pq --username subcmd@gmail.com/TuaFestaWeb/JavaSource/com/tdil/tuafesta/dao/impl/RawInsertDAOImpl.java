package com.tdil.tuafesta.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.RawInsertDAO;
import com.tdil.tuafesta.model.RawInsert;
import com.tdil.tuafesta.model.RawInsertExample;

public class RawInsertDAOImpl implements RawInsertDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public RawInsertDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int countRawInsertByExample(RawInsertExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("RAW_INSERT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int deleteRawInsertByExample(RawInsertExample example) throws SQLException {
		int rows = sqlMapClient.delete("RAW_INSERT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int deleteRawInsertByPrimaryKey(Integer id) throws SQLException {
		RawInsert _key = new RawInsert();
		_key.setId(id);
		int rows = sqlMapClient.delete("RAW_INSERT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public Integer insertRawInsert(RawInsert record) throws SQLException {
		Object newKey = sqlMapClient.insert("RAW_INSERT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public Integer insertRawInsertSelective(RawInsert record) throws SQLException {
		Object newKey = sqlMapClient.insert("RAW_INSERT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<RawInsert> selectRawInsertByExampleWithBLOBs(RawInsertExample example) throws SQLException {
		List<RawInsert> list = sqlMapClient.queryForList("RAW_INSERT.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<RawInsert> selectRawInsertByExampleWithoutBLOBs(RawInsertExample example) throws SQLException {
		List<RawInsert> list = sqlMapClient.queryForList("RAW_INSERT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public RawInsert selectRawInsertByPrimaryKey(Integer id) throws SQLException {
		RawInsert _key = new RawInsert();
		_key.setId(id);
		RawInsert record = (RawInsert) sqlMapClient.queryForObject("RAW_INSERT.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int updateRawInsertByExampleSelective(RawInsert record, RawInsertExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("RAW_INSERT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int updateRawInsertByExampleWithBLOBs(RawInsert record, RawInsertExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("RAW_INSERT.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int updateRawInsertByExampleWithoutBLOBs(RawInsert record, RawInsertExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("RAW_INSERT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int updateRawInsertByPrimaryKeySelective(RawInsert record) throws SQLException {
		int rows = sqlMapClient.update("RAW_INSERT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int updateRawInsertByPrimaryKeyWithBLOBs(RawInsert record) throws SQLException {
		int rows = sqlMapClient.update("RAW_INSERT.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	public int updateRawInsertByPrimaryKeyWithoutBLOBs(RawInsert record) throws SQLException {
		int rows = sqlMapClient.update("RAW_INSERT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	protected static class UpdateByExampleParms extends RawInsertExample {
		private Object record;

		public UpdateByExampleParms(Object record, RawInsertExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
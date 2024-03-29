package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfesionalChangeDAO;
import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.model.ProfesionalChangeExample;
import java.sql.SQLException;
import java.util.List;

public class ProfesionalChangeDAOImpl implements ProfesionalChangeDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public ProfesionalChangeDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int countProfesionalChangeByExample(ProfesionalChangeExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROFESIONAL_CHANGE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteProfesionalChangeByExample(ProfesionalChangeExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROFESIONAL_CHANGE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteProfesionalChangeByPrimaryKey(Integer id) throws SQLException {
		ProfesionalChange _key = new ProfesionalChange();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROFESIONAL_CHANGE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertProfesionalChange(ProfesionalChange record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFESIONAL_CHANGE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertProfesionalChangeSelective(ProfesionalChange record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFESIONAL_CHANGE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProfesionalChange> selectProfesionalChangeByExample(ProfesionalChangeExample example)
			throws SQLException {
		List<ProfesionalChange> list = sqlMapClient.queryForList("PROFESIONAL_CHANGE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public ProfesionalChange selectProfesionalChangeByPrimaryKey(Integer id) throws SQLException {
		ProfesionalChange _key = new ProfesionalChange();
		_key.setId(id);
		ProfesionalChange record = (ProfesionalChange) sqlMapClient.queryForObject(
				"PROFESIONAL_CHANGE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateProfesionalChangeByExampleSelective(ProfesionalChange record, ProfesionalChangeExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFESIONAL_CHANGE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateProfesionalChangeByExample(ProfesionalChange record, ProfesionalChangeExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFESIONAL_CHANGE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateProfesionalChangeByPrimaryKeySelective(ProfesionalChange record) throws SQLException {
		int rows = sqlMapClient.update("PROFESIONAL_CHANGE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateProfesionalChangeByPrimaryKey(ProfesionalChange record) throws SQLException {
		int rows = sqlMapClient.update("PROFESIONAL_CHANGE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfesionalChangeExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfesionalChangeExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
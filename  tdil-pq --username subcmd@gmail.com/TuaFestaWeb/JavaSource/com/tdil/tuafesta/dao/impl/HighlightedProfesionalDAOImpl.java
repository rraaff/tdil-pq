package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.HighlightedProfesionalDAO;
import com.tdil.tuafesta.model.HighlightedProfesional;
import com.tdil.tuafesta.model.HighlightedProfesionalExample;
import java.sql.SQLException;
import java.util.List;

public class HighlightedProfesionalDAOImpl implements HighlightedProfesionalDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public HighlightedProfesionalDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int countHighlightedProfesionalByExample(HighlightedProfesionalExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("HIGHLIGHTED_PROF.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int deleteHighlightedProfesionalByExample(HighlightedProfesionalExample example) throws SQLException {
		int rows = sqlMapClient.delete("HIGHLIGHTED_PROF.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int deleteHighlightedProfesionalByPrimaryKey(Integer id) throws SQLException {
		HighlightedProfesional _key = new HighlightedProfesional();
		_key.setId(id);
		int rows = sqlMapClient.delete("HIGHLIGHTED_PROF.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer insertHighlightedProfesional(HighlightedProfesional record) throws SQLException {
		Object newKey = sqlMapClient.insert("HIGHLIGHTED_PROF.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer insertHighlightedProfesionalSelective(HighlightedProfesional record) throws SQLException {
		Object newKey = sqlMapClient.insert("HIGHLIGHTED_PROF.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<HighlightedProfesional> selectHighlightedProfesionalByExample(HighlightedProfesionalExample example)
			throws SQLException {
		List<HighlightedProfesional> list = sqlMapClient.queryForList("HIGHLIGHTED_PROF.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public HighlightedProfesional selectHighlightedProfesionalByPrimaryKey(Integer id) throws SQLException {
		HighlightedProfesional _key = new HighlightedProfesional();
		_key.setId(id);
		HighlightedProfesional record = (HighlightedProfesional) sqlMapClient.queryForObject(
				"HIGHLIGHTED_PROF.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateHighlightedProfesionalByExampleSelective(HighlightedProfesional record,
			HighlightedProfesionalExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("HIGHLIGHTED_PROF.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateHighlightedProfesionalByExample(HighlightedProfesional record,
			HighlightedProfesionalExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("HIGHLIGHTED_PROF.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateHighlightedProfesionalByPrimaryKeySelective(HighlightedProfesional record) throws SQLException {
		int rows = sqlMapClient.update("HIGHLIGHTED_PROF.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateHighlightedProfesionalByPrimaryKey(HighlightedProfesional record) throws SQLException {
		int rows = sqlMapClient.update("HIGHLIGHTED_PROF.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	protected static class UpdateByExampleParms extends HighlightedProfesionalExample {
		private Object record;

		public UpdateByExampleParms(Object record, HighlightedProfesionalExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
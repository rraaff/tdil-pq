package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfesionalCategoryDAO;
import com.tdil.tuafesta.model.ProfesionalCategory;
import com.tdil.tuafesta.model.ProfesionalCategoryExample;
import java.sql.SQLException;
import java.util.List;

public class ProfesionalCategoryDAOImpl implements ProfesionalCategoryDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public ProfesionalCategoryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int countProfesionalCategoryByExample(ProfesionalCategoryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROF_CATEGORY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int deleteProfesionalCategoryByExample(ProfesionalCategoryExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROF_CATEGORY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int deleteProfesionalCategoryByPrimaryKey(Integer id) throws SQLException {
		ProfesionalCategory _key = new ProfesionalCategory();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROF_CATEGORY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public Integer insertProfesionalCategory(ProfesionalCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_CATEGORY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public Integer insertProfesionalCategorySelective(ProfesionalCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_CATEGORY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProfesionalCategory> selectProfesionalCategoryByExample(ProfesionalCategoryExample example)
			throws SQLException {
		List<ProfesionalCategory> list = sqlMapClient.queryForList("PROF_CATEGORY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public ProfesionalCategory selectProfesionalCategoryByPrimaryKey(Integer id) throws SQLException {
		ProfesionalCategory _key = new ProfesionalCategory();
		_key.setId(id);
		ProfesionalCategory record = (ProfesionalCategory) sqlMapClient.queryForObject(
				"PROF_CATEGORY.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int updateProfesionalCategoryByExampleSelective(ProfesionalCategory record,
			ProfesionalCategoryExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_CATEGORY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int updateProfesionalCategoryByExample(ProfesionalCategory record, ProfesionalCategoryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_CATEGORY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int updateProfesionalCategoryByPrimaryKeySelective(ProfesionalCategory record) throws SQLException {
		int rows = sqlMapClient.update("PROF_CATEGORY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	public int updateProfesionalCategoryByPrimaryKey(ProfesionalCategory record) throws SQLException {
		int rows = sqlMapClient.update("PROF_CATEGORY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROF_CATEGORY
	 * @mbggenerated  Sat Jun 16 14:28:40 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfesionalCategoryExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfesionalCategoryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
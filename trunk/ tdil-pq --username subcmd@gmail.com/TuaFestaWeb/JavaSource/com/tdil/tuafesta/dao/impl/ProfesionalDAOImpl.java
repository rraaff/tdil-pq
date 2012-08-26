package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalExample;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;

import java.sql.SQLException;
import java.util.List;

public class ProfesionalDAOImpl implements ProfesionalDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public ProfesionalDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int countProfesionalByExample(ProfesionalExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROFESIONAL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int deleteProfesionalByExample(ProfesionalExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROFESIONAL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int deleteProfesionalByPrimaryKey(Integer id) throws SQLException {
		Profesional _key = new Profesional();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROFESIONAL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public Integer insertProfesional(Profesional record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFESIONAL.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public Integer insertProfesionalSelective(Profesional record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFESIONAL.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Profesional> selectProfesionalByExample(ProfesionalExample example) throws SQLException {
		List<Profesional> list = sqlMapClient.queryForList("PROFESIONAL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public Profesional selectProfesionalByPrimaryKey(Integer id) throws SQLException {
		Profesional _key = new Profesional();
		_key.setId(id);
		Profesional record = (Profesional) sqlMapClient.queryForObject("PROFESIONAL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int updateProfesionalByExampleSelective(Profesional record, ProfesionalExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFESIONAL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int updateProfesionalByExample(Profesional record, ProfesionalExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFESIONAL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int updateProfesionalByPrimaryKeySelective(Profesional record) throws SQLException {
		int rows = sqlMapClient.update("PROFESIONAL.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	public int updateProfesionalByPrimaryKey(Profesional record) throws SQLException {
		int rows = sqlMapClient.update("PROFESIONAL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROFESIONAL
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfesionalExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfesionalExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<Profesional> selectProfesionalsByGeo4(Geo4 geo4) throws SQLException {
		List<Profesional> list = sqlMapClient.queryForList("PROFESIONAL.selectProfesionalsByGeo4", geo4);
		return list;
	}
}
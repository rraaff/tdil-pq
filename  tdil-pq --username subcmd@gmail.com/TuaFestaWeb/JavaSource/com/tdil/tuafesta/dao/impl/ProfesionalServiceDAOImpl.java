package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfesionalServiceDAO;
import com.tdil.tuafesta.model.ProfesionalService;
import com.tdil.tuafesta.model.ProfesionalServiceExample;
import java.sql.SQLException;
import java.util.List;

public class ProfesionalServiceDAOImpl implements ProfesionalServiceDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public ProfesionalServiceDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int countProfesionalServiceByExample(ProfesionalServiceExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROF_SERVICE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int deleteProfesionalServiceByExample(ProfesionalServiceExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROF_SERVICE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int deleteProfesionalServiceByPrimaryKey(Integer id) throws SQLException {
		ProfesionalService _key = new ProfesionalService();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROF_SERVICE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public Integer insertProfesionalService(ProfesionalService record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_SERVICE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public Integer insertProfesionalServiceSelective(ProfesionalService record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_SERVICE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProfesionalService> selectProfesionalServiceByExample(ProfesionalServiceExample example)
			throws SQLException {
		List<ProfesionalService> list = sqlMapClient.queryForList("PROF_SERVICE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public ProfesionalService selectProfesionalServiceByPrimaryKey(Integer id) throws SQLException {
		ProfesionalService _key = new ProfesionalService();
		_key.setId(id);
		ProfesionalService record = (ProfesionalService) sqlMapClient.queryForObject("PROF_SERVICE.selectByPrimaryKey",
				_key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int updateProfesionalServiceByExampleSelective(ProfesionalService record, ProfesionalServiceExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_SERVICE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int updateProfesionalServiceByExample(ProfesionalService record, ProfesionalServiceExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_SERVICE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int updateProfesionalServiceByPrimaryKeySelective(ProfesionalService record) throws SQLException {
		int rows = sqlMapClient.update("PROF_SERVICE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public int updateProfesionalServiceByPrimaryKey(ProfesionalService record) throws SQLException {
		int rows = sqlMapClient.update("PROF_SERVICE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfesionalServiceExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfesionalServiceExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
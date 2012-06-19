package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.Geo2DAO;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import java.sql.SQLException;
import java.util.List;

public class Geo2DAOImpl implements Geo2DAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public Geo2DAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int countGeo2ByExample(Geo2Example example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("GEO2.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int deleteGeo2ByExample(Geo2Example example) throws SQLException {
		int rows = sqlMapClient.delete("GEO2.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int deleteGeo2ByPrimaryKey(Integer id) throws SQLException {
		Geo2 _key = new Geo2();
		_key.setId(id);
		int rows = sqlMapClient.delete("GEO2.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public Integer insertGeo2(Geo2 record) throws SQLException {
		Object newKey = sqlMapClient.insert("GEO2.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public Integer insertGeo2Selective(Geo2 record) throws SQLException {
		Object newKey = sqlMapClient.insert("GEO2.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Geo2> selectGeo2ByExample(Geo2Example example) throws SQLException {
		List<Geo2> list = sqlMapClient.queryForList("GEO2.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public Geo2 selectGeo2ByPrimaryKey(Integer id) throws SQLException {
		Geo2 _key = new Geo2();
		_key.setId(id);
		Geo2 record = (Geo2) sqlMapClient.queryForObject("GEO2.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int updateGeo2ByExampleSelective(Geo2 record, Geo2Example example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("GEO2.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int updateGeo2ByExample(Geo2 record, Geo2Example example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("GEO2.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int updateGeo2ByPrimaryKeySelective(Geo2 record) throws SQLException {
		int rows = sqlMapClient.update("GEO2.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public int updateGeo2ByPrimaryKey(Geo2 record) throws SQLException {
		int rows = sqlMapClient.update("GEO2.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table GEO2
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	protected static class UpdateByExampleParms extends Geo2Example {
		private Object record;

		public UpdateByExampleParms(Object record, Geo2Example example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
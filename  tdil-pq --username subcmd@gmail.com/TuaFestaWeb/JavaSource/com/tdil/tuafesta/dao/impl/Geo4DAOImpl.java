package com.tdil.tuafesta.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.Geo4DAO;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

public class Geo4DAOImpl implements Geo4DAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Geo4DAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int countGeo4ByExample(Geo4Example example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("GEO4.countByExample", example);
		return count;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int deleteGeo4ByExample(Geo4Example example) throws SQLException {
		int rows = sqlMapClient.delete("GEO4.deleteByExample", example);
		return rows;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int deleteGeo4ByPrimaryKey(Integer id) throws SQLException {
		Geo4 _key = new Geo4();
		_key.setId(id);
		int rows = sqlMapClient.delete("GEO4.deleteByPrimaryKey", _key);
		return rows;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer insertGeo4(Geo4 record) throws SQLException {
		Object newKey = sqlMapClient.insert("GEO4.insert", record);
		return (Integer) newKey;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer insertGeo4Selective(Geo4 record) throws SQLException {
		Object newKey = sqlMapClient.insert("GEO4.insertSelective", record);
		return (Integer) newKey;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Geo4> selectGeo4ByExample(Geo4Example example) throws SQLException {
		List<Geo4> list = sqlMapClient.queryForList("GEO4.selectByExample", example);
		return list;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Geo4 selectGeo4ByPrimaryKey(Integer id) throws SQLException {
		Geo4 _key = new Geo4();
		_key.setId(id);
		Geo4 record = (Geo4) sqlMapClient.queryForObject("GEO4.selectByPrimaryKey", _key);
		return record;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int updateGeo4ByExampleSelective(Geo4 record, Geo4Example example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("GEO4.updateByExampleSelective", parms);
		return rows;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int updateGeo4ByExample(Geo4 record, Geo4Example example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("GEO4.updateByExample", parms);
		return rows;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int updateGeo4ByPrimaryKeySelective(Geo4 record) throws SQLException {
		int rows = sqlMapClient.update("GEO4.updateByPrimaryKeySelective", record);
		return rows;
	}


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public int updateGeo4ByPrimaryKey(Geo4 record) throws SQLException {
		int rows = sqlMapClient.update("GEO4.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table GEO4
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	protected static class UpdateByExampleParms extends Geo4Example {
		private Object record;

		public UpdateByExampleParms(Object record, Geo4Example example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<GeoLevelValueObject> searchGeoLevelsByNombre(String string) throws SQLException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("nombre", string);
		List<GeoLevelValueObject> list = sqlMapClient.queryForList("GEO4.searchGeoLevelsByNombre", params);
		return list;
	}
	
	
	public List<GeoLevelValueObject> selectGeoLevelsByGeo4(String searchName) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchName", "%" + searchName.toUpperCase() + "%");
		List<GeoLevelValueObject> list = sqlMapClient.queryForList("GEO4.selectGeoLevel4", params);
		return list;
	}
	
	public GeoLevelValueObject selectGeoLevelsByGeo4(int id) throws SQLException {
		GeoLevelValueObject list = (GeoLevelValueObject)sqlMapClient.queryForObject("GEO4.selectGeoLevel4ById", id);
		return list;
	}
}
package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Geo3DAOImpl implements Geo3DAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Geo3DAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int countGeo3ByExample(Geo3Example example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("GEO3.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int deleteGeo3ByExample(Geo3Example example) throws SQLException {
		int rows = sqlMapClient.delete("GEO3.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int deleteGeo3ByPrimaryKey(Integer id) throws SQLException {
		Geo3 _key = new Geo3();
		_key.setId(id);
		int rows = sqlMapClient.delete("GEO3.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer insertGeo3(Geo3 record) throws SQLException {
		Object newKey = sqlMapClient.insert("GEO3.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer insertGeo3Selective(Geo3 record) throws SQLException {
		Object newKey = sqlMapClient.insert("GEO3.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Geo3> selectGeo3ByExample(Geo3Example example) throws SQLException {
		List<Geo3> list = sqlMapClient.queryForList("GEO3.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Geo3 selectGeo3ByPrimaryKey(Integer id) throws SQLException {
		Geo3 _key = new Geo3();
		_key.setId(id);
		Geo3 record = (Geo3) sqlMapClient.queryForObject("GEO3.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateGeo3ByExampleSelective(Geo3 record, Geo3Example example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("GEO3.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateGeo3ByExample(Geo3 record, Geo3Example example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("GEO3.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateGeo3ByPrimaryKeySelective(Geo3 record) throws SQLException {
		int rows = sqlMapClient.update("GEO3.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateGeo3ByPrimaryKey(Geo3 record) throws SQLException {
		int rows = sqlMapClient.update("GEO3.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table GEO3
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	protected static class UpdateByExampleParms extends Geo3Example {
		private Object record;

		public UpdateByExampleParms(Object record, Geo3Example example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<GeoLevelValueObject> searchGeoLevelsByNombre(String string, boolean notDeleted) throws SQLException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("nombre", string);
		if (notDeleted) {
			params.put("notDeleted", "notDeleted");
		}
		List<GeoLevelValueObject> list = sqlMapClient.queryForList("GEO3.searchGeoLevelsByNombre", params);
		return list;
	}

	public GeoLevelValueObject selectGeoLevelsByGeo3(int id) throws SQLException {
		GeoLevelValueObject list = (GeoLevelValueObject)sqlMapClient.queryForObject("GEO3.selectGeoLevel3ById", id);
		return list;
	}
}
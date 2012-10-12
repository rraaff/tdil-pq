package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.CacheRegionDAO;
import com.tdil.tuafesta.model.CacheRegion;
import com.tdil.tuafesta.model.CacheRegionExample;
import java.sql.SQLException;
import java.util.List;

public class CacheRegionDAOImpl implements CacheRegionDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public CacheRegionDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int countCacheRegionByExample(CacheRegionExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("CACHE_REGION.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int deleteCacheRegionByExample(CacheRegionExample example) throws SQLException {
		int rows = sqlMapClient.delete("CACHE_REGION.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int deleteCacheRegionByPrimaryKey(Integer id) throws SQLException {
		CacheRegion _key = new CacheRegion();
		_key.setId(id);
		int rows = sqlMapClient.delete("CACHE_REGION.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public Integer insertCacheRegion(CacheRegion record) throws SQLException {
		Object newKey = sqlMapClient.insert("CACHE_REGION.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public Integer insertCacheRegionSelective(CacheRegion record) throws SQLException {
		Object newKey = sqlMapClient.insert("CACHE_REGION.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<CacheRegion> selectCacheRegionByExample(CacheRegionExample example) throws SQLException {
		List<CacheRegion> list = sqlMapClient.queryForList("CACHE_REGION.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public CacheRegion selectCacheRegionByPrimaryKey(Integer id) throws SQLException {
		CacheRegion _key = new CacheRegion();
		_key.setId(id);
		CacheRegion record = (CacheRegion) sqlMapClient.queryForObject("CACHE_REGION.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int updateCacheRegionByExampleSelective(CacheRegion record, CacheRegionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("CACHE_REGION.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int updateCacheRegionByExample(CacheRegion record, CacheRegionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("CACHE_REGION.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int updateCacheRegionByPrimaryKeySelective(CacheRegion record) throws SQLException {
		int rows = sqlMapClient.update("CACHE_REGION.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public int updateCacheRegionByPrimaryKey(CacheRegion record) throws SQLException {
		int rows = sqlMapClient.update("CACHE_REGION.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table CACHE_REGION
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	protected static class UpdateByExampleParms extends CacheRegionExample {
		private Object record;

		public UpdateByExampleParms(Object record, CacheRegionExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
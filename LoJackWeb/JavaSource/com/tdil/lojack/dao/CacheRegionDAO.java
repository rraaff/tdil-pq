package com.tdil.lojack.dao;

import com.tdil.lojack.model.CacheRegion;
import com.tdil.lojack.model.CacheRegionExample;
import java.sql.SQLException;
import java.util.List;

public interface CacheRegionDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int countCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int deleteCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int deleteCacheRegionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	Integer insertCacheRegion(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	Integer insertCacheRegionSelective(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	List<CacheRegion> selectCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	CacheRegion selectCacheRegionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updateCacheRegionByExampleSelective(CacheRegion record, CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updateCacheRegionByExample(CacheRegion record, CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updateCacheRegionByPrimaryKeySelective(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updateCacheRegionByPrimaryKey(CacheRegion record) throws SQLException;
}
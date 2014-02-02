package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.CacheRegion;
import com.tdil.ljpeugeot.model.CacheRegionExample;
import java.sql.SQLException;
import java.util.List;

public interface CacheRegionDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int countCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int deleteCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int deleteCacheRegionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	Integer insertCacheRegion(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	Integer insertCacheRegionSelective(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	List<CacheRegion> selectCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	CacheRegion selectCacheRegionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateCacheRegionByExampleSelective(CacheRegion record, CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateCacheRegionByExample(CacheRegion record, CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateCacheRegionByPrimaryKeySelective(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CACHE_REGION
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateCacheRegionByPrimaryKey(CacheRegion record) throws SQLException;
}
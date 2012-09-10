package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.CacheRegion;
import com.tdil.tuafesta.model.CacheRegionExample;
import java.sql.SQLException;
import java.util.List;

public interface CacheRegionDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int countCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteCacheRegionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertCacheRegion(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertCacheRegionSelective(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	List<CacheRegion> selectCacheRegionByExample(CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	CacheRegion selectCacheRegionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateCacheRegionByExampleSelective(CacheRegion record, CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateCacheRegionByExample(CacheRegion record, CacheRegionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateCacheRegionByPrimaryKeySelective(CacheRegion record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CACHE_REGION
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateCacheRegionByPrimaryKey(CacheRegion record) throws SQLException;
}
package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

import java.sql.SQLException;
import java.util.List;

public interface Geo4DAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int countGeo4ByExample(Geo4Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int deleteGeo4ByExample(Geo4Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int deleteGeo4ByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	Integer insertGeo4(Geo4 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	Integer insertGeo4Selective(Geo4 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	List<Geo4> selectGeo4ByExample(Geo4Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	Geo4 selectGeo4ByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int updateGeo4ByExampleSelective(Geo4 record, Geo4Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int updateGeo4ByExample(Geo4 record, Geo4Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int updateGeo4ByPrimaryKeySelective(Geo4 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO4
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	int updateGeo4ByPrimaryKey(Geo4 record) throws SQLException;

	List<GeoLevelValueObject> searchGeoLevelsByNombre(String string) throws SQLException;
	
	List<GeoLevelValueObject> selectGeoLevelsByGeo4(String searchName) throws SQLException;
}
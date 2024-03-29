package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

import java.sql.SQLException;
import java.util.List;

public interface Geo2DAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int countGeo2ByExample(Geo2Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int deleteGeo2ByExample(Geo2Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int deleteGeo2ByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	Integer insertGeo2(Geo2 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	Integer insertGeo2Selective(Geo2 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	List<Geo2> selectGeo2ByExample(Geo2Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	Geo2 selectGeo2ByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateGeo2ByExampleSelective(Geo2 record, Geo2Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateGeo2ByExample(Geo2 record, Geo2Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateGeo2ByPrimaryKeySelective(Geo2 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO2
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	int updateGeo2ByPrimaryKey(Geo2 record) throws SQLException;

	List<GeoLevelValueObject> searchGeoLevelsByNombre(String string, boolean notDeleted) throws SQLException;
	
	GeoLevelValueObject selectGeoLevelsByGeo2(int id) throws SQLException;
}
package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import java.sql.SQLException;
import java.util.List;

public interface Geo3DAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int countGeo3ByExample(Geo3Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int deleteGeo3ByExample(Geo3Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int deleteGeo3ByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	Integer insertGeo3(Geo3 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	Integer insertGeo3Selective(Geo3 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	List<Geo3> selectGeo3ByExample(Geo3Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	Geo3 selectGeo3ByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int updateGeo3ByExampleSelective(Geo3 record, Geo3Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int updateGeo3ByExample(Geo3 record, Geo3Example example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int updateGeo3ByPrimaryKeySelective(Geo3 record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table GEO3
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	int updateGeo3ByPrimaryKey(Geo3 record) throws SQLException;
}
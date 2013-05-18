package com.tdil.lojack.dao;

import com.tdil.lojack.model.PointOfInterest;
import com.tdil.lojack.model.PointOfInterestExample;
import java.sql.SQLException;
import java.util.List;

public interface PointOfInterestDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int countPointOfInterestByExample(PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int deletePointOfInterestByExample(PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int deletePointOfInterestByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	Integer insertPointOfInterest(PointOfInterest record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	Integer insertPointOfInterestSelective(PointOfInterest record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	List<PointOfInterest> selectPointOfInterestByExample(PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	PointOfInterest selectPointOfInterestByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updatePointOfInterestByExampleSelective(PointOfInterest record, PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updatePointOfInterestByExample(PointOfInterest record, PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updatePointOfInterestByPrimaryKeySelective(PointOfInterest record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.POI
	 * @mbggenerated  Sat May 18 16:41:08 ART 2013
	 */
	int updatePointOfInterestByPrimaryKey(PointOfInterest record) throws SQLException;
}
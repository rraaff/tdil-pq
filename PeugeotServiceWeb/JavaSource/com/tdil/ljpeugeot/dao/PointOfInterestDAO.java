package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.PointOfInterest;
import com.tdil.ljpeugeot.model.PointOfInterestExample;
import java.sql.SQLException;
import java.util.List;

public interface PointOfInterestDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int countPointOfInterestByExample(PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int deletePointOfInterestByExample(PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int deletePointOfInterestByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	Integer insertPointOfInterest(PointOfInterest record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	Integer insertPointOfInterestSelective(PointOfInterest record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	List<PointOfInterest> selectPointOfInterestByExample(PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	PointOfInterest selectPointOfInterestByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updatePointOfInterestByExampleSelective(PointOfInterest record, PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updatePointOfInterestByExample(PointOfInterest record, PointOfInterestExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updatePointOfInterestByPrimaryKeySelective(PointOfInterest record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updatePointOfInterestByPrimaryKey(PointOfInterest record) throws SQLException;
}
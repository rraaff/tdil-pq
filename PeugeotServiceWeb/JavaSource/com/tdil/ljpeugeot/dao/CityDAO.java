package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.CityExample;
import java.sql.SQLException;
import java.util.List;

public interface CityDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int countCityByExample(CityExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int deleteCityByExample(CityExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int deleteCityByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	Integer insertCity(City record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	Integer insertCitySelective(City record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	List<City> selectCityByExample(CityExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	City selectCityByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int updateCityByExampleSelective(City record, CityExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int updateCityByExample(City record, CityExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int updateCityByPrimaryKeySelective(City record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CITY
	 * @mbggenerated  Mon Feb 03 01:37:15 ART 2014
	 */
	int updateCityByPrimaryKey(City record) throws SQLException;
}
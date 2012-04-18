package com.tdil.djmag.dao;

import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int countCountryByExample(CountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int deleteCountryByExample(CountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int deleteCountryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	Integer insertCountry(Country record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	Integer insertCountrySelective(Country record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	List<Country> selectCountryByExample(CountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	Country selectCountryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateCountryByExampleSelective(Country record, CountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateCountryByExample(Country record, CountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateCountryByPrimaryKeySelective(Country record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateCountryByPrimaryKey(Country record) throws SQLException;
}
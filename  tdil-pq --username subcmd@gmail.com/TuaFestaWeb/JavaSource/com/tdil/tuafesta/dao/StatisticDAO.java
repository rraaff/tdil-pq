package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Statistic;
import com.tdil.tuafesta.model.StatisticExample;
import com.tdil.tuafesta.model.valueobjects.StatisticValueObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StatisticDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int countStatisticByExample(StatisticExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int deleteStatisticByExample(StatisticExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int deleteStatisticByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	Integer insertStatistic(Statistic record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	Integer insertStatisticSelective(Statistic record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	List<Statistic> selectStatisticByExample(StatisticExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	Statistic selectStatisticByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int updateStatisticByExampleSelective(Statistic record, StatisticExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int updateStatisticByExample(Statistic record, StatisticExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int updateStatisticByPrimaryKeySelective(Statistic record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 01 23:16:17 ART 2012
	 */
	int updateStatisticByPrimaryKey(Statistic record) throws SQLException;

	List<StatisticValueObject> selectProdCategoryStats(Map<String, Object> params) throws SQLException;
	
	List<StatisticValueObject> selectServCategoryStats(Map<String, Object> params) throws SQLException;
	
	List<StatisticValueObject> selectGeoLevelStats(Map<String, Object> params) throws SQLException;

	List<StatisticValueObject> selectProfesionalViewStats(Map<String, Object> params) throws SQLException;

	List<StatisticValueObject> selectProfesionalContactStats(Map<String, Object> params) throws SQLException;

	List<StatisticValueObject> selectProfesionalRegistrationStats(Map<String, Object> params) throws SQLException;

	List<StatisticValueObject> selectClientRegistrationStats(Map<String, Object> params) throws SQLException;
}
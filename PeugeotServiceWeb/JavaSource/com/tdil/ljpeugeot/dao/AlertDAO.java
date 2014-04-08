package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.Alert;
import com.tdil.ljpeugeot.model.AlertExample;
import java.sql.SQLException;
import java.util.List;

public interface AlertDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int countAlertByExample(AlertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int deleteAlertByExample(AlertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int deleteAlertByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	Integer insertAlert(Alert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	Integer insertAlertSelective(Alert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	List<Alert> selectAlertByExample(AlertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	Alert selectAlertByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int updateAlertByExampleSelective(Alert record, AlertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int updateAlertByExample(Alert record, AlertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int updateAlertByPrimaryKeySelective(Alert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.ALERT
	 * @mbggenerated  Mon Apr 07 23:01:25 ART 2014
	 */
	int updateAlertByPrimaryKey(Alert record) throws SQLException;
}
package com.tdil.lojack.dao;

import com.tdil.lojack.model.SystemProperty;
import com.tdil.lojack.model.SystemPropertyExample;
import java.sql.SQLException;
import java.util.List;

public interface SystemPropertyDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int countSystemPropertyByExample(SystemPropertyExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int deleteSystemPropertyByExample(SystemPropertyExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int deleteSystemPropertyByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	Integer insertSystemProperty(SystemProperty record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	Integer insertSystemPropertySelective(SystemProperty record)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	List<SystemProperty> selectSystemPropertyByExample(
			SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	SystemProperty selectSystemPropertyByPrimaryKey(Integer id)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateSystemPropertyByExampleSelective(SystemProperty record,
			SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateSystemPropertyByExample(SystemProperty record,
			SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateSystemPropertyByPrimaryKeySelective(SystemProperty record)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateSystemPropertyByPrimaryKey(SystemProperty record)
			throws SQLException;
}
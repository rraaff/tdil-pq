package com.tdil.milka.dao;

import com.tdil.milka.model.SystemProperty;
import com.tdil.milka.model.SystemPropertyExample;
import java.sql.SQLException;
import java.util.List;

public interface SystemPropertyDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int countSystemPropertyByExample(SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int deleteSystemPropertyByExample(SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int deleteSystemPropertyByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Integer insertSystemProperty(SystemProperty record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Integer insertSystemPropertySelective(SystemProperty record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	List<SystemProperty> selectSystemPropertyByExample(SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	SystemProperty selectSystemPropertyByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateSystemPropertyByExampleSelective(SystemProperty record, SystemPropertyExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateSystemPropertyByExample(SystemProperty record, SystemPropertyExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateSystemPropertyByPrimaryKeySelective(SystemProperty record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSPROPERTIES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateSystemPropertyByPrimaryKey(SystemProperty record) throws SQLException;
}
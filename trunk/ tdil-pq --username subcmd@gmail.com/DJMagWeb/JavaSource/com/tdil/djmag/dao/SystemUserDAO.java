package com.tdil.djmag.dao;

import com.tdil.djmag.model.SystemUser;
import com.tdil.djmag.model.SystemUserExample;
import java.sql.SQLException;
import java.util.List;

public interface SystemUserDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int countSystemUserByExample(SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int deleteSystemUserByExample(SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int deleteSystemUserByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Integer insertSystemUser(SystemUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Integer insertSystemUserSelective(SystemUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	List<SystemUser> selectSystemUserByExample(SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	SystemUser selectSystemUserByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateSystemUserByExampleSelective(SystemUser record, SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateSystemUserByExample(SystemUser record, SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateSystemUserByPrimaryKeySelective(SystemUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SYSTEMUSER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateSystemUserByPrimaryKey(SystemUser record) throws SQLException;
}
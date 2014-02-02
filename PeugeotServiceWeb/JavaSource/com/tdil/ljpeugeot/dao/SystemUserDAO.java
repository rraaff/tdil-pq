package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.SystemUser;
import com.tdil.ljpeugeot.model.SystemUserExample;
import java.sql.SQLException;
import java.util.List;

public interface SystemUserDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int countSystemUserByExample(SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int deleteSystemUserByExample(SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int deleteSystemUserByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	Integer insertSystemUser(SystemUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	Integer insertSystemUserSelective(SystemUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	List<SystemUser> selectSystemUserByExample(SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	SystemUser selectSystemUserByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateSystemUserByExampleSelective(SystemUser record, SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateSystemUserByExample(SystemUser record, SystemUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateSystemUserByPrimaryKeySelective(SystemUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SYSTEMUSER
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateSystemUserByPrimaryKey(SystemUser record) throws SQLException;
}
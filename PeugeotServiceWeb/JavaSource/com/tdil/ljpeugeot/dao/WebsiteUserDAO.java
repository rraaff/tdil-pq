package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.model.WebsiteUserExample;
import java.sql.SQLException;
import java.util.List;

public interface WebsiteUserDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int countWebsiteUserByExample(WebsiteUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int deleteWebsiteUserByExample(WebsiteUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int deleteWebsiteUserByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	Integer insertWebsiteUser(WebsiteUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	Integer insertWebsiteUserSelective(WebsiteUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	List<WebsiteUser> selectWebsiteUserByExample(WebsiteUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	WebsiteUser selectWebsiteUserByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateWebsiteUserByExampleSelective(WebsiteUser record, WebsiteUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateWebsiteUserByExample(WebsiteUser record, WebsiteUserExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateWebsiteUserByPrimaryKeySelective(WebsiteUser record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WEBSITEUSER
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateWebsiteUserByPrimaryKey(WebsiteUser record) throws SQLException;
}
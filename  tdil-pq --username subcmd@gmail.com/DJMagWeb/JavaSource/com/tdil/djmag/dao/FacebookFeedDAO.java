package com.tdil.djmag.dao;

import com.tdil.djmag.model.FacebookFeed;
import com.tdil.djmag.model.FacebookFeedExample;
import java.sql.SQLException;
import java.util.List;

public interface FacebookFeedDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int countFacebookFeedByExample(FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteFacebookFeedByExample(FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteFacebookFeedByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertFacebookFeed(FacebookFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertFacebookFeedSelective(FacebookFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<FacebookFeed> selectFacebookFeedByExampleWithBLOBs(FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<FacebookFeed> selectFacebookFeedByExampleWithoutBLOBs(FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	FacebookFeed selectFacebookFeedByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFacebookFeedByExampleSelective(FacebookFeed record, FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFacebookFeedByExampleWithBLOBs(FacebookFeed record, FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFacebookFeedByExampleWithoutBLOBs(FacebookFeed record, FacebookFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFacebookFeedByPrimaryKeySelective(FacebookFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFacebookFeedByPrimaryKeyWithBLOBs(FacebookFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFacebookFeedByPrimaryKeyWithoutBLOBs(FacebookFeed record) throws SQLException;
}
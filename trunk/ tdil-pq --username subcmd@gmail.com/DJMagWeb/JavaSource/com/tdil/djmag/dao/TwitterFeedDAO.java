package com.tdil.djmag.dao;

import com.tdil.djmag.model.TwitterFeed;
import com.tdil.djmag.model.TwitterFeedExample;
import java.sql.SQLException;
import java.util.List;

public interface TwitterFeedDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int countTwitterFeedByExample(TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int deleteTwitterFeedByExample(TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int deleteTwitterFeedByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	Integer insertTwitterFeed(TwitterFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	Integer insertTwitterFeedSelective(TwitterFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	List<TwitterFeed> selectTwitterFeedByExampleWithBLOBs(TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	List<TwitterFeed> selectTwitterFeedByExampleWithoutBLOBs(TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	TwitterFeed selectTwitterFeedByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int updateTwitterFeedByExampleSelective(TwitterFeed record, TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int updateTwitterFeedByExampleWithBLOBs(TwitterFeed record, TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int updateTwitterFeedByExampleWithoutBLOBs(TwitterFeed record, TwitterFeedExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int updateTwitterFeedByPrimaryKeySelective(TwitterFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int updateTwitterFeedByPrimaryKeyWithBLOBs(TwitterFeed record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	int updateTwitterFeedByPrimaryKeyWithoutBLOBs(TwitterFeed record) throws SQLException;
}
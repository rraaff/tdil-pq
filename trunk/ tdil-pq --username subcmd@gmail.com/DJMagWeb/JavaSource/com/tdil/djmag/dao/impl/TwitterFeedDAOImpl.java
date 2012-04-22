package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.TwitterFeedDAO;
import com.tdil.djmag.model.TwitterFeed;
import com.tdil.djmag.model.TwitterFeedExample;
import java.sql.SQLException;
import java.util.List;

public class TwitterFeedDAOImpl implements TwitterFeedDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public TwitterFeedDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int countTwitterFeedByExample(TwitterFeedExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("TWITTER_FEED.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int deleteTwitterFeedByExample(TwitterFeedExample example) throws SQLException {
		int rows = sqlMapClient.delete("TWITTER_FEED.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int deleteTwitterFeedByPrimaryKey(Integer id) throws SQLException {
		TwitterFeed _key = new TwitterFeed();
		_key.setId(id);
		int rows = sqlMapClient.delete("TWITTER_FEED.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public Integer insertTwitterFeed(TwitterFeed record) throws SQLException {
		Object newKey = sqlMapClient.insert("TWITTER_FEED.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public Integer insertTwitterFeedSelective(TwitterFeed record) throws SQLException {
		Object newKey = sqlMapClient.insert("TWITTER_FEED.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<TwitterFeed> selectTwitterFeedByExampleWithBLOBs(TwitterFeedExample example) throws SQLException {
		List<TwitterFeed> list = sqlMapClient.queryForList("TWITTER_FEED.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<TwitterFeed> selectTwitterFeedByExampleWithoutBLOBs(TwitterFeedExample example) throws SQLException {
		List<TwitterFeed> list = sqlMapClient.queryForList("TWITTER_FEED.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public TwitterFeed selectTwitterFeedByPrimaryKey(Integer id) throws SQLException {
		TwitterFeed _key = new TwitterFeed();
		_key.setId(id);
		TwitterFeed record = (TwitterFeed) sqlMapClient.queryForObject("TWITTER_FEED.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int updateTwitterFeedByExampleSelective(TwitterFeed record, TwitterFeedExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("TWITTER_FEED.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int updateTwitterFeedByExampleWithBLOBs(TwitterFeed record, TwitterFeedExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("TWITTER_FEED.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int updateTwitterFeedByExampleWithoutBLOBs(TwitterFeed record, TwitterFeedExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("TWITTER_FEED.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int updateTwitterFeedByPrimaryKeySelective(TwitterFeed record) throws SQLException {
		int rows = sqlMapClient.update("TWITTER_FEED.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int updateTwitterFeedByPrimaryKeyWithBLOBs(TwitterFeed record) throws SQLException {
		int rows = sqlMapClient.update("TWITTER_FEED.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	public int updateTwitterFeedByPrimaryKeyWithoutBLOBs(TwitterFeed record) throws SQLException {
		int rows = sqlMapClient.update("TWITTER_FEED.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table TWITTER_FEED
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	protected static class UpdateByExampleParms extends TwitterFeedExample {
		private Object record;

		public UpdateByExampleParms(Object record, TwitterFeedExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
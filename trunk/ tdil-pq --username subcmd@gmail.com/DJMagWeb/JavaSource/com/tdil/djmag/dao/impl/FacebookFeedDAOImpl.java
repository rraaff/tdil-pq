package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.FacebookFeedDAO;
import com.tdil.djmag.model.FacebookFeed;
import com.tdil.djmag.model.FacebookFeedExample;
import java.sql.SQLException;
import java.util.List;

public class FacebookFeedDAOImpl implements FacebookFeedDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public FacebookFeedDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int countFacebookFeedByExample(FacebookFeedExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("FACEBOOK_FEED.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int deleteFacebookFeedByExample(FacebookFeedExample example) throws SQLException {
		int rows = sqlMapClient.delete("FACEBOOK_FEED.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int deleteFacebookFeedByPrimaryKey(Integer id) throws SQLException {
		FacebookFeed _key = new FacebookFeed();
		_key.setId(id);
		int rows = sqlMapClient.delete("FACEBOOK_FEED.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public Integer insertFacebookFeed(FacebookFeed record) throws SQLException {
		Object newKey = sqlMapClient.insert("FACEBOOK_FEED.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public Integer insertFacebookFeedSelective(FacebookFeed record) throws SQLException {
		Object newKey = sqlMapClient.insert("FACEBOOK_FEED.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<FacebookFeed> selectFacebookFeedByExampleWithBLOBs(FacebookFeedExample example) throws SQLException {
		List<FacebookFeed> list = sqlMapClient.queryForList("FACEBOOK_FEED.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<FacebookFeed> selectFacebookFeedByExampleWithoutBLOBs(FacebookFeedExample example) throws SQLException {
		List<FacebookFeed> list = sqlMapClient.queryForList("FACEBOOK_FEED.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public FacebookFeed selectFacebookFeedByPrimaryKey(Integer id) throws SQLException {
		FacebookFeed _key = new FacebookFeed();
		_key.setId(id);
		FacebookFeed record = (FacebookFeed) sqlMapClient.queryForObject("FACEBOOK_FEED.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int updateFacebookFeedByExampleSelective(FacebookFeed record, FacebookFeedExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FACEBOOK_FEED.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int updateFacebookFeedByExampleWithBLOBs(FacebookFeed record, FacebookFeedExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FACEBOOK_FEED.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int updateFacebookFeedByExampleWithoutBLOBs(FacebookFeed record, FacebookFeedExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FACEBOOK_FEED.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int updateFacebookFeedByPrimaryKeySelective(FacebookFeed record) throws SQLException {
		int rows = sqlMapClient.update("FACEBOOK_FEED.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int updateFacebookFeedByPrimaryKeyWithBLOBs(FacebookFeed record) throws SQLException {
		int rows = sqlMapClient.update("FACEBOOK_FEED.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public int updateFacebookFeedByPrimaryKeyWithoutBLOBs(FacebookFeed record) throws SQLException {
		int rows = sqlMapClient.update("FACEBOOK_FEED.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FACEBOOK_FEED
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	protected static class UpdateByExampleParms extends FacebookFeedExample {
		private Object record;

		public UpdateByExampleParms(Object record, FacebookFeedExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
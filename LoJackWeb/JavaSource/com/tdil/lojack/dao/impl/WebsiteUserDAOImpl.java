package com.tdil.lojack.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.lojack.dao.WebsiteUserDAO;
import com.tdil.lojack.model.WebsiteUser;
import com.tdil.lojack.model.WebsiteUserExample;
import java.sql.SQLException;
import java.util.List;

public class WebsiteUserDAOImpl implements WebsiteUserDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public WebsiteUserDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int countWebsiteUserByExample(WebsiteUserExample example)
			throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject(
				"WEBSITEUSER.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int deleteWebsiteUserByExample(WebsiteUserExample example)
			throws SQLException {
		int rows = sqlMapClient.delete("WEBSITEUSER.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int deleteWebsiteUserByPrimaryKey(Integer id) throws SQLException {
		WebsiteUser _key = new WebsiteUser();
		_key.setId(id);
		int rows = sqlMapClient.delete("WEBSITEUSER.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public Integer insertWebsiteUser(WebsiteUser record) throws SQLException {
		Object newKey = sqlMapClient.insert("WEBSITEUSER.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public Integer insertWebsiteUserSelective(WebsiteUser record)
			throws SQLException {
		Object newKey = sqlMapClient.insert("WEBSITEUSER.insertSelective",
				record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	@SuppressWarnings("unchecked")
	public List<WebsiteUser> selectWebsiteUserByExample(
			WebsiteUserExample example) throws SQLException {
		List<WebsiteUser> list = sqlMapClient.queryForList(
				"WEBSITEUSER.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public WebsiteUser selectWebsiteUserByPrimaryKey(Integer id)
			throws SQLException {
		WebsiteUser _key = new WebsiteUser();
		_key.setId(id);
		WebsiteUser record = (WebsiteUser) sqlMapClient.queryForObject(
				"WEBSITEUSER.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int updateWebsiteUserByExampleSelective(WebsiteUser record,
			WebsiteUserExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("WEBSITEUSER.updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int updateWebsiteUserByExample(WebsiteUser record,
			WebsiteUserExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("WEBSITEUSER.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int updateWebsiteUserByPrimaryKeySelective(WebsiteUser record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"WEBSITEUSER.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	public int updateWebsiteUserByPrimaryKey(WebsiteUser record)
			throws SQLException {
		int rows = sqlMapClient
				.update("WEBSITEUSER.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Thu Apr 18 18:21:07 ART 2013
	 */
	protected static class UpdateByExampleParms extends WebsiteUserExample {
		private Object record;

		public UpdateByExampleParms(Object record, WebsiteUserExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.NewsletterDAO;
import com.tdil.djmag.model.Newsletter;
import com.tdil.djmag.model.NewsletterExample;
import java.sql.SQLException;
import java.util.List;

public class NewsletterDAOImpl implements NewsletterDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public NewsletterDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int countNewsletterByExample(NewsletterExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("NEWSLETTER.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int deleteNewsletterByExample(NewsletterExample example) throws SQLException {
		int rows = sqlMapClient.delete("NEWSLETTER.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int deleteNewsletterByPrimaryKey(Integer id) throws SQLException {
		Newsletter _key = new Newsletter();
		_key.setId(id);
		int rows = sqlMapClient.delete("NEWSLETTER.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public Integer insertNewsletter(Newsletter record) throws SQLException {
		Object newKey = sqlMapClient.insert("NEWSLETTER.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public Integer insertNewsletterSelective(Newsletter record) throws SQLException {
		Object newKey = sqlMapClient.insert("NEWSLETTER.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Newsletter> selectNewsletterByExample(NewsletterExample example) throws SQLException {
		List<Newsletter> list = sqlMapClient.queryForList("NEWSLETTER.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public Newsletter selectNewsletterByPrimaryKey(Integer id) throws SQLException {
		Newsletter _key = new Newsletter();
		_key.setId(id);
		Newsletter record = (Newsletter) sqlMapClient.queryForObject("NEWSLETTER.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateNewsletterByExampleSelective(Newsletter record, NewsletterExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NEWSLETTER.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateNewsletterByExample(Newsletter record, NewsletterExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NEWSLETTER.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateNewsletterByPrimaryKeySelective(Newsletter record) throws SQLException {
		int rows = sqlMapClient.update("NEWSLETTER.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateNewsletterByPrimaryKey(Newsletter record) throws SQLException {
		int rows = sqlMapClient.update("NEWSLETTER.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NEWSLETTER
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	protected static class UpdateByExampleParms extends NewsletterExample {
		private Object record;

		public UpdateByExampleParms(Object record, NewsletterExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
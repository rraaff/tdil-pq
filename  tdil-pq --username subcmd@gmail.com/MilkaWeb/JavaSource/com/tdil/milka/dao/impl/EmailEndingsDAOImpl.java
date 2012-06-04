package com.tdil.milka.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.EmailEndingsDAO;
import com.tdil.milka.model.EmailEndings;
import com.tdil.milka.model.EmailEndingsExample;
import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;
import com.tdil.milka.model.valueobjects.ExperienceValueObject;

public class EmailEndingsDAOImpl implements EmailEndingsDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public EmailEndingsDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int countEmailEndingsByExample(EmailEndingsExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("EMAIL_ENDINGS.countByExample", example);
		return count;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int deleteEmailEndingsByExample(EmailEndingsExample example) throws SQLException {
		int rows = sqlMapClient.delete("EMAIL_ENDINGS.deleteByExample", example);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int deleteEmailEndingsByPrimaryKey(Integer id) throws SQLException {
		EmailEndings _key = new EmailEndings();
		_key.setId(id);
		int rows = sqlMapClient.delete("EMAIL_ENDINGS.deleteByPrimaryKey", _key);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer insertEmailEndings(EmailEndings record) throws SQLException {
		Object newKey = sqlMapClient.insert("EMAIL_ENDINGS.insert", record);
		return (Integer) newKey;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer insertEmailEndingsSelective(EmailEndings record) throws SQLException {
		Object newKey = sqlMapClient.insert("EMAIL_ENDINGS.insertSelective", record);
		return (Integer) newKey;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<EmailEndings> selectEmailEndingsByExample(EmailEndingsExample example) throws SQLException {
		List<EmailEndings> list = sqlMapClient.queryForList("EMAIL_ENDINGS.selectByExample", example);
		return list;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public EmailEndings selectEmailEndingsByPrimaryKey(Integer id) throws SQLException {
		EmailEndings _key = new EmailEndings();
		_key.setId(id);
		EmailEndings record = (EmailEndings) sqlMapClient.queryForObject("EMAIL_ENDINGS.selectByPrimaryKey", _key);
		return record;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int updateEmailEndingsByExampleSelective(EmailEndings record, EmailEndingsExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("EMAIL_ENDINGS.updateByExampleSelective", parms);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int updateEmailEndingsByExample(EmailEndings record, EmailEndingsExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("EMAIL_ENDINGS.updateByExample", parms);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int updateEmailEndingsByPrimaryKeySelective(EmailEndings record) throws SQLException {
		int rows = sqlMapClient.update("EMAIL_ENDINGS.updateByPrimaryKeySelective", record);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public int updateEmailEndingsByPrimaryKey(EmailEndings record) throws SQLException {
		int rows = sqlMapClient.update("EMAIL_ENDINGS.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	protected static class UpdateByExampleParms extends EmailEndingsExample {
		private Object record;

		public UpdateByExampleParms(Object record, EmailEndingsExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/** Custom queries */
	public List<EmailEndingsValueObject> selectEmailEndingsToApproveWithAuthor() throws SQLException {
		List<EmailEndingsValueObject> list = sqlMapClient.queryForList("EMAIL_ENDINGS.selectEmailsWithAuthorsToApprove");
		return list;
	}
	public List<EmailEndingsValueObject> selectEmailEndingsToReviewWithAuthor() throws SQLException {
		List<EmailEndingsValueObject> list = sqlMapClient.queryForList("EMAIL_ENDINGS.selectEmailsWithAuthorsToReview");
		return list;
	}
	
	// TODO PARAMS
	public List<ExperienceValueObject> search() throws SQLException {
		List<ExperienceValueObject> list = sqlMapClient.queryForList("EMAIL_ENDINGS.selectExperience");
		return list;
	}
}
package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.MailToParentDAO;
import com.tdil.milka.model.MailToParent;
import com.tdil.milka.model.MailToParentExample;
import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;
import com.tdil.milka.model.valueobjects.ExperienceValueObject;
import com.tdil.milka.model.valueobjects.MailToParentValueObject;

import java.sql.SQLException;
import java.util.List;

public class MailToParentDAOImpl implements MailToParentDAO {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public MailToParentDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int countMailToParentByExample(MailToParentExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("MAIL_TO_PARENT.countByExample", example);
		return count;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteMailToParentByExample(MailToParentExample example) throws SQLException {
		int rows = sqlMapClient.delete("MAIL_TO_PARENT.deleteByExample", example);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteMailToParentByPrimaryKey(Integer id) throws SQLException {
		MailToParent _key = new MailToParent();
		_key.setId(id);
		int rows = sqlMapClient.delete("MAIL_TO_PARENT.deleteByPrimaryKey", _key);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertMailToParent(MailToParent record) throws SQLException {
		Object newKey = sqlMapClient.insert("MAIL_TO_PARENT.insert", record);
		return (Integer) newKey;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertMailToParentSelective(MailToParent record) throws SQLException {
		Object newKey = sqlMapClient.insert("MAIL_TO_PARENT.insertSelective", record);
		return (Integer) newKey;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<MailToParent> selectMailToParentByExample(MailToParentExample example) throws SQLException {
		List<MailToParent> list = sqlMapClient.queryForList("MAIL_TO_PARENT.selectByExample", example);
		return list;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public MailToParent selectMailToParentByPrimaryKey(Integer id) throws SQLException {
		MailToParent _key = new MailToParent();
		_key.setId(id);
		MailToParent record = (MailToParent) sqlMapClient.queryForObject("MAIL_TO_PARENT.selectByPrimaryKey", _key);
		return record;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateMailToParentByExampleSelective(MailToParent record, MailToParentExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MAIL_TO_PARENT.updateByExampleSelective", parms);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateMailToParentByExample(MailToParent record, MailToParentExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MAIL_TO_PARENT.updateByExample", parms);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateMailToParentByPrimaryKeySelective(MailToParent record) throws SQLException {
		int rows = sqlMapClient.update("MAIL_TO_PARENT.updateByPrimaryKeySelective", record);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateMailToParentByPrimaryKey(MailToParent record) throws SQLException {
		int rows = sqlMapClient.update("MAIL_TO_PARENT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MAIL_TO_PARENT
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected static class UpdateByExampleParms extends MailToParentExample {
		private Object record;

		public UpdateByExampleParms(Object record, MailToParentExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/** Custom queries */
	public List<MailToParentValueObject> selectMailToParentToApproveWithAuthor() throws SQLException {
		List<MailToParentValueObject> list = sqlMapClient.queryForList("MAIL_TO_PARENT.selectMailToParentWithAuthorsToApprove");
		return list;
	}
	public List<MailToParentValueObject> selectMailToParentToReviewWithAuthor() throws SQLException {
		List<MailToParentValueObject> list = sqlMapClient.queryForList("MAIL_TO_PARENT.selectMailToParentWithAuthorsToReview");
		return list;
	}
	
	public List<ExperienceValueObject> search() throws SQLException {
		List<ExperienceValueObject> list = sqlMapClient.queryForList("MAIL_TO_PARENT.selectExperience");
		return list;
	}
}
package com.tdil.milka.dao;

import com.tdil.milka.model.EmailEndings;
import com.tdil.milka.model.EmailEndingsExample;
import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;

import java.sql.SQLException;
import java.util.List;

public interface EmailEndingsDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int countEmailEndingsByExample(EmailEndingsExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int deleteEmailEndingsByExample(EmailEndingsExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int deleteEmailEndingsByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	Integer insertEmailEndings(EmailEndings record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	Integer insertEmailEndingsSelective(EmailEndings record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	List<EmailEndings> selectEmailEndingsByExample(EmailEndingsExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	EmailEndings selectEmailEndingsByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateEmailEndingsByExampleSelective(EmailEndings record, EmailEndingsExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateEmailEndingsByExample(EmailEndings record, EmailEndingsExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateEmailEndingsByPrimaryKeySelective(EmailEndings record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateEmailEndingsByPrimaryKey(EmailEndings record) throws SQLException;

	List<EmailEndingsValueObject> selectEmailEndingsToApproveWithAuthor() throws SQLException;

	List<EmailEndingsValueObject> selectEmailEndingsToReviewWithAuthor() throws SQLException;
}
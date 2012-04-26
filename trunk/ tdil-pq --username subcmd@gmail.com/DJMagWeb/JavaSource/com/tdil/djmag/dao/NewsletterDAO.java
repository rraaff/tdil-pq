package com.tdil.djmag.dao;

import com.tdil.djmag.model.Newsletter;
import com.tdil.djmag.model.NewsletterExample;
import java.sql.SQLException;
import java.util.List;

public interface NewsletterDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int countNewsletterByExample(NewsletterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int deleteNewsletterByExample(NewsletterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int deleteNewsletterByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Integer insertNewsletter(Newsletter record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Integer insertNewsletterSelective(Newsletter record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	List<Newsletter> selectNewsletterByExample(NewsletterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Newsletter selectNewsletterByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateNewsletterByExampleSelective(Newsletter record, NewsletterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateNewsletterByExample(Newsletter record, NewsletterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateNewsletterByPrimaryKeySelective(Newsletter record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NEWSLETTER
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateNewsletterByPrimaryKey(Newsletter record) throws SQLException;
}
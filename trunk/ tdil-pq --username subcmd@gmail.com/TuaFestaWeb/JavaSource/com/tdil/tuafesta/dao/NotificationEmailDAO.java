package com.tdil.tuafesta.dao;

import java.sql.SQLException;
import java.util.List;

import com.tdil.tuafesta.model.NotificationEmail;
import com.tdil.tuafesta.model.NotificationEmailExample;

public interface NotificationEmailDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int countNotificationEmailByExample(NotificationEmailExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int deleteNotificationEmailByExample(NotificationEmailExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int deleteNotificationEmailByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	Integer insertNotificationEmail(NotificationEmail record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	Integer insertNotificationEmailSelective(NotificationEmail record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	List<NotificationEmail> selectNotificationEmailByExampleWithBLOBs(NotificationEmailExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	List<NotificationEmail> selectNotificationEmailByExampleWithoutBLOBs(NotificationEmailExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	NotificationEmail selectNotificationEmailByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateNotificationEmailByExampleSelective(NotificationEmail record, NotificationEmailExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateNotificationEmailByExampleWithBLOBs(NotificationEmail record, NotificationEmailExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateNotificationEmailByExampleWithoutBLOBs(NotificationEmail record, NotificationEmailExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateNotificationEmailByPrimaryKeySelective(NotificationEmail record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateNotificationEmailByPrimaryKeyWithBLOBs(NotificationEmail record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	int updateNotificationEmailByPrimaryKeyWithoutBLOBs(NotificationEmail record) throws SQLException;
}
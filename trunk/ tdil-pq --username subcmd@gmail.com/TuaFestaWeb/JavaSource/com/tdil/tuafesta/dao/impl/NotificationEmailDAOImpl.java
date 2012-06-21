package com.tdil.tuafesta.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.NotificationEmailDAO;
import com.tdil.tuafesta.model.NotificationEmail;
import com.tdil.tuafesta.model.NotificationEmailExample;

public class NotificationEmailDAOImpl implements NotificationEmailDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public NotificationEmailDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int countNotificationEmailByExample(NotificationEmailExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("NOTIFICATION_EMAIL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int deleteNotificationEmailByExample(NotificationEmailExample example) throws SQLException {
		int rows = sqlMapClient.delete("NOTIFICATION_EMAIL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int deleteNotificationEmailByPrimaryKey(Integer id) throws SQLException {
		NotificationEmail _key = new NotificationEmail();
		_key.setId(id);
		int rows = sqlMapClient.delete("NOTIFICATION_EMAIL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public Integer insertNotificationEmail(NotificationEmail record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTIFICATION_EMAIL.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public Integer insertNotificationEmailSelective(NotificationEmail record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTIFICATION_EMAIL.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<NotificationEmail> selectNotificationEmailByExampleWithBLOBs(NotificationEmailExample example)
			throws SQLException {
		List<NotificationEmail> list = sqlMapClient
				.queryForList("NOTIFICATION_EMAIL.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<NotificationEmail> selectNotificationEmailByExampleWithoutBLOBs(NotificationEmailExample example)
			throws SQLException {
		List<NotificationEmail> list = sqlMapClient.queryForList("NOTIFICATION_EMAIL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public NotificationEmail selectNotificationEmailByPrimaryKey(Integer id) throws SQLException {
		NotificationEmail _key = new NotificationEmail();
		_key.setId(id);
		NotificationEmail record = (NotificationEmail) sqlMapClient.queryForObject(
				"NOTIFICATION_EMAIL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int updateNotificationEmailByExampleSelective(NotificationEmail record, NotificationEmailExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTIFICATION_EMAIL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int updateNotificationEmailByExampleWithBLOBs(NotificationEmail record, NotificationEmailExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTIFICATION_EMAIL.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int updateNotificationEmailByExampleWithoutBLOBs(NotificationEmail record, NotificationEmailExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTIFICATION_EMAIL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int updateNotificationEmailByPrimaryKeySelective(NotificationEmail record) throws SQLException {
		int rows = sqlMapClient.update("NOTIFICATION_EMAIL.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int updateNotificationEmailByPrimaryKeyWithBLOBs(NotificationEmail record) throws SQLException {
		int rows = sqlMapClient.update("NOTIFICATION_EMAIL.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	public int updateNotificationEmailByPrimaryKeyWithoutBLOBs(NotificationEmail record) throws SQLException {
		int rows = sqlMapClient.update("NOTIFICATION_EMAIL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NOTIFICATION_EMAIL
	 * @mbggenerated  Wed Jun 20 22:44:22 ART 2012
	 */
	protected static class UpdateByExampleParms extends NotificationEmailExample {
		private Object record;

		public UpdateByExampleParms(Object record, NotificationEmailExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
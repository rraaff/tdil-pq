package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.ContactDataExample;
import java.sql.SQLException;
import java.util.List;

public interface ContactDataDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int countContactDataByExample(ContactDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int deleteContactDataByExample(ContactDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int deleteContactDataByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	Integer insertContactData(ContactData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	Integer insertContactDataSelective(ContactData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	List<ContactData> selectContactDataByExample(ContactDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	ContactData selectContactDataByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateContactDataByExampleSelective(ContactData record, ContactDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateContactDataByExample(ContactData record, ContactDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateContactDataByPrimaryKeySelective(ContactData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Mon Feb 03 23:15:53 ART 2014
	 */
	int updateContactDataByPrimaryKey(ContactData record) throws SQLException;
}
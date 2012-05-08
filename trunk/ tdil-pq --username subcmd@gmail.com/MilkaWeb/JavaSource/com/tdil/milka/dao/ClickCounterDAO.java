package com.tdil.milka.dao;

import com.tdil.milka.model.ClickCounter;
import com.tdil.milka.model.ClickCounterExample;
import java.sql.SQLException;
import java.util.List;

public interface ClickCounterDAO {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int countClickCounterByExample(ClickCounterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int deleteClickCounterByExample(ClickCounterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int deleteClickCounterByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	Integer insertClickCounter(ClickCounter record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	Integer insertClickCounterSelective(ClickCounter record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	List<ClickCounter> selectClickCounterByExample(ClickCounterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	ClickCounter selectClickCounterByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int updateClickCounterByExampleSelective(ClickCounter record, ClickCounterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int updateClickCounterByExample(ClickCounter record, ClickCounterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int updateClickCounterByPrimaryKeySelective(ClickCounter record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLICK_COUNTER
	 * @mbggenerated  Tue May 08 10:59:45 ART 2012
	 */
	int updateClickCounterByPrimaryKey(ClickCounter record) throws SQLException;

	/** Increment counter */
    int incrementCounter(ClickCounter record) throws SQLException;
}
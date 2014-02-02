package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.StateExample;
import java.sql.SQLException;
import java.util.List;

public interface StateDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int countStateByExample(StateExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int deleteStateByExample(StateExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int deleteStateByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	Integer insertState(State record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	Integer insertStateSelective(State record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	List<State> selectStateByExample(StateExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	State selectStateByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateStateByExampleSelective(State record, StateExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateStateByExample(State record, StateExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateStateByPrimaryKeySelective(State record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.STATE
	 * @mbggenerated  Sun Feb 02 17:26:57 ART 2014
	 */
	int updateStateByPrimaryKey(State record) throws SQLException;
}
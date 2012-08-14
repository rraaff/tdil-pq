package com.tdil.tuafesta.dao;

import java.sql.SQLException;
import java.util.List;

import com.tdil.tuafesta.model.RawInsert;
import com.tdil.tuafesta.model.RawInsertExample;

public interface RawInsertDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int countRawInsertByExample(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int deleteRawInsertByExample(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int deleteRawInsertByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	Integer insertRawInsert(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	Integer insertRawInsertSelective(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	List<RawInsert> selectRawInsertByExampleWithBLOBs(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	List<RawInsert> selectRawInsertByExampleWithoutBLOBs(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	RawInsert selectRawInsertByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int updateRawInsertByExampleSelective(RawInsert record, RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int updateRawInsertByExampleWithBLOBs(RawInsert record, RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int updateRawInsertByExampleWithoutBLOBs(RawInsert record, RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int updateRawInsertByPrimaryKeySelective(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int updateRawInsertByPrimaryKeyWithBLOBs(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	int updateRawInsertByPrimaryKeyWithoutBLOBs(RawInsert record) throws SQLException;
}
package com.tdil.milka.dao;

import com.tdil.milka.model.RawInsert;
import com.tdil.milka.model.RawInsertExample;
import java.sql.SQLException;
import java.util.List;

public interface RawInsertDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int countRawInsertByExample(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int deleteRawInsertByExample(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int deleteRawInsertByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	Integer insertRawInsert(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	Integer insertRawInsertSelective(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	List<RawInsert> selectRawInsertByExampleWithBLOBs(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	List<RawInsert> selectRawInsertByExampleWithoutBLOBs(RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	RawInsert selectRawInsertByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int updateRawInsertByExampleSelective(RawInsert record, RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int updateRawInsertByExampleWithBLOBs(RawInsert record, RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int updateRawInsertByExampleWithoutBLOBs(RawInsert record, RawInsertExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int updateRawInsertByPrimaryKeySelective(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int updateRawInsertByPrimaryKeyWithBLOBs(RawInsert record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RAW_INSERT
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	int updateRawInsertByPrimaryKeyWithoutBLOBs(RawInsert record) throws SQLException;
}
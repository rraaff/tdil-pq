package com.tdil.djmag.dao;

import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.BlobDataExample;
import java.sql.SQLException;
import java.util.List;

public interface BlobDataDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int countBlobDataByExample(BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int deleteBlobDataByExample(BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int deleteBlobDataByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	Integer insertBlobData(BlobData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	Integer insertBlobDataSelective(BlobData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	List<BlobData> selectBlobDataByExampleWithBLOBs(BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	List<BlobData> selectBlobDataByExampleWithoutBLOBs(BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	BlobData selectBlobDataByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int updateBlobDataByExampleSelective(BlobData record, BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int updateBlobDataByExampleWithBLOBs(BlobData record, BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int updateBlobDataByExampleWithoutBLOBs(BlobData record, BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int updateBlobDataByPrimaryKeySelective(BlobData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int updateBlobDataByPrimaryKeyWithBLOBs(BlobData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	int updateBlobDataByPrimaryKeyWithoutBLOBs(BlobData record) throws SQLException;
}
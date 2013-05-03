package com.tdil.lojack.dao;

import com.tdil.lojack.model.BlobData;
import com.tdil.lojack.model.BlobDataExample;
import java.sql.SQLException;
import java.util.List;

public interface BlobDataDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int countBlobDataByExample(BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int deleteBlobDataByExample(BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int deleteBlobDataByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	Integer insertBlobData(BlobData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	Integer insertBlobDataSelective(BlobData record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	List<BlobData> selectBlobDataByExampleWithBLOBs(BlobDataExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	List<BlobData> selectBlobDataByExampleWithoutBLOBs(BlobDataExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	BlobData selectBlobDataByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateBlobDataByExampleSelective(BlobData record,
			BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateBlobDataByExampleWithBLOBs(BlobData record,
			BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateBlobDataByExampleWithoutBLOBs(BlobData record,
			BlobDataExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateBlobDataByPrimaryKeySelective(BlobData record)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateBlobDataByPrimaryKeyWithBLOBs(BlobData record)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	int updateBlobDataByPrimaryKeyWithoutBLOBs(BlobData record)
			throws SQLException;
}
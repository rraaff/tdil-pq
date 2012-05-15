package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.BlobDataDAO;
import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.BlobDataExample;
import java.sql.SQLException;
import java.util.List;

public class BlobDataDAOImpl implements BlobDataDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public BlobDataDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int countBlobDataByExample(BlobDataExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("BLOB_DATA.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int deleteBlobDataByExample(BlobDataExample example) throws SQLException {
		int rows = sqlMapClient.delete("BLOB_DATA.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int deleteBlobDataByPrimaryKey(Integer id) throws SQLException {
		BlobData _key = new BlobData();
		_key.setId(id);
		int rows = sqlMapClient.delete("BLOB_DATA.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public Integer insertBlobData(BlobData record) throws SQLException {
		Object newKey = sqlMapClient.insert("BLOB_DATA.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public Integer insertBlobDataSelective(BlobData record) throws SQLException {
		Object newKey = sqlMapClient.insert("BLOB_DATA.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<BlobData> selectBlobDataByExampleWithBLOBs(BlobDataExample example) throws SQLException {
		List<BlobData> list = sqlMapClient.queryForList("BLOB_DATA.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<BlobData> selectBlobDataByExampleWithoutBLOBs(BlobDataExample example) throws SQLException {
		List<BlobData> list = sqlMapClient.queryForList("BLOB_DATA.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public BlobData selectBlobDataByPrimaryKey(Integer id) throws SQLException {
		BlobData _key = new BlobData();
		_key.setId(id);
		BlobData record = (BlobData) sqlMapClient.queryForObject("BLOB_DATA.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int updateBlobDataByExampleSelective(BlobData record, BlobDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BLOB_DATA.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int updateBlobDataByExampleWithBLOBs(BlobData record, BlobDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BLOB_DATA.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int updateBlobDataByExampleWithoutBLOBs(BlobData record, BlobDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BLOB_DATA.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int updateBlobDataByPrimaryKeySelective(BlobData record) throws SQLException {
		int rows = sqlMapClient.update("BLOB_DATA.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int updateBlobDataByPrimaryKeyWithBLOBs(BlobData record) throws SQLException {
		int rows = sqlMapClient.update("BLOB_DATA.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	public int updateBlobDataByPrimaryKeyWithoutBLOBs(BlobData record) throws SQLException {
		int rows = sqlMapClient.update("BLOB_DATA.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table BLOB_DATA
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	protected static class UpdateByExampleParms extends BlobDataExample {
		private Object record;

		public UpdateByExampleParms(Object record, BlobDataExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
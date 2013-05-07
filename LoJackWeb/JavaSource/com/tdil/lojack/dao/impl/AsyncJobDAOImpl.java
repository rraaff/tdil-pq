package com.tdil.lojack.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.lojack.dao.AsyncJobDAO;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.lojack.model.AsyncJobExample;

public class AsyncJobDAOImpl implements AsyncJobDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public AsyncJobDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int countAsyncJobByExample(AsyncJobExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("ASYNC_JOB.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int deleteAsyncJobByExample(AsyncJobExample example) throws SQLException {
		int rows = sqlMapClient.delete("ASYNC_JOB.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int deleteAsyncJobByPrimaryKey(Integer id) throws SQLException {
		AsyncJob _key = new AsyncJob();
		_key.setId(id);
		int rows = sqlMapClient.delete("ASYNC_JOB.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public Integer insertAsyncJob(AsyncJob record) throws SQLException {
		Object newKey = sqlMapClient.insert("ASYNC_JOB.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public Integer insertAsyncJobSelective(AsyncJob record) throws SQLException {
		Object newKey = sqlMapClient.insert("ASYNC_JOB.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	@SuppressWarnings("unchecked")
	public List<AsyncJob> selectAsyncJobByExample(AsyncJobExample example) throws SQLException {
		List<AsyncJob> list = sqlMapClient.queryForList("ASYNC_JOB.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public AsyncJob selectAsyncJobByPrimaryKey(Integer id) throws SQLException {
		AsyncJob _key = new AsyncJob();
		_key.setId(id);
		AsyncJob record = (AsyncJob) sqlMapClient.queryForObject("ASYNC_JOB.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int updateAsyncJobByExampleSelective(AsyncJob record, AsyncJobExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("ASYNC_JOB.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int updateAsyncJobByExample(AsyncJob record, AsyncJobExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("ASYNC_JOB.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int updateAsyncJobByPrimaryKeySelective(AsyncJob record) throws SQLException {
		int rows = sqlMapClient.update("ASYNC_JOB.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	public int updateAsyncJobByPrimaryKey(AsyncJob record) throws SQLException {
		int rows = sqlMapClient.update("ASYNC_JOB.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Tue May 07 20:05:15 ART 2013
	 */
	protected static class UpdateByExampleParms extends AsyncJobExample {
		private Object record;

		public UpdateByExampleParms(Object record, AsyncJobExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public int oldestMWJob() throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject(
				"ASYNC_JOB.oldestMWJob");
		return count;
	}
}
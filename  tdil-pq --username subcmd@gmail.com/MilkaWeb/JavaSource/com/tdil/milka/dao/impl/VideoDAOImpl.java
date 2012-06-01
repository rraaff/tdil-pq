package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.VideoDAO;
import com.tdil.milka.model.Video;
import com.tdil.milka.model.VideoExample;
import java.sql.SQLException;
import java.util.List;

public class VideoDAOImpl implements VideoDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public VideoDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int countVideoByExample(VideoExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("VIDEO.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int deleteVideoByExample(VideoExample example) throws SQLException {
		int rows = sqlMapClient.delete("VIDEO.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int deleteVideoByPrimaryKey(Integer id) throws SQLException {
		Video _key = new Video();
		_key.setId(id);
		int rows = sqlMapClient.delete("VIDEO.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public Integer insertVideo(Video record) throws SQLException {
		Object newKey = sqlMapClient.insert("VIDEO.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public Integer insertVideoSelective(Video record) throws SQLException {
		Object newKey = sqlMapClient.insert("VIDEO.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Video> selectVideoByExample(VideoExample example) throws SQLException {
		List<Video> list = sqlMapClient.queryForList("VIDEO.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public Video selectVideoByPrimaryKey(Integer id) throws SQLException {
		Video _key = new Video();
		_key.setId(id);
		Video record = (Video) sqlMapClient.queryForObject("VIDEO.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateVideoByExampleSelective(Video record, VideoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("VIDEO.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateVideoByExample(Video record, VideoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("VIDEO.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateVideoByPrimaryKeySelective(Video record) throws SQLException {
		int rows = sqlMapClient.update("VIDEO.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateVideoByPrimaryKey(Video record) throws SQLException {
		int rows = sqlMapClient.update("VIDEO.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table VIDEO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	protected static class UpdateByExampleParms extends VideoExample {
		private Object record;

		public UpdateByExampleParms(Object record, VideoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
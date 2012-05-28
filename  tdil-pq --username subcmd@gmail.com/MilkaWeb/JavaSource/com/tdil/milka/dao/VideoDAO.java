package com.tdil.milka.dao;

import com.tdil.milka.model.Video;
import com.tdil.milka.model.VideoExample;
import java.sql.SQLException;
import java.util.List;

public interface VideoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int countVideoByExample(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int deleteVideoByExample(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int deleteVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	Integer insertVideo(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	Integer insertVideoSelective(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	List<Video> selectVideoByExample(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	Video selectVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updateVideoByExampleSelective(Video record, VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updateVideoByExample(Video record, VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updateVideoByPrimaryKeySelective(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updateVideoByPrimaryKey(Video record) throws SQLException;
}
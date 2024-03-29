package com.tdil.djmag.dao;

import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.Video;
import com.tdil.djmag.model.VideoExample;
import java.sql.SQLException;
import java.util.List;

public interface VideoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int countVideoByExample(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteVideoByExample(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertVideo(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertVideoSelective(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<Video> selectVideoByExampleWithBLOBs(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<Video> selectVideoByExampleWithoutBLOBs(VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Video selectVideoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateVideoByExampleSelective(Video record, VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateVideoByExampleWithBLOBs(Video record, VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateVideoByExampleWithoutBLOBs(Video record, VideoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateVideoByPrimaryKeySelective(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateVideoByPrimaryKeyWithBLOBs(Video record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VIDEO
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateVideoByPrimaryKeyWithoutBLOBs(Video record) throws SQLException;

	/** Custom queries */
	List<Video> selectLastActiveVideosForCountry(Country country) throws SQLException;
}
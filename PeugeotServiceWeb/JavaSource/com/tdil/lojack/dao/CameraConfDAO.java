package com.tdil.lojack.dao;

import java.sql.SQLException;
import java.util.List;

import com.tdil.lojack.model.CameraConf;
import com.tdil.lojack.model.CameraConfExample;

public interface CameraConfDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int countCameraConfByExample(CameraConfExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int deleteCameraConfByExample(CameraConfExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int deleteCameraConfByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	Integer insertCameraConf(CameraConf record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	Integer insertCameraConfSelective(CameraConf record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	List<CameraConf> selectCameraConfByExample(CameraConfExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	CameraConf selectCameraConfByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int updateCameraConfByExampleSelective(CameraConf record, CameraConfExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int updateCameraConfByExample(CameraConf record, CameraConfExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int updateCameraConfByPrimaryKeySelective(CameraConf record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CAMERA_CONF
	 * @mbggenerated  Wed Sep 11 22:59:58 ART 2013
	 */
	int updateCameraConfByPrimaryKey(CameraConf record) throws SQLException;
}
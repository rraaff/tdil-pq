package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.WallWritting;
import com.tdil.tuafesta.model.WallWrittingExample;
import com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface WallWrittingDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int countWallWrittingByExample(WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int deleteWallWrittingByExample(WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int deleteWallWrittingByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	Integer insertWallWritting(WallWritting record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	Integer insertWallWrittingSelective(WallWritting record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	List<WallWritting> selectWallWrittingByExample(WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	WallWritting selectWallWrittingByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateWallWrittingByExampleSelective(WallWritting record, WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateWallWrittingByExample(WallWritting record, WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateWallWrittingByPrimaryKeySelective(WallWritting record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateWallWrittingByPrimaryKey(WallWritting record) throws SQLException;

	List<WallWrittingValueObject> selectWallWindow(Map<String, Object> params) throws SQLException;

	List<WallWrittingValueObject> selectWallWrittingPending(int id) throws SQLException;

	List<WallWrittingValueObject> selectWallWindowAll(Map<String, Object> params) throws SQLException;
}
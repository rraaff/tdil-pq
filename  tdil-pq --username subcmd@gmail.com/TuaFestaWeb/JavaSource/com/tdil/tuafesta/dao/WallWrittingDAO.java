package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.WallWritting;
import com.tdil.tuafesta.model.WallWrittingExample;
import java.sql.SQLException;
import java.util.List;

public interface WallWrittingDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int countWallWrittingByExample(WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int deleteWallWrittingByExample(WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int deleteWallWrittingByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	Integer insertWallWritting(WallWritting record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	Integer insertWallWrittingSelective(WallWritting record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	List<WallWritting> selectWallWrittingByExample(WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	WallWritting selectWallWrittingByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int updateWallWrittingByExampleSelective(WallWritting record, WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int updateWallWrittingByExample(WallWritting record, WallWrittingExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int updateWallWrittingByPrimaryKeySelective(WallWritting record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	int updateWallWrittingByPrimaryKey(WallWritting record) throws SQLException;
}
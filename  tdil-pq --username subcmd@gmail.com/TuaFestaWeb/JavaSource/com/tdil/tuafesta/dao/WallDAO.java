package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Wall;
import com.tdil.tuafesta.model.WallExample;
import java.sql.SQLException;
import java.util.List;

public interface WallDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int countWallByExample(WallExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteWallByExample(WallExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteWallByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertWall(Wall record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertWallSelective(Wall record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	List<Wall> selectWallByExample(WallExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Wall selectWallByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateWallByExampleSelective(Wall record, WallExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateWallByExample(Wall record, WallExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateWallByPrimaryKeySelective(Wall record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateWallByPrimaryKey(Wall record) throws SQLException;
}
package com.tdil.djmag.dao;

import com.tdil.djmag.model.Magazine;
import com.tdil.djmag.model.MagazineExample;
import java.sql.SQLException;
import java.util.List;

public interface MagazineDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int countMagazineByExample(MagazineExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int deleteMagazineByExample(MagazineExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int deleteMagazineByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Integer insertMagazine(Magazine record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Integer insertMagazineSelective(Magazine record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	List<Magazine> selectMagazineByExample(MagazineExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	Magazine selectMagazineByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateMagazineByExampleSelective(Magazine record, MagazineExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateMagazineByExample(Magazine record, MagazineExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateMagazineByPrimaryKeySelective(Magazine record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	int updateMagazineByPrimaryKey(Magazine record) throws SQLException;
}
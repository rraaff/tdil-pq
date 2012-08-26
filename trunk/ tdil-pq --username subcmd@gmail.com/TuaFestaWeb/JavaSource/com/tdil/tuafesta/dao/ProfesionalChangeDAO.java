package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.model.ProfesionalChangeExample;
import java.sql.SQLException;
import java.util.List;

public interface ProfesionalChangeDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	int countProfesionalChangeByExample(ProfesionalChangeExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:46 ART 2012
	 */
	int deleteProfesionalChangeByExample(ProfesionalChangeExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	int deleteProfesionalChangeByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	Integer insertProfesionalChange(ProfesionalChange record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	Integer insertProfesionalChangeSelective(ProfesionalChange record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	List<ProfesionalChange> selectProfesionalChangeByExample(ProfesionalChangeExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	ProfesionalChange selectProfesionalChangeByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	int updateProfesionalChangeByExampleSelective(ProfesionalChange record, ProfesionalChangeExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	int updateProfesionalChangeByExample(ProfesionalChange record, ProfesionalChangeExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	int updateProfesionalChangeByPrimaryKeySelective(ProfesionalChange record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL_CHANGE
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	int updateProfesionalChangeByPrimaryKey(ProfesionalChange record) throws SQLException;
}
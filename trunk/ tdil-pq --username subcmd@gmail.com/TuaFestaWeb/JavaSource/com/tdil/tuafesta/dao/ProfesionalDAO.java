package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalExample;
import java.sql.SQLException;
import java.util.List;

public interface ProfesionalDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int countProfesionalByExample(ProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int deleteProfesionalByExample(ProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int deleteProfesionalByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	Integer insertProfesional(Profesional record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	Integer insertProfesionalSelective(Profesional record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	List<Profesional> selectProfesionalByExample(ProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	Profesional selectProfesionalByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int updateProfesionalByExampleSelective(Profesional record, ProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int updateProfesionalByExample(Profesional record, ProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int updateProfesionalByPrimaryKeySelective(Profesional record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFESIONAL
	 * @mbggenerated  Mon Aug 06 18:31:17 ART 2012
	 */
	int updateProfesionalByPrimaryKey(Profesional record) throws SQLException;
}
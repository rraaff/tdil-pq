package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.ProfesionalService;
import com.tdil.tuafesta.model.ProfesionalServiceExample;
import java.sql.SQLException;
import java.util.List;

public interface ProfesionalServiceDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int countProfesionalServiceByExample(ProfesionalServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int deleteProfesionalServiceByExample(ProfesionalServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int deleteProfesionalServiceByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	Integer insertProfesionalService(ProfesionalService record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	Integer insertProfesionalServiceSelective(ProfesionalService record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	List<ProfesionalService> selectProfesionalServiceByExample(ProfesionalServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	ProfesionalService selectProfesionalServiceByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int updateProfesionalServiceByExampleSelective(ProfesionalService record, ProfesionalServiceExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int updateProfesionalServiceByExample(ProfesionalService record, ProfesionalServiceExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int updateProfesionalServiceByPrimaryKeySelective(ProfesionalService record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Sun Jul 15 20:26:46 ART 2012
	 */
	int updateProfesionalServiceByPrimaryKey(ProfesionalService record) throws SQLException;
}
package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.ProfesionalProduct;
import com.tdil.tuafesta.model.ProfesionalProductExample;
import java.sql.SQLException;
import java.util.List;

public interface ProfesionalProductDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int countProfesionalProductByExample(ProfesionalProductExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int deleteProfesionalProductByExample(ProfesionalProductExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int deleteProfesionalProductByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	Integer insertProfesionalProduct(ProfesionalProduct record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	Integer insertProfesionalProductSelective(ProfesionalProduct record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	List<ProfesionalProduct> selectProfesionalProductByExample(ProfesionalProductExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	ProfesionalProduct selectProfesionalProductByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateProfesionalProductByExampleSelective(ProfesionalProduct record, ProfesionalProductExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateProfesionalProductByExample(ProfesionalProduct record, ProfesionalProductExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateProfesionalProductByPrimaryKeySelective(ProfesionalProduct record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	int updateProfesionalProductByPrimaryKey(ProfesionalProduct record) throws SQLException;
}
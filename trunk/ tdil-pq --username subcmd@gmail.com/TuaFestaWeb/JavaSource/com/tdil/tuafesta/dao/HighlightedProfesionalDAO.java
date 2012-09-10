package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.HighlightedProfesional;
import com.tdil.tuafesta.model.HighlightedProfesionalExample;
import java.sql.SQLException;
import java.util.List;

public interface HighlightedProfesionalDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int countHighlightedProfesionalByExample(HighlightedProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteHighlightedProfesionalByExample(HighlightedProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteHighlightedProfesionalByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertHighlightedProfesional(HighlightedProfesional record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertHighlightedProfesionalSelective(HighlightedProfesional record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	List<HighlightedProfesional> selectHighlightedProfesionalByExample(HighlightedProfesionalExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	HighlightedProfesional selectHighlightedProfesionalByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateHighlightedProfesionalByExampleSelective(HighlightedProfesional record,
			HighlightedProfesionalExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateHighlightedProfesionalByExample(HighlightedProfesional record, HighlightedProfesionalExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateHighlightedProfesionalByPrimaryKeySelective(HighlightedProfesional record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateHighlightedProfesionalByPrimaryKey(HighlightedProfesional record) throws SQLException;
}
package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.HighlightedCategory;
import com.tdil.tuafesta.model.HighlightedCategoryExample;
import com.tdil.tuafesta.model.valueobjects.HighlightedCategoryValueObject;

import java.sql.SQLException;
import java.util.List;

public interface HighlightedCategoryDAO {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int countHighlightedCategoryByExample(HighlightedCategoryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int deleteHighlightedCategoryByExample(HighlightedCategoryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int deleteHighlightedCategoryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	Integer insertHighlightedCategory(HighlightedCategory record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	Integer insertHighlightedCategorySelective(HighlightedCategory record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	List<HighlightedCategory> selectHighlightedCategoryByExample(HighlightedCategoryExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	HighlightedCategory selectHighlightedCategoryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int updateHighlightedCategoryByExampleSelective(HighlightedCategory record, HighlightedCategoryExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int updateHighlightedCategoryByExample(HighlightedCategory record, HighlightedCategoryExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int updateHighlightedCategoryByPrimaryKeySelective(HighlightedCategory record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	int updateHighlightedCategoryByPrimaryKey(HighlightedCategory record) throws SQLException;

	List<HighlightedCategoryValueObject> selectAllHighlightedCategoriesValueObjects() throws SQLException;
}
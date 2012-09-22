package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int countCategoryByExample(CategoryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int deleteCategoryByExample(CategoryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int deleteCategoryByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    Integer insertCategory(Category record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    Integer insertCategorySelective(Category record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    List<Category> selectCategoryByExample(CategoryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    Category selectCategoryByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int updateCategoryByExampleSelective(Category record, CategoryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int updateCategoryByExample(Category record, CategoryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int updateCategoryByPrimaryKeySelective(Category record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORY
     *
     * @mbggenerated Sat Sep 22 16:49:03 ART 2012
     */
    int updateCategoryByPrimaryKey(Category record) throws SQLException;
}
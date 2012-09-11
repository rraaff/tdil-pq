package com.tdil.tuafesta.dao;

import java.sql.SQLException;
import java.util.List;

import com.tdil.tuafesta.model.ServiceCategory;
import com.tdil.tuafesta.model.ServiceCategoryExample;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;

public interface ServiceCategoryDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int countServiceCategoryByExample(ServiceCategoryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int deleteServiceCategoryByExample(ServiceCategoryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int deleteServiceCategoryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	Integer insertServiceCategory(ServiceCategory record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	Integer insertServiceCategorySelective(ServiceCategory record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	List<ServiceCategory> selectServiceCategoryByExample(ServiceCategoryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	ServiceCategory selectServiceCategoryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int updateServiceCategoryByExampleSelective(ServiceCategory record, ServiceCategoryExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int updateServiceCategoryByExample(ServiceCategory record, ServiceCategoryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int updateServiceCategoryByPrimaryKeySelective(ServiceCategory record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERV_CATEGORY
	 * @mbggenerated  Mon Sep 10 20:21:28 ART 2012
	 */
	int updateServiceCategoryByPrimaryKey(ServiceCategory record) throws SQLException;

	public List<CategoryValueObject> selectTopCategories() throws SQLException;
	
	public List<CategoryValueObject> selectAllCategories() throws SQLException;
}
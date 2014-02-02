package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import java.sql.SQLException;
import java.util.List;

public interface ModelDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int countModelByExample(ModelExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int deleteModelByExample(ModelExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int deleteModelByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	Integer insertModel(Model record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	Integer insertModelSelective(Model record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	List<Model> selectModelByExample(ModelExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	Model selectModelByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int updateModelByExampleSelective(Model record, ModelExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int updateModelByExample(Model record, ModelExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int updateModelByPrimaryKeySelective(Model record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.MODEL
	 * @mbggenerated  Sun Feb 02 11:16:10 ART 2014
	 */
	int updateModelByPrimaryKey(Model record) throws SQLException;
}
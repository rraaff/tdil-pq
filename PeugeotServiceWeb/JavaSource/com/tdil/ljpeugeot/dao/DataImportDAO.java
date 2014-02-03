package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.DataImportExample;
import java.sql.SQLException;
import java.util.List;

public interface DataImportDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int countDataImportByExample(DataImportExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int deleteDataImportByExample(DataImportExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int deleteDataImportByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	Integer insertDataImport(DataImport record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	Integer insertDataImportSelective(DataImport record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	List<DataImport> selectDataImportByExample(DataImportExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	DataImport selectDataImportByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int updateDataImportByExampleSelective(DataImport record, DataImportExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int updateDataImportByExample(DataImport record, DataImportExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int updateDataImportByPrimaryKeySelective(DataImport record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.DATA_IMPORT
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	int updateDataImportByPrimaryKey(DataImport record) throws SQLException;
}
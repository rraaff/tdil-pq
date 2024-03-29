package com.tdil.djmag.dao;

import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import java.sql.SQLException;
import java.util.List;

public interface SectionDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int countSectionByExample(SectionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteSectionByExample(SectionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteSectionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertSection(Section record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertSectionSelective(Section record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<Section> selectSectionByExample(SectionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Section selectSectionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateSectionByExampleSelective(Section record, SectionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateSectionByExample(Section record, SectionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateSectionByPrimaryKeySelective(Section record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateSectionByPrimaryKey(Section record) throws SQLException;

	/** Custom queries*/
	List<Section> selectActiveSectionsForCountry(Country country) throws SQLException;
}
package com.tdil.djmag.dao;

import com.tdil.djmag.model.Footer;
import com.tdil.djmag.model.FooterExample;
import java.sql.SQLException;
import java.util.List;

public interface FooterDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int countFooterByExample(FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteFooterByExample(FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteFooterByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertFooter(Footer record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertFooterSelective(Footer record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<Footer> selectFooterByExampleWithBLOBs(FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<Footer> selectFooterByExampleWithoutBLOBs(FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Footer selectFooterByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFooterByExampleSelective(Footer record, FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFooterByExampleWithBLOBs(Footer record, FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFooterByExampleWithoutBLOBs(Footer record, FooterExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFooterByPrimaryKeySelective(Footer record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFooterByPrimaryKeyWithBLOBs(Footer record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateFooterByPrimaryKeyWithoutBLOBs(Footer record) throws SQLException;
}
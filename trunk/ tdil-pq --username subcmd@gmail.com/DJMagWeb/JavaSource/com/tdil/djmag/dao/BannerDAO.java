package com.tdil.djmag.dao;

import com.tdil.djmag.model.Banner;
import com.tdil.djmag.model.BannerExample;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.valueobjects.BannerValueObject;

import java.sql.SQLException;
import java.util.List;

public interface BannerDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int countBannerByExample(BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int deleteBannerByExample(BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int deleteBannerByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	Integer insertBanner(Banner record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	Integer insertBannerSelective(Banner record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	List<Banner> selectBannerByExampleWithBLOBs(BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	List<Banner> selectBannerByExampleWithoutBLOBs(BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	Banner selectBannerByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int updateBannerByExampleSelective(Banner record, BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int updateBannerByExampleWithBLOBs(Banner record, BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int updateBannerByExampleWithoutBLOBs(Banner record, BannerExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int updateBannerByPrimaryKeySelective(Banner record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int updateBannerByPrimaryKeyWithBLOBs(Banner record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	int updateBannerByPrimaryKeyWithoutBLOBs(Banner record) throws SQLException;

	/** custom queries */
	List<BannerValueObject> getActiveBannersForCountry(Country country) throws SQLException;
}
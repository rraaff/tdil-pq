package com.tdil.djmag.dao;

import com.tdil.djmag.model.BannerPosition;
import com.tdil.djmag.model.BannerPositionExample;
import java.sql.SQLException;
import java.util.List;

public interface BannerPositionDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int countBannerPositionByExample(BannerPositionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int deleteBannerPositionByExample(BannerPositionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int deleteBannerPositionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	Integer insertBannerPosition(BannerPosition record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	Integer insertBannerPositionSelective(BannerPosition record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	List<BannerPosition> selectBannerPositionByExample(BannerPositionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	BannerPosition selectBannerPositionByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int updateBannerPositionByExampleSelective(BannerPosition record, BannerPositionExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int updateBannerPositionByExample(BannerPosition record, BannerPositionExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int updateBannerPositionByPrimaryKeySelective(BannerPosition record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	int updateBannerPositionByPrimaryKey(BannerPosition record) throws SQLException;
}
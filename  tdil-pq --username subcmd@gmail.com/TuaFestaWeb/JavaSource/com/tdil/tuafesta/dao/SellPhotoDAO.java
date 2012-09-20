package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.SellPhoto;
import com.tdil.tuafesta.model.SellPhotoExample;
import java.sql.SQLException;
import java.util.List;

public interface SellPhotoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int countSellPhotoByExample(SellPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int deleteSellPhotoByExample(SellPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int deleteSellPhotoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	Integer insertSellPhoto(SellPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	Integer insertSellPhotoSelective(SellPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	List<SellPhoto> selectSellPhotoByExample(SellPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	SellPhoto selectSellPhotoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int updateSellPhotoByExampleSelective(SellPhoto record, SellPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int updateSellPhotoByExample(SellPhoto record, SellPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int updateSellPhotoByPrimaryKeySelective(SellPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	int updateSellPhotoByPrimaryKey(SellPhoto record) throws SQLException;
}
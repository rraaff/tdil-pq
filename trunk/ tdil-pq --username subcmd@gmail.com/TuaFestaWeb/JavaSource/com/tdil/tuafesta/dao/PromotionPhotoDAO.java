package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.PromotionPhoto;
import com.tdil.tuafesta.model.PromotionPhotoExample;
import java.sql.SQLException;
import java.util.List;

public interface PromotionPhotoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int countPromotionPhotoByExample(PromotionPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deletePromotionPhotoByExample(PromotionPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deletePromotionPhotoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertPromotionPhoto(PromotionPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertPromotionPhotoSelective(PromotionPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	List<PromotionPhoto> selectPromotionPhotoByExample(PromotionPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	PromotionPhoto selectPromotionPhotoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updatePromotionPhotoByExampleSelective(PromotionPhoto record, PromotionPhotoExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updatePromotionPhotoByExample(PromotionPhoto record, PromotionPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updatePromotionPhotoByPrimaryKeySelective(PromotionPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updatePromotionPhotoByPrimaryKey(PromotionPhoto record) throws SQLException;
}
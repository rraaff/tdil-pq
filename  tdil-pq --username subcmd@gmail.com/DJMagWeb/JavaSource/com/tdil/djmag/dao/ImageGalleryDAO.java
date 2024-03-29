package com.tdil.djmag.dao;

import com.tdil.djmag.model.ImageGallery;
import com.tdil.djmag.model.ImageGalleryExample;
import java.sql.SQLException;
import java.util.List;

public interface ImageGalleryDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int countImageGalleryByExample(ImageGalleryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteImageGalleryByExample(ImageGalleryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteImageGalleryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertImageGallery(ImageGallery record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertImageGallerySelective(ImageGallery record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<ImageGallery> selectImageGalleryByExample(ImageGalleryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	ImageGallery selectImageGalleryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateImageGalleryByExampleSelective(ImageGallery record, ImageGalleryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateImageGalleryByExample(ImageGallery record, ImageGalleryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateImageGalleryByPrimaryKeySelective(ImageGallery record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table IMAGE_GALLERY
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateImageGalleryByPrimaryKey(ImageGallery record) throws SQLException;
}
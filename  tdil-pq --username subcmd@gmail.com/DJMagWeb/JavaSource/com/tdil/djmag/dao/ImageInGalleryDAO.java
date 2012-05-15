package com.tdil.djmag.dao;

import com.tdil.djmag.model.ImageInGallery;
import com.tdil.djmag.model.ImageInGalleryExample;
import java.sql.SQLException;
import java.util.List;

public interface ImageInGalleryDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int countImageInGalleryByExample(ImageInGalleryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int deleteImageInGalleryByExample(ImageInGalleryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int deleteImageInGalleryByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    Integer insertImageInGallery(ImageInGallery record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    Integer insertImageInGallerySelective(ImageInGallery record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    List<ImageInGallery> selectImageInGalleryByExample(ImageInGalleryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    ImageInGallery selectImageInGalleryByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int updateImageInGalleryByExampleSelective(ImageInGallery record, ImageInGalleryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int updateImageInGalleryByExample(ImageInGallery record, ImageInGalleryExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int updateImageInGalleryByPrimaryKeySelective(ImageInGallery record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_IN_GAL
     *
     * @mbggenerated Mon May 14 23:58:28 ART 2012
     */
    int updateImageInGalleryByPrimaryKey(ImageInGallery record) throws SQLException;
}
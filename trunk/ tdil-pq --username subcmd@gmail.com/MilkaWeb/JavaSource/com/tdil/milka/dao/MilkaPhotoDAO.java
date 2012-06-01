package com.tdil.milka.dao;

import com.tdil.milka.model.MilkaPhoto;
import com.tdil.milka.model.MilkaPhotoExample;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;

import java.sql.SQLException;
import java.util.List;

public interface MilkaPhotoDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int countMilkaPhotoByExample(MilkaPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int deleteMilkaPhotoByExample(MilkaPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int deleteMilkaPhotoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	Integer insertMilkaPhoto(MilkaPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	Integer insertMilkaPhotoSelective(MilkaPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	List<MilkaPhoto> selectMilkaPhotoByExample(MilkaPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	MilkaPhoto selectMilkaPhotoByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateMilkaPhotoByExampleSelective(MilkaPhoto record, MilkaPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateMilkaPhotoByExample(MilkaPhoto record, MilkaPhotoExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateMilkaPhotoByPrimaryKeySelective(MilkaPhoto record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	int updateMilkaPhotoByPrimaryKey(MilkaPhoto record) throws SQLException;

	/** Resultado con los autores */
	List<MilkaPhotoValueObject> selectMilkaPhotoToApproveWithAuthor() throws SQLException;
	
	List<MilkaPhotoValueObject> selectMilkaPhotoToReviewWithAuthor() throws SQLException;
}
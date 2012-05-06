package com.tdil.milka.dao;

import com.tdil.milka.model.MilkaPhotoTag;
import com.tdil.milka.model.MilkaPhotoTagExample;
import java.sql.SQLException;
import java.util.List;

public interface MilkaPhotoTagDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int countMilkaPhotoTagByExample(MilkaPhotoTagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int deleteMilkaPhotoTagByExample(MilkaPhotoTagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int deleteMilkaPhotoTagByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	Integer insertMilkaPhotoTag(MilkaPhotoTag record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	Integer insertMilkaPhotoTagSelective(MilkaPhotoTag record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	List<MilkaPhotoTag> selectMilkaPhotoTagByExample(MilkaPhotoTagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	MilkaPhotoTag selectMilkaPhotoTagByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int updateMilkaPhotoTagByExampleSelective(MilkaPhotoTag record, MilkaPhotoTagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int updateMilkaPhotoTagByExample(MilkaPhotoTag record, MilkaPhotoTagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int updateMilkaPhotoTagByPrimaryKeySelective(MilkaPhotoTag record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Fri May 04 18:33:03 ART 2012
	 */
	int updateMilkaPhotoTagByPrimaryKey(MilkaPhotoTag record) throws SQLException;
}
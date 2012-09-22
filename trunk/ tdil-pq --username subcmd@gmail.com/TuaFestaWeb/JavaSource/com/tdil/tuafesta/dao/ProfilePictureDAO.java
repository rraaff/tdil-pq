package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.ProfilePicture;
import com.tdil.tuafesta.model.ProfilePictureExample;
import java.sql.SQLException;
import java.util.List;

public interface ProfilePictureDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int countProfilePictureByExample(ProfilePictureExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int deleteProfilePictureByExample(ProfilePictureExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int deleteProfilePictureByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	Integer insertProfilePicture(ProfilePicture record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	Integer insertProfilePictureSelective(ProfilePicture record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	List<ProfilePicture> selectProfilePictureByExample(ProfilePictureExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	ProfilePicture selectProfilePictureByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int updateProfilePictureByExampleSelective(ProfilePicture record, ProfilePictureExample example)
			throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int updateProfilePictureByExample(ProfilePicture record, ProfilePictureExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int updateProfilePictureByPrimaryKeySelective(ProfilePicture record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Sat Sep 22 16:49:03 ART 2012
	 */
	int updateProfilePictureByPrimaryKey(ProfilePicture record) throws SQLException;
}
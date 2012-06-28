package com.tdil.milka.dao;

import java.sql.SQLException;
import java.util.List;

import com.tdil.milka.model.LoveNicknames;
import com.tdil.milka.model.LoveNicknamesExample;
import com.tdil.milka.model.valueobjects.ExperienceValueObject;

public interface LoveNicknamesDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int countLoveNicknamesByExample(LoveNicknamesExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int deleteLoveNicknamesByExample(LoveNicknamesExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int deleteLoveNicknamesByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Integer insertLoveNicknames(LoveNicknames record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Integer insertLoveNicknamesSelective(LoveNicknames record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	List<LoveNicknames> selectLoveNicknamesByExample(LoveNicknamesExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	LoveNicknames selectLoveNicknamesByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateLoveNicknamesByExampleSelective(LoveNicknames record, LoveNicknamesExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateLoveNicknamesByExample(LoveNicknames record, LoveNicknamesExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateLoveNicknamesByPrimaryKeySelective(LoveNicknames record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateLoveNicknamesByPrimaryKey(LoveNicknames record) throws SQLException;

	List<LoveNicknames> selectLoveNicknamesToApprove() throws SQLException;

	List<LoveNicknames> selectLoveNicknamesToReview() throws SQLException;
	
	List<LoveNicknames> selectLoveNicknamesForFlash() throws SQLException;
	
	List<ExperienceValueObject> search() throws SQLException;
}
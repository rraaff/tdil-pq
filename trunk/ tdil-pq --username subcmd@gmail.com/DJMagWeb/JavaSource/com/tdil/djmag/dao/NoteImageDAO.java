package com.tdil.djmag.dao;

import com.tdil.djmag.model.NoteImage;
import com.tdil.djmag.model.NoteImageExample;
import java.sql.SQLException;
import java.util.List;

public interface NoteImageDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int countNoteImageByExample(NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteNoteImageByExample(NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int deleteNoteImageByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertNoteImage(NoteImage record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	Integer insertNoteImageSelective(NoteImage record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<NoteImage> selectNoteImageByExampleWithBLOBs(NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	List<NoteImage> selectNoteImageByExampleWithoutBLOBs(NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	NoteImage selectNoteImageByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateNoteImageByExampleSelective(NoteImage record, NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateNoteImageByExampleWithBLOBs(NoteImage record, NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateNoteImageByExampleWithoutBLOBs(NoteImage record, NoteImageExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateNoteImageByPrimaryKeySelective(NoteImage record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateNoteImageByPrimaryKeyWithBLOBs(NoteImage record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	int updateNoteImageByPrimaryKeyWithoutBLOBs(NoteImage record) throws SQLException;
}
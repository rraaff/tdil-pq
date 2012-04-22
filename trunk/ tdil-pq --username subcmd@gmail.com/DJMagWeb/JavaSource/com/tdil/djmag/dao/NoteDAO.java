package com.tdil.djmag.dao;

import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteExample;
import java.sql.SQLException;
import java.util.List;

public interface NoteDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int countNoteByExample(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int deleteNoteByExample(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int deleteNoteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	Integer insertNote(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	Integer insertNoteSelective(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	List<Note> selectNoteByExampleWithBLOBs(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	List<Note> selectNoteByExampleWithoutBLOBs(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	Note selectNoteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int updateNoteByExampleSelective(Note record, NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int updateNoteByExampleWithBLOBs(Note record, NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int updateNoteByExampleWithoutBLOBs(Note record, NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int updateNoteByPrimaryKeySelective(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int updateNoteByPrimaryKeyWithBLOBs(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Sun Apr 22 11:54:20 ART 2012
	 */
	int updateNoteByPrimaryKeyWithoutBLOBs(Note record) throws SQLException;
}
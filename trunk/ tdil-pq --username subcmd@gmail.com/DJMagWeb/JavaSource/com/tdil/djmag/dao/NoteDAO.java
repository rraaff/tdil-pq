package com.tdil.djmag.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteExample;
import com.tdil.djmag.model.valueobjects.NoteValueObject;

public interface NoteDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int countNoteByExample(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int deleteNoteByExample(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int deleteNoteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	Integer insertNote(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	Integer insertNoteSelective(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	List<Note> selectNoteByExampleWithBLOBs(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	List<Note> selectNoteByExampleWithoutBLOBs(NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	Note selectNoteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int updateNoteByExampleSelective(Note record, NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int updateNoteByExampleWithBLOBs(Note record, NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int updateNoteByExampleWithoutBLOBs(Note record, NoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int updateNoteByPrimaryKeySelective(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int updateNoteByPrimaryKeyWithBLOBs(Note record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon May 14 23:58:28 ART 2012
	 */
	int updateNoteByPrimaryKeyWithoutBLOBs(Note record) throws SQLException;

	/** Custom queries */
	List<NoteValueObject> selectActiveFrontCoversNotesForCountry(Country country) throws SQLException;

	List<NoteValueObject> selectActiveAgendaNotesForCountry(Country country) throws SQLException;
	
	List<NoteValueObject> selectActiveLastNotesForCountry(Country country) throws SQLException;
	
	List<NoteValueObject> selectActiveNotesForMenuItem(MenuItem menuItem) throws SQLException;
	
	List<NoteValueObject> selectNoteByParams(Integer countryId, String webTitle, Date date) throws SQLException;
}
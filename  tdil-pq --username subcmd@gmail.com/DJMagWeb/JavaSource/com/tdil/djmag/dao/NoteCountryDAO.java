package com.tdil.djmag.dao;

import com.tdil.djmag.model.NoteCountry;
import com.tdil.djmag.model.NoteCountryExample;
import java.sql.SQLException;
import java.util.List;

public interface NoteCountryDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int countNoteCountryByExample(NoteCountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int deleteNoteCountryByExample(NoteCountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int deleteNoteCountryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	Integer insertNoteCountry(NoteCountry record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	Integer insertNoteCountrySelective(NoteCountry record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	List<NoteCountry> selectNoteCountryByExample(NoteCountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	NoteCountry selectNoteCountryByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int updateNoteCountryByExampleSelective(NoteCountry record, NoteCountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int updateNoteCountryByExample(NoteCountry record, NoteCountryExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int updateNoteCountryByPrimaryKeySelective(NoteCountry record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	int updateNoteCountryByPrimaryKey(NoteCountry record) throws SQLException;
}
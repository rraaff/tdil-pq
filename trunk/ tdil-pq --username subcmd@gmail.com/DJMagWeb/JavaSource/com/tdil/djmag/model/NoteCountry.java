package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class NoteCountry extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_COUNTRY.id_note
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	private Integer idNote;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_COUNTRY.id_country
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	private Integer idCountry;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_COUNTRY.id_note
	 * @return  the value of NOTE_COUNTRY.id_note
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public Integer getIdNote() {
		return idNote;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_COUNTRY.id_note
	 * @param idNote  the value for NOTE_COUNTRY.id_note
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public void setIdNote(Integer idNote) {
		this.idNote = idNote;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_COUNTRY.id_country
	 * @return  the value of NOTE_COUNTRY.id_country
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_COUNTRY.id_country
	 * @param idCountry  the value for NOTE_COUNTRY.id_country
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}
}
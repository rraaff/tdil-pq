package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class RankingNoteCountry extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_NOTE_COUNTRY.id_ranking_note
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	private Integer idRankingNote;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_NOTE_COUNTRY.id_country
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	private Integer idCountry;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_NOTE_COUNTRY.id_ranking_note
	 * @return  the value of RANKING_NOTE_COUNTRY.id_ranking_note
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public Integer getIdRankingNote() {
		return idRankingNote;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_NOTE_COUNTRY.id_ranking_note
	 * @param idRankingNote  the value for RANKING_NOTE_COUNTRY.id_ranking_note
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public void setIdRankingNote(Integer idRankingNote) {
		this.idRankingNote = idRankingNote;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_NOTE_COUNTRY.id_country
	 * @return  the value of RANKING_NOTE_COUNTRY.id_country
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_NOTE_COUNTRY.id_country
	 * @param idCountry  the value for RANKING_NOTE_COUNTRY.id_country
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}
}
package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class FilteredWord extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column FILTERED_WORD.word
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String word;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column FILTERED_WORD.word
	 * @return  the value of FILTERED_WORD.word
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getWord() {
		return word;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column FILTERED_WORD.word
	 * @param word  the value for FILTERED_WORD.word
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setWord(String word) {
		this.word = word == null ? null : word.trim();
	}
}
package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class RankingNote extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_NOTE.description
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column RANKING_NOTE.positions
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String positions;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_NOTE.description
	 * @return  the value of RANKING_NOTE.description
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_NOTE.description
	 * @param description  the value for RANKING_NOTE.description
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column RANKING_NOTE.positions
	 * @return  the value of RANKING_NOTE.positions
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getPositions() {
		return positions;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column RANKING_NOTE.positions
	 * @param positions  the value for RANKING_NOTE.positions
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setPositions(String positions) {
		this.positions = positions == null ? null : positions.trim();
	}
}
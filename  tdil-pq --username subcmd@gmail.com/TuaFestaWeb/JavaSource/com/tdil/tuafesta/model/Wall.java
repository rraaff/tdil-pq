package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Wall extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL.description
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL.moderated
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer moderated;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL.profanityFilter
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer profanityfilter;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL.description
	 * @return  the value of WALL.description
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL.description
	 * @param description  the value for WALL.description
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL.moderated
	 * @return  the value of WALL.moderated
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getModerated() {
		return moderated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL.moderated
	 * @param moderated  the value for WALL.moderated
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setModerated(Integer moderated) {
		this.moderated = moderated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL.profanityFilter
	 * @return  the value of WALL.profanityFilter
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getProfanityfilter() {
		return profanityfilter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL.profanityFilter
	 * @param profanityfilter  the value for WALL.profanityFilter
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setProfanityfilter(Integer profanityfilter) {
		this.profanityfilter = profanityfilter;
	}
}
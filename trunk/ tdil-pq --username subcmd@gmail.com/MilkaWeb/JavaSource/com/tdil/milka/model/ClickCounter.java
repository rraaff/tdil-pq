package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class ClickCounter extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLICK_COUNTER.clicks
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	private Integer clicks;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLICK_COUNTER.clicks
	 * @return  the value of CLICK_COUNTER.clicks
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public Integer getClicks() {
		return clicks;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLICK_COUNTER.clicks
	 * @param clicks  the value for CLICK_COUNTER.clicks
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
}
package com.tdil.djmag.model;

import java.util.Date;
import com.tdil.ibatis.PersistentObject;

public class Newsletter extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NEWSLETTER.email
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NEWSLETTER.subscriptiondate
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	private Date subscriptiondate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NEWSLETTER.email
	 * @return  the value of NEWSLETTER.email
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NEWSLETTER.email
	 * @param email  the value for NEWSLETTER.email
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NEWSLETTER.subscriptiondate
	 * @return  the value of NEWSLETTER.subscriptiondate
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public Date getSubscriptiondate() {
		return subscriptiondate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NEWSLETTER.subscriptiondate
	 * @param subscriptiondate  the value for NEWSLETTER.subscriptiondate
	 * @mbggenerated  Fri Apr 20 07:58:56 ART 2012
	 */
	public void setSubscriptiondate(Date subscriptiondate) {
		this.subscriptiondate = subscriptiondate;
	}
}
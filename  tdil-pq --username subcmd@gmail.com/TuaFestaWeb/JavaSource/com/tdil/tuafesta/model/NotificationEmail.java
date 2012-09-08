package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class NotificationEmail extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.notificationType
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String notificationtype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.description
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.subject
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String subject;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.from_
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String from;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.replacements
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String replacements;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.content
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	private String content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.notificationType
	 * @return  the value of NOTIFICATION_EMAIL.notificationType
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getNotificationtype() {
		return notificationtype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.notificationType
	 * @param notificationtype  the value for NOTIFICATION_EMAIL.notificationType
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype == null ? null : notificationtype.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.description
	 * @return  the value of NOTIFICATION_EMAIL.description
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.description
	 * @param description  the value for NOTIFICATION_EMAIL.description
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.subject
	 * @return  the value of NOTIFICATION_EMAIL.subject
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.subject
	 * @param subject  the value for NOTIFICATION_EMAIL.subject
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setSubject(String subject) {
		this.subject = subject == null ? null : subject.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.from_
	 * @return  the value of NOTIFICATION_EMAIL.from_
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.from_
	 * @param from  the value for NOTIFICATION_EMAIL.from_
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setFrom(String from) {
		this.from = from == null ? null : from.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.replacements
	 * @return  the value of NOTIFICATION_EMAIL.replacements
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getReplacements() {
		return replacements;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.replacements
	 * @param replacements  the value for NOTIFICATION_EMAIL.replacements
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setReplacements(String replacements) {
		this.replacements = replacements == null ? null : replacements.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.content
	 * @return  the value of NOTIFICATION_EMAIL.content
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.content
	 * @param content  the value for NOTIFICATION_EMAIL.content
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}
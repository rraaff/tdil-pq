package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class NotificationEmail extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.notificationType
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String notificationtype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.replacements
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String replacements;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTIFICATION_EMAIL.content
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.notificationType
	 * @return  the value of NOTIFICATION_EMAIL.notificationType
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getNotificationtype() {
		return notificationtype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.notificationType
	 * @param notificationtype  the value for NOTIFICATION_EMAIL.notificationType
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype == null ? null : notificationtype.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.description
	 * @return  the value of NOTIFICATION_EMAIL.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.description
	 * @param description  the value for NOTIFICATION_EMAIL.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.replacements
	 * @return  the value of NOTIFICATION_EMAIL.replacements
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getReplacements() {
		return replacements;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.replacements
	 * @param replacements  the value for NOTIFICATION_EMAIL.replacements
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setReplacements(String replacements) {
		this.replacements = replacements == null ? null : replacements.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTIFICATION_EMAIL.content
	 * @return  the value of NOTIFICATION_EMAIL.content
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTIFICATION_EMAIL.content
	 * @param content  the value for NOTIFICATION_EMAIL.content
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}
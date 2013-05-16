package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class LoveNicknames extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.creationDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Date creationdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.publishDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Date publishdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.id_author
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idAuthor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.sex
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String sex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.originalText
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String originaltext;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.position
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String position;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.approved
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.id_click_counter
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idClickCounter;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.url_link
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String urlLink;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column LOVE_NICKNAMES.url_target
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String urlTarget;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.creationDate
	 * @return  the value of LOVE_NICKNAMES.creationDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.creationDate
	 * @param creationdate  the value for LOVE_NICKNAMES.creationDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.publishDate
	 * @return  the value of LOVE_NICKNAMES.publishDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Date getPublishdate() {
		return publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.publishDate
	 * @param publishdate  the value for LOVE_NICKNAMES.publishDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.id_author
	 * @return  the value of LOVE_NICKNAMES.id_author
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdAuthor() {
		return idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.id_author
	 * @param idAuthor  the value for LOVE_NICKNAMES.id_author
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.sex
	 * @return  the value of LOVE_NICKNAMES.sex
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.sex
	 * @param sex  the value for LOVE_NICKNAMES.sex
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.originalText
	 * @return  the value of LOVE_NICKNAMES.originalText
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getOriginaltext() {
		return originaltext;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.originalText
	 * @param originaltext  the value for LOVE_NICKNAMES.originalText
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setOriginaltext(String originaltext) {
		this.originaltext = originaltext == null ? null : originaltext.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.position
	 * @return  the value of LOVE_NICKNAMES.position
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.position
	 * @param position  the value for LOVE_NICKNAMES.position
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.approved
	 * @return  the value of LOVE_NICKNAMES.approved
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.approved
	 * @param approved  the value for LOVE_NICKNAMES.approved
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.id_click_counter
	 * @return  the value of LOVE_NICKNAMES.id_click_counter
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdClickCounter() {
		return idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.id_click_counter
	 * @param idClickCounter  the value for LOVE_NICKNAMES.id_click_counter
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdClickCounter(Integer idClickCounter) {
		this.idClickCounter = idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.url_link
	 * @return  the value of LOVE_NICKNAMES.url_link
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getUrlLink() {
		return urlLink;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.url_link
	 * @param urlLink  the value for LOVE_NICKNAMES.url_link
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink == null ? null : urlLink.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column LOVE_NICKNAMES.url_target
	 * @return  the value of LOVE_NICKNAMES.url_target
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getUrlTarget() {
		return urlTarget;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column LOVE_NICKNAMES.url_target
	 * @param urlTarget  the value for LOVE_NICKNAMES.url_target
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget == null ? null : urlTarget.trim();
	}
}
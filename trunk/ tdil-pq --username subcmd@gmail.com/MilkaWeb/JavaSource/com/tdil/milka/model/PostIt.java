package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class PostIt extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.creationDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Date creationdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.publishDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Date publishdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.id_author
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idAuthor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.originalText
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String originaltext;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.title
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.approved
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.id_thumb
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idThumb;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.ext_thum
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String extThum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.id_image
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idImage;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.ext_image
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String extImage;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.url_link
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String urlLink;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.url_target
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String urlTarget;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.position
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer position;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.color
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private String color;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POST_IT.id_click_counter
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private Integer idClickCounter;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.creationDate
	 * @return  the value of POST_IT.creationDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.creationDate
	 * @param creationdate  the value for POST_IT.creationDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.publishDate
	 * @return  the value of POST_IT.publishDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Date getPublishdate() {
		return publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.publishDate
	 * @param publishdate  the value for POST_IT.publishDate
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.id_author
	 * @return  the value of POST_IT.id_author
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdAuthor() {
		return idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.id_author
	 * @param idAuthor  the value for POST_IT.id_author
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.originalText
	 * @return  the value of POST_IT.originalText
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getOriginaltext() {
		return originaltext;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.originalText
	 * @param originaltext  the value for POST_IT.originalText
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setOriginaltext(String originaltext) {
		this.originaltext = originaltext == null ? null : originaltext.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.title
	 * @return  the value of POST_IT.title
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.title
	 * @param title  the value for POST_IT.title
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.description
	 * @return  the value of POST_IT.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.description
	 * @param description  the value for POST_IT.description
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.approved
	 * @return  the value of POST_IT.approved
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.approved
	 * @param approved  the value for POST_IT.approved
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.id_thumb
	 * @return  the value of POST_IT.id_thumb
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdThumb() {
		return idThumb;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.id_thumb
	 * @param idThumb  the value for POST_IT.id_thumb
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdThumb(Integer idThumb) {
		this.idThumb = idThumb;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.ext_thum
	 * @return  the value of POST_IT.ext_thum
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getExtThum() {
		return extThum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.ext_thum
	 * @param extThum  the value for POST_IT.ext_thum
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setExtThum(String extThum) {
		this.extThum = extThum == null ? null : extThum.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.id_image
	 * @return  the value of POST_IT.id_image
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdImage() {
		return idImage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.id_image
	 * @param idImage  the value for POST_IT.id_image
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.ext_image
	 * @return  the value of POST_IT.ext_image
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getExtImage() {
		return extImage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.ext_image
	 * @param extImage  the value for POST_IT.ext_image
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setExtImage(String extImage) {
		this.extImage = extImage == null ? null : extImage.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.url_link
	 * @return  the value of POST_IT.url_link
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getUrlLink() {
		return urlLink;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.url_link
	 * @param urlLink  the value for POST_IT.url_link
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink == null ? null : urlLink.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.url_target
	 * @return  the value of POST_IT.url_target
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getUrlTarget() {
		return urlTarget;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.url_target
	 * @param urlTarget  the value for POST_IT.url_target
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget == null ? null : urlTarget.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.position
	 * @return  the value of POST_IT.position
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.position
	 * @param position  the value for POST_IT.position
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.color
	 * @return  the value of POST_IT.color
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getColor() {
		return color;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.color
	 * @param color  the value for POST_IT.color
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setColor(String color) {
		this.color = color == null ? null : color.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POST_IT.id_click_counter
	 * @return  the value of POST_IT.id_click_counter
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer getIdClickCounter() {
		return idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POST_IT.id_click_counter
	 * @param idClickCounter  the value for POST_IT.id_click_counter
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setIdClickCounter(Integer idClickCounter) {
		this.idClickCounter = idClickCounter;
	}
}
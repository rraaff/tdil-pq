package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class EmailEndings extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.creationDate
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Date creationdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.publishDate
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Date publishdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.id_author
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer idAuthor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.title
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.description
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.frontCover
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer frontcover;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.showInHome
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer showinhome;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.approved
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.id_blob_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer idBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.ext_blob_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String extBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.id_approved_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer idApprovedData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.ext_approved_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String extApprovedData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.id_click_counter
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private Integer idClickCounter;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.url_link
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String urlLink;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column EMAIL_ENDINGS.url_target
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String urlTarget;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.creationDate
	 * @return  the value of EMAIL_ENDINGS.creationDate
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.creationDate
	 * @param creationdate  the value for EMAIL_ENDINGS.creationDate
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.publishDate
	 * @return  the value of EMAIL_ENDINGS.publishDate
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Date getPublishdate() {
		return publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.publishDate
	 * @param publishdate  the value for EMAIL_ENDINGS.publishDate
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.id_author
	 * @return  the value of EMAIL_ENDINGS.id_author
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getIdAuthor() {
		return idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.id_author
	 * @param idAuthor  the value for EMAIL_ENDINGS.id_author
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.title
	 * @return  the value of EMAIL_ENDINGS.title
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.title
	 * @param title  the value for EMAIL_ENDINGS.title
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.description
	 * @return  the value of EMAIL_ENDINGS.description
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.description
	 * @param description  the value for EMAIL_ENDINGS.description
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.frontCover
	 * @return  the value of EMAIL_ENDINGS.frontCover
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getFrontcover() {
		return frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.frontCover
	 * @param frontcover  the value for EMAIL_ENDINGS.frontCover
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setFrontcover(Integer frontcover) {
		this.frontcover = frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.showInHome
	 * @return  the value of EMAIL_ENDINGS.showInHome
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getShowinhome() {
		return showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.showInHome
	 * @param showinhome  the value for EMAIL_ENDINGS.showInHome
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setShowinhome(Integer showinhome) {
		this.showinhome = showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.approved
	 * @return  the value of EMAIL_ENDINGS.approved
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.approved
	 * @param approved  the value for EMAIL_ENDINGS.approved
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.id_blob_data
	 * @return  the value of EMAIL_ENDINGS.id_blob_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getIdBlobData() {
		return idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.id_blob_data
	 * @param idBlobData  the value for EMAIL_ENDINGS.id_blob_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setIdBlobData(Integer idBlobData) {
		this.idBlobData = idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.ext_blob_data
	 * @return  the value of EMAIL_ENDINGS.ext_blob_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getExtBlobData() {
		return extBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.ext_blob_data
	 * @param extBlobData  the value for EMAIL_ENDINGS.ext_blob_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setExtBlobData(String extBlobData) {
		this.extBlobData = extBlobData == null ? null : extBlobData.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.id_approved_data
	 * @return  the value of EMAIL_ENDINGS.id_approved_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getIdApprovedData() {
		return idApprovedData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.id_approved_data
	 * @param idApprovedData  the value for EMAIL_ENDINGS.id_approved_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setIdApprovedData(Integer idApprovedData) {
		this.idApprovedData = idApprovedData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.ext_approved_data
	 * @return  the value of EMAIL_ENDINGS.ext_approved_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getExtApprovedData() {
		return extApprovedData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.ext_approved_data
	 * @param extApprovedData  the value for EMAIL_ENDINGS.ext_approved_data
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setExtApprovedData(String extApprovedData) {
		this.extApprovedData = extApprovedData == null ? null : extApprovedData.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.id_click_counter
	 * @return  the value of EMAIL_ENDINGS.id_click_counter
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public Integer getIdClickCounter() {
		return idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.id_click_counter
	 * @param idClickCounter  the value for EMAIL_ENDINGS.id_click_counter
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setIdClickCounter(Integer idClickCounter) {
		this.idClickCounter = idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.url_link
	 * @return  the value of EMAIL_ENDINGS.url_link
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getUrlLink() {
		return urlLink;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.url_link
	 * @param urlLink  the value for EMAIL_ENDINGS.url_link
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink == null ? null : urlLink.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column EMAIL_ENDINGS.url_target
	 * @return  the value of EMAIL_ENDINGS.url_target
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getUrlTarget() {
		return urlTarget;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column EMAIL_ENDINGS.url_target
	 * @param urlTarget  the value for EMAIL_ENDINGS.url_target
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget == null ? null : urlTarget.trim();
	}
}
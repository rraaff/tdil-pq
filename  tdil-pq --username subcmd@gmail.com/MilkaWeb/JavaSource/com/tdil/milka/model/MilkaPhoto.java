package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class MilkaPhoto extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.creationDate
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Date creationdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.publishDate
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Date publishdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.id_author
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer idAuthor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.frontCover
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer frontcover;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.showInHome
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer showinhome;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.approved
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.id_blob_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer idBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.ext_blob_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private String extBlobData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.id_approved_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer idApprovedData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.ext_approved_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private String extApprovedData;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.id_click_counter
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private Integer idClickCounter;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MILKA_PHOTO.tags
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	private String tags;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.creationDate
	 * @return  the value of MILKA_PHOTO.creationDate
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.creationDate
	 * @param creationdate  the value for MILKA_PHOTO.creationDate
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.publishDate
	 * @return  the value of MILKA_PHOTO.publishDate
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Date getPublishdate() {
		return publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.publishDate
	 * @param publishdate  the value for MILKA_PHOTO.publishDate
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.id_author
	 * @return  the value of MILKA_PHOTO.id_author
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getIdAuthor() {
		return idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.id_author
	 * @param idAuthor  the value for MILKA_PHOTO.id_author
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.frontCover
	 * @return  the value of MILKA_PHOTO.frontCover
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getFrontcover() {
		return frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.frontCover
	 * @param frontcover  the value for MILKA_PHOTO.frontCover
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setFrontcover(Integer frontcover) {
		this.frontcover = frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.showInHome
	 * @return  the value of MILKA_PHOTO.showInHome
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getShowinhome() {
		return showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.showInHome
	 * @param showinhome  the value for MILKA_PHOTO.showInHome
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setShowinhome(Integer showinhome) {
		this.showinhome = showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.approved
	 * @return  the value of MILKA_PHOTO.approved
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.approved
	 * @param approved  the value for MILKA_PHOTO.approved
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.id_blob_data
	 * @return  the value of MILKA_PHOTO.id_blob_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getIdBlobData() {
		return idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.id_blob_data
	 * @param idBlobData  the value for MILKA_PHOTO.id_blob_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setIdBlobData(Integer idBlobData) {
		this.idBlobData = idBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.ext_blob_data
	 * @return  the value of MILKA_PHOTO.ext_blob_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public String getExtBlobData() {
		return extBlobData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.ext_blob_data
	 * @param extBlobData  the value for MILKA_PHOTO.ext_blob_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setExtBlobData(String extBlobData) {
		this.extBlobData = extBlobData == null ? null : extBlobData.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.id_approved_data
	 * @return  the value of MILKA_PHOTO.id_approved_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getIdApprovedData() {
		return idApprovedData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.id_approved_data
	 * @param idApprovedData  the value for MILKA_PHOTO.id_approved_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setIdApprovedData(Integer idApprovedData) {
		this.idApprovedData = idApprovedData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.ext_approved_data
	 * @return  the value of MILKA_PHOTO.ext_approved_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public String getExtApprovedData() {
		return extApprovedData;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.ext_approved_data
	 * @param extApprovedData  the value for MILKA_PHOTO.ext_approved_data
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setExtApprovedData(String extApprovedData) {
		this.extApprovedData = extApprovedData == null ? null : extApprovedData.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.id_click_counter
	 * @return  the value of MILKA_PHOTO.id_click_counter
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public Integer getIdClickCounter() {
		return idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.id_click_counter
	 * @param idClickCounter  the value for MILKA_PHOTO.id_click_counter
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setIdClickCounter(Integer idClickCounter) {
		this.idClickCounter = idClickCounter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MILKA_PHOTO.tags
	 * @return  the value of MILKA_PHOTO.tags
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MILKA_PHOTO.tags
	 * @param tags  the value for MILKA_PHOTO.tags
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	public void setTags(String tags) {
		this.tags = tags == null ? null : tags.trim();
	}
}
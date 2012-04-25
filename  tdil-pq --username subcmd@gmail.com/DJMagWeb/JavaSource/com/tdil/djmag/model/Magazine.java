package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Magazine extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MAGAZINE.description
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MAGAZINE.publish_date
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private Date publishDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MAGAZINE.frontCoverFilename
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private String frontcoverfilename;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MAGAZINE.magazineContentFilename
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private String magazinecontentfilename;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MAGAZINE.frontCoverContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private byte[] frontcovercontent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MAGAZINE.magazineContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	private byte[] magazinecontent;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MAGAZINE.description
	 * @return  the value of MAGAZINE.description
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MAGAZINE.description
	 * @param description  the value for MAGAZINE.description
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MAGAZINE.publish_date
	 * @return  the value of MAGAZINE.publish_date
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MAGAZINE.publish_date
	 * @param publishDate  the value for MAGAZINE.publish_date
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MAGAZINE.frontCoverFilename
	 * @return  the value of MAGAZINE.frontCoverFilename
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public String getFrontcoverfilename() {
		return frontcoverfilename;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MAGAZINE.frontCoverFilename
	 * @param frontcoverfilename  the value for MAGAZINE.frontCoverFilename
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setFrontcoverfilename(String frontcoverfilename) {
		this.frontcoverfilename = frontcoverfilename == null ? null : frontcoverfilename.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MAGAZINE.magazineContentFilename
	 * @return  the value of MAGAZINE.magazineContentFilename
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public String getMagazinecontentfilename() {
		return magazinecontentfilename;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MAGAZINE.magazineContentFilename
	 * @param magazinecontentfilename  the value for MAGAZINE.magazineContentFilename
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setMagazinecontentfilename(String magazinecontentfilename) {
		this.magazinecontentfilename = magazinecontentfilename == null ? null : magazinecontentfilename.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MAGAZINE.frontCoverContent
	 * @return  the value of MAGAZINE.frontCoverContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public byte[] getFrontcovercontent() {
		return frontcovercontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MAGAZINE.frontCoverContent
	 * @param frontcovercontent  the value for MAGAZINE.frontCoverContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setFrontcovercontent(byte[] frontcovercontent) {
		this.frontcovercontent = frontcovercontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MAGAZINE.magazineContent
	 * @return  the value of MAGAZINE.magazineContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public byte[] getMagazinecontent() {
		return magazinecontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MAGAZINE.magazineContent
	 * @param magazinecontent  the value for MAGAZINE.magazineContent
	 * @mbggenerated  Wed Apr 25 07:29:57 ART 2012
	 */
	public void setMagazinecontent(byte[] magazinecontent) {
		this.magazinecontent = magazinecontent;
	}
}
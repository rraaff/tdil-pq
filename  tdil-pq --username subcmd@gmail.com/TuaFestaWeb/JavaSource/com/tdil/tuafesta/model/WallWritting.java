package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class WallWritting extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL_WRITTING.creationDate
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Date creationdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL_WRITTING.publishDate
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Date publishdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL_WRITTING.id_author
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer idAuthor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL_WRITTING.originalText
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private String originaltext;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL_WRITTING.approved
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WALL_WRITTING.id_wall
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer idWall;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL_WRITTING.creationDate
	 * @return  the value of WALL_WRITTING.creationDate
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL_WRITTING.creationDate
	 * @param creationdate  the value for WALL_WRITTING.creationDate
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL_WRITTING.publishDate
	 * @return  the value of WALL_WRITTING.publishDate
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Date getPublishdate() {
		return publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL_WRITTING.publishDate
	 * @param publishdate  the value for WALL_WRITTING.publishDate
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL_WRITTING.id_author
	 * @return  the value of WALL_WRITTING.id_author
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getIdAuthor() {
		return idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL_WRITTING.id_author
	 * @param idAuthor  the value for WALL_WRITTING.id_author
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL_WRITTING.originalText
	 * @return  the value of WALL_WRITTING.originalText
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public String getOriginaltext() {
		return originaltext;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL_WRITTING.originalText
	 * @param originaltext  the value for WALL_WRITTING.originalText
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setOriginaltext(String originaltext) {
		this.originaltext = originaltext == null ? null : originaltext.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL_WRITTING.approved
	 * @return  the value of WALL_WRITTING.approved
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL_WRITTING.approved
	 * @param approved  the value for WALL_WRITTING.approved
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WALL_WRITTING.id_wall
	 * @return  the value of WALL_WRITTING.id_wall
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getIdWall() {
		return idWall;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WALL_WRITTING.id_wall
	 * @param idWall  the value for WALL_WRITTING.id_wall
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setIdWall(Integer idWall) {
		this.idWall = idWall;
	}
}
package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class NoteImage extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.id_note
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer idNote;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.summary
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String summary;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.fileName
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String filename;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.extension
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String extension;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.contentType
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String contenttype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.orderNumber
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer ordernumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.content
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String content;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE_IMAGE.noteImage
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private byte[] noteimage;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.id_note
	 * @return  the value of NOTE_IMAGE.id_note
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getIdNote() {
		return idNote;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.id_note
	 * @param idNote  the value for NOTE_IMAGE.id_note
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setIdNote(Integer idNote) {
		this.idNote = idNote;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.title
	 * @return  the value of NOTE_IMAGE.title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.title
	 * @param title  the value for NOTE_IMAGE.title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.summary
	 * @return  the value of NOTE_IMAGE.summary
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.summary
	 * @param summary  the value for NOTE_IMAGE.summary
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.fileName
	 * @return  the value of NOTE_IMAGE.fileName
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.fileName
	 * @param filename  the value for NOTE_IMAGE.fileName
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setFilename(String filename) {
		this.filename = filename == null ? null : filename.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.extension
	 * @return  the value of NOTE_IMAGE.extension
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.extension
	 * @param extension  the value for NOTE_IMAGE.extension
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setExtension(String extension) {
		this.extension = extension == null ? null : extension.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.contentType
	 * @return  the value of NOTE_IMAGE.contentType
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getContenttype() {
		return contenttype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.contentType
	 * @param contenttype  the value for NOTE_IMAGE.contentType
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype == null ? null : contenttype.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.orderNumber
	 * @return  the value of NOTE_IMAGE.orderNumber
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getOrdernumber() {
		return ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.orderNumber
	 * @param ordernumber  the value for NOTE_IMAGE.orderNumber
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.content
	 * @return  the value of NOTE_IMAGE.content
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.content
	 * @param content  the value for NOTE_IMAGE.content
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE_IMAGE.noteImage
	 * @return  the value of NOTE_IMAGE.noteImage
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public byte[] getNoteimage() {
		return noteimage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE_IMAGE.noteImage
	 * @param noteimage  the value for NOTE_IMAGE.noteImage
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setNoteimage(byte[] noteimage) {
		this.noteimage = noteimage;
	}
}
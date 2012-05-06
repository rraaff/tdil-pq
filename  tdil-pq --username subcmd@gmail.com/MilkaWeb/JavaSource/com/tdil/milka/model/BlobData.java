package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class BlobData extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BLOB_DATA.dataType
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	private String datatype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BLOB_DATA.filename
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	private String filename;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column BLOB_DATA.content
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	private byte[] content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BLOB_DATA.dataType
	 * @return  the value of BLOB_DATA.dataType
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BLOB_DATA.dataType
	 * @param datatype  the value for BLOB_DATA.dataType
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype == null ? null : datatype.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BLOB_DATA.filename
	 * @return  the value of BLOB_DATA.filename
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BLOB_DATA.filename
	 * @param filename  the value for BLOB_DATA.filename
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public void setFilename(String filename) {
		this.filename = filename == null ? null : filename.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column BLOB_DATA.content
	 * @return  the value of BLOB_DATA.content
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column BLOB_DATA.content
	 * @param content  the value for BLOB_DATA.content
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
}
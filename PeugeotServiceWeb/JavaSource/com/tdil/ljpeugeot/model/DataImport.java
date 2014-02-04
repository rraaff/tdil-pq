package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class DataImport extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.type
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.filename
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String filename;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.status
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.processed
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer processed;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.errors
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer errors;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.startTime
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Date starttime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DATA_IMPORT.endTime
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Date endtime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.type
	 * @return  the value of DATA_IMPORT.type
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.type
	 * @param type  the value for DATA_IMPORT.type
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.filename
	 * @return  the value of DATA_IMPORT.filename
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.filename
	 * @param filename  the value for DATA_IMPORT.filename
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setFilename(String filename) {
		this.filename = filename == null ? null : filename.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.status
	 * @return  the value of DATA_IMPORT.status
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.status
	 * @param status  the value for DATA_IMPORT.status
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.processed
	 * @return  the value of DATA_IMPORT.processed
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getProcessed() {
		return processed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.processed
	 * @param processed  the value for DATA_IMPORT.processed
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setProcessed(Integer processed) {
		this.processed = processed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.errors
	 * @return  the value of DATA_IMPORT.errors
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getErrors() {
		return errors;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.errors
	 * @param errors  the value for DATA_IMPORT.errors
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setErrors(Integer errors) {
		this.errors = errors;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.startTime
	 * @return  the value of DATA_IMPORT.startTime
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.startTime
	 * @param starttime  the value for DATA_IMPORT.startTime
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DATA_IMPORT.endTime
	 * @return  the value of DATA_IMPORT.endTime
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DATA_IMPORT.endTime
	 * @param endtime  the value for DATA_IMPORT.endTime
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
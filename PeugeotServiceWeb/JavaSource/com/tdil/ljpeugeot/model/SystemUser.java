package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class SystemUser extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.username
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private String username;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.password
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.type
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.loggingAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer loggingaccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.syspropAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer syspropaccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.modelImportAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer modelimportaccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.dealerImportAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer dealerimportaccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.emailTemplateAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer emailtemplateaccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.nativeAppAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	private Integer nativeappaccess;
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.username
	 * @return  the value of SYSTEMUSER.username
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.username
	 * @param username  the value for SYSTEMUSER.username
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.password
	 * @return  the value of SYSTEMUSER.password
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.password
	 * @param password  the value for SYSTEMUSER.password
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.type
	 * @return  the value of SYSTEMUSER.type
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.type
	 * @param type  the value for SYSTEMUSER.type
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.loggingAccess
	 * @return  the value of SYSTEMUSER.loggingAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getLoggingaccess() {
		return loggingaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.loggingAccess
	 * @param loggingaccess  the value for SYSTEMUSER.loggingAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setLoggingaccess(Integer loggingaccess) {
		this.loggingaccess = loggingaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.syspropAccess
	 * @return  the value of SYSTEMUSER.syspropAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getSyspropaccess() {
		return syspropaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.syspropAccess
	 * @param syspropaccess  the value for SYSTEMUSER.syspropAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setSyspropaccess(Integer syspropaccess) {
		this.syspropaccess = syspropaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.modelImportAccess
	 * @return  the value of SYSTEMUSER.modelImportAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getModelimportaccess() {
		return modelimportaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.modelImportAccess
	 * @param modelimportaccess  the value for SYSTEMUSER.modelImportAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setModelimportaccess(Integer modelimportaccess) {
		this.modelimportaccess = modelimportaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.dealerImportAccess
	 * @return  the value of SYSTEMUSER.dealerImportAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getDealerimportaccess() {
		return dealerimportaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.dealerImportAccess
	 * @param dealerimportaccess  the value for SYSTEMUSER.dealerImportAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setDealerimportaccess(Integer dealerimportaccess) {
		this.dealerimportaccess = dealerimportaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.emailTemplateAccess
	 * @return  the value of SYSTEMUSER.emailTemplateAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getEmailtemplateaccess() {
		return emailtemplateaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.emailTemplateAccess
	 * @param emailtemplateaccess  the value for SYSTEMUSER.emailTemplateAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setEmailtemplateaccess(Integer emailtemplateaccess) {
		this.emailtemplateaccess = emailtemplateaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.nativeAppAccess
	 * @return  the value of SYSTEMUSER.nativeAppAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public Integer getNativeappaccess() {
		return nativeappaccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.nativeAppAccess
	 * @param nativeappaccess  the value for SYSTEMUSER.nativeAppAccess
	 * @mbggenerated  Thu Apr 03 07:35:17 ART 2014
	 */
	public void setNativeappaccess(Integer nativeappaccess) {
		this.nativeappaccess = nativeappaccess;
	}

	public static final int CALL_CENTER = 1;
	public static final int ADMIN = 0;
	public boolean isAdmin() {
		return ADMIN == this.getType();
	}
	
	public boolean isCallCenter() {
		return CALL_CENTER == this.getType();
	}
}
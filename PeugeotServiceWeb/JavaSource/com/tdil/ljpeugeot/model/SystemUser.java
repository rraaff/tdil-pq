package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;

public class SystemUser extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.username
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String username;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.password
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.type
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer type;
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.username
	 * @return  the value of SYSTEMUSER.username
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.username
	 * @param username  the value for SYSTEMUSER.username
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.password
	 * @return  the value of SYSTEMUSER.password
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.password
	 * @param password  the value for SYSTEMUSER.password
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.type
	 * @return  the value of SYSTEMUSER.type
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.type
	 * @param type  the value for SYSTEMUSER.type
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setType(Integer type) {
		this.type = type;
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
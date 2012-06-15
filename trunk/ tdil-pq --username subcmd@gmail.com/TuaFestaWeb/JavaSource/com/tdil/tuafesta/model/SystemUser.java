package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class SystemUser extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.username
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	private String username;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.password
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.name
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.email
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SYSTEMUSER.resetcode
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	private String resetcode;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.username
	 * @return  the value of SYSTEMUSER.username
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.username
	 * @param username  the value for SYSTEMUSER.username
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.password
	 * @return  the value of SYSTEMUSER.password
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.password
	 * @param password  the value for SYSTEMUSER.password
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.name
	 * @return  the value of SYSTEMUSER.name
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.name
	 * @param name  the value for SYSTEMUSER.name
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.email
	 * @return  the value of SYSTEMUSER.email
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.email
	 * @param email  the value for SYSTEMUSER.email
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SYSTEMUSER.resetcode
	 * @return  the value of SYSTEMUSER.resetcode
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public String getResetcode() {
		return resetcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SYSTEMUSER.resetcode
	 * @param resetcode  the value for SYSTEMUSER.resetcode
	 * @mbggenerated  Fri Jun 15 00:46:45 ART 2012
	 */
	public void setResetcode(String resetcode) {
		this.resetcode = resetcode == null ? null : resetcode.trim();
	}
}
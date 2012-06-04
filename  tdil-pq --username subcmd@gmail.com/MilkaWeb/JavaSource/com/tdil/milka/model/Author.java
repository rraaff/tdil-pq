package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class Author extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column AUTHOR.name
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column AUTHOR.email
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	private String email;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column AUTHOR.name
	 * @return  the value of AUTHOR.name
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column AUTHOR.name
	 * @param name  the value for AUTHOR.name
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column AUTHOR.email
	 * @return  the value of AUTHOR.email
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column AUTHOR.email
	 * @param email  the value for AUTHOR.email
	 * @mbggenerated  Sun Jun 03 20:33:04 ART 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
}
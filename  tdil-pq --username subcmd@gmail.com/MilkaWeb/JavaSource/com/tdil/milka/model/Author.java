package com.tdil.milka.model;

import com.tdil.ibatis.PersistentObject;

public class Author extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column AUTHOR.firstname
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	private String firstname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column AUTHOR.lastname
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	private String lastname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column AUTHOR.email
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	private String email;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column AUTHOR.firstname
	 * @return  the value of AUTHOR.firstname
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column AUTHOR.firstname
	 * @param firstname  the value for AUTHOR.firstname
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname == null ? null : firstname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column AUTHOR.lastname
	 * @return  the value of AUTHOR.lastname
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column AUTHOR.lastname
	 * @param lastname  the value for AUTHOR.lastname
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname == null ? null : lastname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column AUTHOR.email
	 * @return  the value of AUTHOR.email
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column AUTHOR.email
	 * @param email  the value for AUTHOR.email
	 * @mbggenerated  Sun May 06 12:40:13 ART 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
}
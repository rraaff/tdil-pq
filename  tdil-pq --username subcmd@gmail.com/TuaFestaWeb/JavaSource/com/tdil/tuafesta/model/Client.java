package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Client extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.firstname
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String firstname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.lastname
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String lastname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.sex
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String sex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.birthdate
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Date birthdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.id_geolevel
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer idGeolevel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.email
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.password
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.facebookid
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String facebookid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.emailvalid
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer emailvalid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.status
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CLIENT.verifemail
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String verifemail;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.firstname
	 * @return  the value of CLIENT.firstname
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.firstname
	 * @param firstname  the value for CLIENT.firstname
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname == null ? null : firstname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.lastname
	 * @return  the value of CLIENT.lastname
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.lastname
	 * @param lastname  the value for CLIENT.lastname
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname == null ? null : lastname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.sex
	 * @return  the value of CLIENT.sex
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.sex
	 * @param sex  the value for CLIENT.sex
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.birthdate
	 * @return  the value of CLIENT.birthdate
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.birthdate
	 * @param birthdate  the value for CLIENT.birthdate
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.id_geolevel
	 * @return  the value of CLIENT.id_geolevel
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getIdGeolevel() {
		return idGeolevel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.id_geolevel
	 * @param idGeolevel  the value for CLIENT.id_geolevel
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setIdGeolevel(Integer idGeolevel) {
		this.idGeolevel = idGeolevel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.email
	 * @return  the value of CLIENT.email
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.email
	 * @param email  the value for CLIENT.email
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.password
	 * @return  the value of CLIENT.password
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.password
	 * @param password  the value for CLIENT.password
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.facebookid
	 * @return  the value of CLIENT.facebookid
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getFacebookid() {
		return facebookid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.facebookid
	 * @param facebookid  the value for CLIENT.facebookid
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setFacebookid(String facebookid) {
		this.facebookid = facebookid == null ? null : facebookid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.emailvalid
	 * @return  the value of CLIENT.emailvalid
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getEmailvalid() {
		return emailvalid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.emailvalid
	 * @param emailvalid  the value for CLIENT.emailvalid
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setEmailvalid(Integer emailvalid) {
		this.emailvalid = emailvalid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.status
	 * @return  the value of CLIENT.status
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.status
	 * @param status  the value for CLIENT.status
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CLIENT.verifemail
	 * @return  the value of CLIENT.verifemail
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getVerifemail() {
		return verifemail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CLIENT.verifemail
	 * @param verifemail  the value for CLIENT.verifemail
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setVerifemail(String verifemail) {
		this.verifemail = verifemail == null ? null : verifemail.trim();
	}
}
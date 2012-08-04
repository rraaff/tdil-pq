package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Profesional extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.firstname
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String firstname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.lastname
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String lastname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.sex
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String sex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.birthdate
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private Date birthdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.phone
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.email
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.password
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.website
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String website;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.facebook
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String facebook;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.businesshours
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String businesshours;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.description
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.emailvalid
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private Integer emailvalid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.approved
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private Integer approved;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.datachanged
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private Integer datachanged;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.id_wall
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private Integer idWall;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROFESIONAL.verifemail
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	private String verifemail;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.firstname
	 * @return  the value of PROFESIONAL.firstname
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.firstname
	 * @param firstname  the value for PROFESIONAL.firstname
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname == null ? null : firstname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.lastname
	 * @return  the value of PROFESIONAL.lastname
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.lastname
	 * @param lastname  the value for PROFESIONAL.lastname
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname == null ? null : lastname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.sex
	 * @return  the value of PROFESIONAL.sex
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.sex
	 * @param sex  the value for PROFESIONAL.sex
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.birthdate
	 * @return  the value of PROFESIONAL.birthdate
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.birthdate
	 * @param birthdate  the value for PROFESIONAL.birthdate
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.phone
	 * @return  the value of PROFESIONAL.phone
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.phone
	 * @param phone  the value for PROFESIONAL.phone
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.email
	 * @return  the value of PROFESIONAL.email
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.email
	 * @param email  the value for PROFESIONAL.email
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.password
	 * @return  the value of PROFESIONAL.password
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.password
	 * @param password  the value for PROFESIONAL.password
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.website
	 * @return  the value of PROFESIONAL.website
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.website
	 * @param website  the value for PROFESIONAL.website
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setWebsite(String website) {
		this.website = website == null ? null : website.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.facebook
	 * @return  the value of PROFESIONAL.facebook
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getFacebook() {
		return facebook;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.facebook
	 * @param facebook  the value for PROFESIONAL.facebook
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook == null ? null : facebook.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.businesshours
	 * @return  the value of PROFESIONAL.businesshours
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getBusinesshours() {
		return businesshours;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.businesshours
	 * @param businesshours  the value for PROFESIONAL.businesshours
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours == null ? null : businesshours.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.description
	 * @return  the value of PROFESIONAL.description
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.description
	 * @param description  the value for PROFESIONAL.description
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.emailvalid
	 * @return  the value of PROFESIONAL.emailvalid
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Integer getEmailvalid() {
		return emailvalid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.emailvalid
	 * @param emailvalid  the value for PROFESIONAL.emailvalid
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setEmailvalid(Integer emailvalid) {
		this.emailvalid = emailvalid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.approved
	 * @return  the value of PROFESIONAL.approved
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.approved
	 * @param approved  the value for PROFESIONAL.approved
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.datachanged
	 * @return  the value of PROFESIONAL.datachanged
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Integer getDatachanged() {
		return datachanged;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.datachanged
	 * @param datachanged  the value for PROFESIONAL.datachanged
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setDatachanged(Integer datachanged) {
		this.datachanged = datachanged;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.id_wall
	 * @return  the value of PROFESIONAL.id_wall
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Integer getIdWall() {
		return idWall;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.id_wall
	 * @param idWall  the value for PROFESIONAL.id_wall
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setIdWall(Integer idWall) {
		this.idWall = idWall;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROFESIONAL.verifemail
	 * @return  the value of PROFESIONAL.verifemail
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getVerifemail() {
		return verifemail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROFESIONAL.verifemail
	 * @param verifemail  the value for PROFESIONAL.verifemail
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setVerifemail(String verifemail) {
		this.verifemail = verifemail == null ? null : verifemail.trim();
	}
}
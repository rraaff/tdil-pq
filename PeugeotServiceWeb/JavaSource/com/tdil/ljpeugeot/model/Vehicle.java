package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Vehicle extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.id_websiteuser
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer idWebsiteuser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.id_model
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer idModel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.description
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.km
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer km;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.lastServiceKm
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer lastservicekm;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.lastServiceDate
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Date lastservicedate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice1
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer needsadvice1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.advice1sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer advice1sent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice2
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer needsadvice2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.advice2sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer advice2sent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice3
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer needsadvice3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.advice3sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer advice3sent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.warrantyExpired
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private Integer warrantyexpired;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.id_websiteuser
	 * @return  the value of VEHICLE.id_websiteuser
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getIdWebsiteuser() {
		return idWebsiteuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.id_websiteuser
	 * @param idWebsiteuser  the value for VEHICLE.id_websiteuser
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setIdWebsiteuser(Integer idWebsiteuser) {
		this.idWebsiteuser = idWebsiteuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.id_model
	 * @return  the value of VEHICLE.id_model
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getIdModel() {
		return idModel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.id_model
	 * @param idModel  the value for VEHICLE.id_model
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.description
	 * @return  the value of VEHICLE.description
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.description
	 * @param description  the value for VEHICLE.description
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.km
	 * @return  the value of VEHICLE.km
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.km
	 * @param km  the value for VEHICLE.km
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.lastServiceKm
	 * @return  the value of VEHICLE.lastServiceKm
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getLastservicekm() {
		return lastservicekm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.lastServiceKm
	 * @param lastservicekm  the value for VEHICLE.lastServiceKm
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setLastservicekm(Integer lastservicekm) {
		this.lastservicekm = lastservicekm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.lastServiceDate
	 * @return  the value of VEHICLE.lastServiceDate
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Date getLastservicedate() {
		return lastservicedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.lastServiceDate
	 * @param lastservicedate  the value for VEHICLE.lastServiceDate
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setLastservicedate(Date lastservicedate) {
		this.lastservicedate = lastservicedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice1
	 * @return  the value of VEHICLE.needsAdvice1
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getNeedsadvice1() {
		return needsadvice1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice1
	 * @param needsadvice1  the value for VEHICLE.needsAdvice1
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setNeedsadvice1(Integer needsadvice1) {
		this.needsadvice1 = needsadvice1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.advice1sent
	 * @return  the value of VEHICLE.advice1sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getAdvice1sent() {
		return advice1sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.advice1sent
	 * @param advice1sent  the value for VEHICLE.advice1sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setAdvice1sent(Integer advice1sent) {
		this.advice1sent = advice1sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice2
	 * @return  the value of VEHICLE.needsAdvice2
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getNeedsadvice2() {
		return needsadvice2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice2
	 * @param needsadvice2  the value for VEHICLE.needsAdvice2
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setNeedsadvice2(Integer needsadvice2) {
		this.needsadvice2 = needsadvice2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.advice2sent
	 * @return  the value of VEHICLE.advice2sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getAdvice2sent() {
		return advice2sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.advice2sent
	 * @param advice2sent  the value for VEHICLE.advice2sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setAdvice2sent(Integer advice2sent) {
		this.advice2sent = advice2sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice3
	 * @return  the value of VEHICLE.needsAdvice3
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getNeedsadvice3() {
		return needsadvice3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice3
	 * @param needsadvice3  the value for VEHICLE.needsAdvice3
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setNeedsadvice3(Integer needsadvice3) {
		this.needsadvice3 = needsadvice3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.advice3sent
	 * @return  the value of VEHICLE.advice3sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getAdvice3sent() {
		return advice3sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.advice3sent
	 * @param advice3sent  the value for VEHICLE.advice3sent
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setAdvice3sent(Integer advice3sent) {
		this.advice3sent = advice3sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.warrantyExpired
	 * @return  the value of VEHICLE.warrantyExpired
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer getWarrantyexpired() {
		return warrantyexpired;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.warrantyExpired
	 * @param warrantyexpired  the value for VEHICLE.warrantyExpired
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public void setWarrantyexpired(Integer warrantyexpired) {
		this.warrantyexpired = warrantyexpired;
	}
}
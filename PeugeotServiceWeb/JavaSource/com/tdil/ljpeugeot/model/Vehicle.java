package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Vehicle extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.id_websiteuser
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer idWebsiteuser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.id_model
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer idModel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.id_dealer
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer idDealer;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.purchaseDate
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Date purchasedate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.domain
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String domain;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.description
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.km
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer km;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.lastServiceKm
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer lastservicekm;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.lastServiceDate
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Date lastservicedate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer needsadvice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice1
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer needsadvice1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.advice1sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer advice1sent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice2
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer needsadvice2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.advice2sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer advice2sent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.needsAdvice3
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer needsadvice3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.advice3sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer advice3sent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column VEHICLE.warrantyExpired
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private Integer warrantyexpired;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.id_websiteuser
	 * @return  the value of VEHICLE.id_websiteuser
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getIdWebsiteuser() {
		return idWebsiteuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.id_websiteuser
	 * @param idWebsiteuser  the value for VEHICLE.id_websiteuser
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setIdWebsiteuser(Integer idWebsiteuser) {
		this.idWebsiteuser = idWebsiteuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.id_model
	 * @return  the value of VEHICLE.id_model
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getIdModel() {
		return idModel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.id_model
	 * @param idModel  the value for VEHICLE.id_model
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.id_dealer
	 * @return  the value of VEHICLE.id_dealer
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getIdDealer() {
		return idDealer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.id_dealer
	 * @param idDealer  the value for VEHICLE.id_dealer
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setIdDealer(Integer idDealer) {
		this.idDealer = idDealer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.purchaseDate
	 * @return  the value of VEHICLE.purchaseDate
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Date getPurchasedate() {
		return purchasedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.purchaseDate
	 * @param purchasedate  the value for VEHICLE.purchaseDate
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.domain
	 * @return  the value of VEHICLE.domain
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.domain
	 * @param domain  the value for VEHICLE.domain
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setDomain(String domain) {
		this.domain = domain == null ? null : domain.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.description
	 * @return  the value of VEHICLE.description
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.description
	 * @param description  the value for VEHICLE.description
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.km
	 * @return  the value of VEHICLE.km
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.km
	 * @param km  the value for VEHICLE.km
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.lastServiceKm
	 * @return  the value of VEHICLE.lastServiceKm
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getLastservicekm() {
		return lastservicekm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.lastServiceKm
	 * @param lastservicekm  the value for VEHICLE.lastServiceKm
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setLastservicekm(Integer lastservicekm) {
		this.lastservicekm = lastservicekm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.lastServiceDate
	 * @return  the value of VEHICLE.lastServiceDate
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Date getLastservicedate() {
		return lastservicedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.lastServiceDate
	 * @param lastservicedate  the value for VEHICLE.lastServiceDate
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setLastservicedate(Date lastservicedate) {
		this.lastservicedate = lastservicedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice
	 * @return  the value of VEHICLE.needsAdvice
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getNeedsadvice() {
		return needsadvice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice
	 * @param needsadvice  the value for VEHICLE.needsAdvice
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setNeedsadvice(Integer needsadvice) {
		this.needsadvice = needsadvice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice1
	 * @return  the value of VEHICLE.needsAdvice1
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getNeedsadvice1() {
		return needsadvice1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice1
	 * @param needsadvice1  the value for VEHICLE.needsAdvice1
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setNeedsadvice1(Integer needsadvice1) {
		this.needsadvice1 = needsadvice1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.advice1sent
	 * @return  the value of VEHICLE.advice1sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getAdvice1sent() {
		return advice1sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.advice1sent
	 * @param advice1sent  the value for VEHICLE.advice1sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setAdvice1sent(Integer advice1sent) {
		this.advice1sent = advice1sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice2
	 * @return  the value of VEHICLE.needsAdvice2
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getNeedsadvice2() {
		return needsadvice2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice2
	 * @param needsadvice2  the value for VEHICLE.needsAdvice2
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setNeedsadvice2(Integer needsadvice2) {
		this.needsadvice2 = needsadvice2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.advice2sent
	 * @return  the value of VEHICLE.advice2sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getAdvice2sent() {
		return advice2sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.advice2sent
	 * @param advice2sent  the value for VEHICLE.advice2sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setAdvice2sent(Integer advice2sent) {
		this.advice2sent = advice2sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.needsAdvice3
	 * @return  the value of VEHICLE.needsAdvice3
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getNeedsadvice3() {
		return needsadvice3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.needsAdvice3
	 * @param needsadvice3  the value for VEHICLE.needsAdvice3
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setNeedsadvice3(Integer needsadvice3) {
		this.needsadvice3 = needsadvice3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.advice3sent
	 * @return  the value of VEHICLE.advice3sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getAdvice3sent() {
		return advice3sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.advice3sent
	 * @param advice3sent  the value for VEHICLE.advice3sent
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setAdvice3sent(Integer advice3sent) {
		this.advice3sent = advice3sent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column VEHICLE.warrantyExpired
	 * @return  the value of VEHICLE.warrantyExpired
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer getWarrantyexpired() {
		return warrantyexpired;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column VEHICLE.warrantyExpired
	 * @param warrantyexpired  the value for VEHICLE.warrantyExpired
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setWarrantyexpired(Integer warrantyexpired) {
		this.warrantyexpired = warrantyexpired;
	}
}
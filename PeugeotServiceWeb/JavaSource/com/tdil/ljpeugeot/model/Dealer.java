package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.math.BigDecimal;

public class Dealer extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.id_data_import
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private Integer idDataImport;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.name
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.address
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.email
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.phone
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.id_city
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private Integer idCity;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.lat
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private BigDecimal lat;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DEALER.lon
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	private BigDecimal lon;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.id_data_import
	 * @return  the value of DEALER.id_data_import
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public Integer getIdDataImport() {
		return idDataImport;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.id_data_import
	 * @param idDataImport  the value for DEALER.id_data_import
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setIdDataImport(Integer idDataImport) {
		this.idDataImport = idDataImport;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.name
	 * @return  the value of DEALER.name
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.name
	 * @param name  the value for DEALER.name
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.address
	 * @return  the value of DEALER.address
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.address
	 * @param address  the value for DEALER.address
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.email
	 * @return  the value of DEALER.email
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.email
	 * @param email  the value for DEALER.email
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.phone
	 * @return  the value of DEALER.phone
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.phone
	 * @param phone  the value for DEALER.phone
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.id_city
	 * @return  the value of DEALER.id_city
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public Integer getIdCity() {
		return idCity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.id_city
	 * @param idCity  the value for DEALER.id_city
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.lat
	 * @return  the value of DEALER.lat
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public BigDecimal getLat() {
		return lat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.lat
	 * @param lat  the value for DEALER.lat
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DEALER.lon
	 * @return  the value of DEALER.lon
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public BigDecimal getLon() {
		return lon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DEALER.lon
	 * @param lon  the value for DEALER.lon
	 * @mbggenerated  Sat Feb 01 22:51:52 ART 2014
	 */
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
}
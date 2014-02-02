package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Service extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SERVICE.id_vechicle
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	private Integer idVechicle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SERVICE.km
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	private Integer km;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SERVICE.serviceDate
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	private Date servicedate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SERVICE.id_vechicle
	 * @return  the value of SERVICE.id_vechicle
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public Integer getIdVechicle() {
		return idVechicle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SERVICE.id_vechicle
	 * @param idVechicle  the value for SERVICE.id_vechicle
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public void setIdVechicle(Integer idVechicle) {
		this.idVechicle = idVechicle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SERVICE.km
	 * @return  the value of SERVICE.km
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SERVICE.km
	 * @param km  the value for SERVICE.km
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SERVICE.serviceDate
	 * @return  the value of SERVICE.serviceDate
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public Date getServicedate() {
		return servicedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SERVICE.serviceDate
	 * @param servicedate  the value for SERVICE.serviceDate
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public void setServicedate(Date servicedate) {
		this.servicedate = servicedate;
	}
}
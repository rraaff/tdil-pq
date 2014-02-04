package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Service extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SERVICE.id_vehicle
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private Integer idVehicle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SERVICE.km
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private Integer km;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SERVICE.serviceDate
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private Date servicedate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SERVICE.id_vehicle
	 * @return  the value of SERVICE.id_vehicle
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Integer getIdVehicle() {
		return idVehicle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SERVICE.id_vehicle
	 * @param idVehicle  the value for SERVICE.id_vehicle
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setIdVehicle(Integer idVehicle) {
		this.idVehicle = idVehicle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SERVICE.km
	 * @return  the value of SERVICE.km
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SERVICE.km
	 * @param km  the value for SERVICE.km
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SERVICE.serviceDate
	 * @return  the value of SERVICE.serviceDate
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Date getServicedate() {
		return servicedate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SERVICE.serviceDate
	 * @param servicedate  the value for SERVICE.serviceDate
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setServicedate(Date servicedate) {
		this.servicedate = servicedate;
	}
}
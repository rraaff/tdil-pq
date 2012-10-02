package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Geo2 extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO2.nombre
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	private String nombre;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO2.availableForService
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	private Integer availableforservice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO2.showInHome
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	private Integer showinhome;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO2.homeIndex
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	private Integer homeindex;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO2.nombre
	 * @return  the value of GEO2.nombre
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO2.nombre
	 * @param nombre  the value for GEO2.nombre
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? null : nombre.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO2.availableForService
	 * @return  the value of GEO2.availableForService
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public Integer getAvailableforservice() {
		return availableforservice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO2.availableForService
	 * @param availableforservice  the value for GEO2.availableForService
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public void setAvailableforservice(Integer availableforservice) {
		this.availableforservice = availableforservice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO2.showInHome
	 * @return  the value of GEO2.showInHome
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public Integer getShowinhome() {
		return showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO2.showInHome
	 * @param showinhome  the value for GEO2.showInHome
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public void setShowinhome(Integer showinhome) {
		this.showinhome = showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO2.homeIndex
	 * @return  the value of GEO2.homeIndex
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public Integer getHomeindex() {
		return homeindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO2.homeIndex
	 * @param homeindex  the value for GEO2.homeIndex
	 * @mbggenerated  Mon Oct 01 21:07:50 ART 2012
	 */
	public void setHomeindex(Integer homeindex) {
		this.homeindex = homeindex;
	}
}
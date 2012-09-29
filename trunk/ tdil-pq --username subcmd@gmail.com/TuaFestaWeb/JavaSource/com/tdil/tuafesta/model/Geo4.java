package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Geo4 extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.geo3_id
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer geo3Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.nombre
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private String nombre;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.availableForService
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer availableforservice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.showInHome
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer showinhome;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.homeIndex
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	private Integer homeindex;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.geo3_id
	 * @return  the value of GEO4.geo3_id
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getGeo3Id() {
		return geo3Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.geo3_id
	 * @param geo3Id  the value for GEO4.geo3_id
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setGeo3Id(Integer geo3Id) {
		this.geo3Id = geo3Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.nombre
	 * @return  the value of GEO4.nombre
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.nombre
	 * @param nombre  the value for GEO4.nombre
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? null : nombre.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.availableForService
	 * @return  the value of GEO4.availableForService
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getAvailableforservice() {
		return availableforservice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.availableForService
	 * @param availableforservice  the value for GEO4.availableForService
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setAvailableforservice(Integer availableforservice) {
		this.availableforservice = availableforservice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.showInHome
	 * @return  the value of GEO4.showInHome
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getShowinhome() {
		return showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.showInHome
	 * @param showinhome  the value for GEO4.showInHome
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setShowinhome(Integer showinhome) {
		this.showinhome = showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.homeIndex
	 * @return  the value of GEO4.homeIndex
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public Integer getHomeindex() {
		return homeindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.homeIndex
	 * @param homeindex  the value for GEO4.homeIndex
	 * @mbggenerated  Sat Sep 29 00:10:00 ART 2012
	 */
	public void setHomeindex(Integer homeindex) {
		this.homeindex = homeindex;
	}
}
package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Geo3 extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.geo2_id
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	private Integer geo2Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.nombre
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	private String nombre;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.availableForService
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	private Integer availableforservice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.showInHome
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	private Integer showinhome;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.homeIndex
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	private Integer homeindex;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.geo2_id
	 * @return  the value of GEO3.geo2_id
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Integer getGeo2Id() {
		return geo2Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.geo2_id
	 * @param geo2Id  the value for GEO3.geo2_id
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setGeo2Id(Integer geo2Id) {
		this.geo2Id = geo2Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.nombre
	 * @return  the value of GEO3.nombre
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.nombre
	 * @param nombre  the value for GEO3.nombre
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? null : nombre.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.availableForService
	 * @return  the value of GEO3.availableForService
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Integer getAvailableforservice() {
		return availableforservice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.availableForService
	 * @param availableforservice  the value for GEO3.availableForService
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setAvailableforservice(Integer availableforservice) {
		this.availableforservice = availableforservice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.showInHome
	 * @return  the value of GEO3.showInHome
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Integer getShowinhome() {
		return showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.showInHome
	 * @param showinhome  the value for GEO3.showInHome
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setShowinhome(Integer showinhome) {
		this.showinhome = showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.homeIndex
	 * @return  the value of GEO3.homeIndex
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Integer getHomeindex() {
		return homeindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.homeIndex
	 * @param homeindex  the value for GEO3.homeIndex
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setHomeindex(Integer homeindex) {
		this.homeindex = homeindex;
	}
}
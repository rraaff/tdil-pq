package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Geo4 extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.geo3_id
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	private Integer geo3Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO4.nombre
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	private String nombre;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.geo3_id
	 * @return  the value of GEO4.geo3_id
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	public Integer getGeo3Id() {
		return geo3Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.geo3_id
	 * @param geo3Id  the value for GEO4.geo3_id
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	public void setGeo3Id(Integer geo3Id) {
		this.geo3Id = geo3Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO4.nombre
	 * @return  the value of GEO4.nombre
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO4.nombre
	 * @param nombre  the value for GEO4.nombre
	 * @mbggenerated  Sat Aug 11 00:25:13 ART 2012
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? null : nombre.trim();
	}
}
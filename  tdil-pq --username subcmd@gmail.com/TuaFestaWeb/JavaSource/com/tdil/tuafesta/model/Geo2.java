package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Geo2 extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO2.nombre
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	private String nombre;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO2.nombre
	 * @return  the value of GEO2.nombre
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO2.nombre
	 * @param nombre  the value for GEO2.nombre
	 * @mbggenerated  Mon Jun 18 23:18:25 ART 2012
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? null : nombre.trim();
	}
}
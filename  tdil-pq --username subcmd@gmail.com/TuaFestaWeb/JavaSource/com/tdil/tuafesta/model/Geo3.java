package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Geo3 extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.geo2_id
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private Integer geo2Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column GEO3.nombre
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	private String nombre;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.geo2_id
	 * @return  the value of GEO3.geo2_id
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public Integer getGeo2Id() {
		return geo2Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.geo2_id
	 * @param geo2Id  the value for GEO3.geo2_id
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setGeo2Id(Integer geo2Id) {
		this.geo2Id = geo2Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column GEO3.nombre
	 * @return  the value of GEO3.nombre
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column GEO3.nombre
	 * @param nombre  the value for GEO3.nombre
	 * @mbggenerated  Tue Sep 18 00:21:02 ART 2012
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? null : nombre.trim();
	}
}
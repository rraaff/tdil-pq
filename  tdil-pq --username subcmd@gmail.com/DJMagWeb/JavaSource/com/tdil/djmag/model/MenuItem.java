package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class MenuItem extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MENUITEM.id_country
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private Integer idCountry;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MENUITEM.id_section
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private Integer idSection;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MENUITEM.name
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MENUITEM.position
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private Integer position;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MENUITEM.id_country
	 * @return  the value of MENUITEM.id_country
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer getIdCountry() {
		return idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MENUITEM.id_country
	 * @param idCountry  the value for MENUITEM.id_country
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MENUITEM.id_section
	 * @return  the value of MENUITEM.id_section
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer getIdSection() {
		return idSection;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MENUITEM.id_section
	 * @param idSection  the value for MENUITEM.id_section
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setIdSection(Integer idSection) {
		this.idSection = idSection;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MENUITEM.name
	 * @return  the value of MENUITEM.name
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MENUITEM.name
	 * @param name  the value for MENUITEM.name
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MENUITEM.position
	 * @return  the value of MENUITEM.position
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MENUITEM.position
	 * @param position  the value for MENUITEM.position
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
}
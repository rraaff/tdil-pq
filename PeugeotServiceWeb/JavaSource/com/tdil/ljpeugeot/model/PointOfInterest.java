package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.math.BigDecimal;

public class PointOfInterest extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POI.type
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POI.subtype
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private Integer subtype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POI.name
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POI.description
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POI.lat
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private BigDecimal lat;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POI.lon
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private BigDecimal lon;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POI.type
	 * @return  the value of POI.type
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POI.type
	 * @param type  the value for POI.type
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POI.subtype
	 * @return  the value of POI.subtype
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Integer getSubtype() {
		return subtype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POI.subtype
	 * @param subtype  the value for POI.subtype
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setSubtype(Integer subtype) {
		this.subtype = subtype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POI.name
	 * @return  the value of POI.name
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POI.name
	 * @param name  the value for POI.name
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POI.description
	 * @return  the value of POI.description
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POI.description
	 * @param description  the value for POI.description
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POI.lat
	 * @return  the value of POI.lat
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public BigDecimal getLat() {
		return lat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POI.lat
	 * @param lat  the value for POI.lat
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POI.lon
	 * @return  the value of POI.lon
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public BigDecimal getLon() {
		return lon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POI.lon
	 * @param lon  the value for POI.lon
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
}
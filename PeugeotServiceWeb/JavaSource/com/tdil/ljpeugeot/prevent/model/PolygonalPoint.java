package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="item")
public class PolygonalPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6777155349639214902L;
	
	@XStreamAlias(value="longitude")
	private String longitude;
	@XStreamAlias(value="latitude")
	private String latitude;
	
	public PolygonalPoint() {
		// TODO Auto-generated constructor stub
	}
	
	public PolygonalPoint(String latitude, String longitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	

}

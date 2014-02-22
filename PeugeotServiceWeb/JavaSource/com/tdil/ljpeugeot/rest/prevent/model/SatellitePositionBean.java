package com.tdil.ljpeugeot.rest.prevent.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.prevent.model.SatellitePosition;
import com.tdil.log4j.LoggerProvider;
public class SatellitePositionBean implements Serializable {

	private static final long serialVersionUID = 594597535382996379L;

	private String longitude;
	private String latitude;
	private String street;
	private String number;
	private String zoom;
	
	public SatellitePositionBean(SatellitePosition vehicle) {
		try {
			BeanUtils.copyProperties(this, vehicle);
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getZoom() {
		return zoom;
	}
	public void setZoom(String zoom) {
		this.zoom = zoom;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SatellitePosition [");
		if (longitude != null) {
			builder.append("longitude=");
			builder.append(longitude);
			builder.append(", ");
		}
		if (latitude != null) {
			builder.append("latitude=");
			builder.append(latitude);
			builder.append(", ");
		}
		if (street != null) {
			builder.append("street=");
			builder.append(street);
			builder.append(", ");
		}
		if (number != null) {
			builder.append("number=");
			builder.append(number);
			builder.append(", ");
		}
		if (zoom != null) {
			builder.append("zoom=");
			builder.append(zoom);
		}
		builder.append(']');
		return builder.toString();
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SatellitePositionBean.class);
	}	

}

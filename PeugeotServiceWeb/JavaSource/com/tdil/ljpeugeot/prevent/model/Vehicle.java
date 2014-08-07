package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="Vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 4830779533951262329L;

	@XStreamAlias(value="Status")
	private String status;
	@XStreamAlias(value="ID")
	private String id;
	@XStreamAlias(value="Description")
	private String description;
	@XStreamAlias(value="Brand")
	private String brand;
	@XStreamAlias(value="Model")
	private String model;
	@XStreamAlias(value="Year")
	private String year;
	@XStreamAlias(value="DateTime")
	private String dateTime;
	@XStreamAlias(value="Latitude")
	private String latitude;
	@XStreamAlias(value="Longitude")
	private String longitude;
	@XStreamAlias(value="Speed")
	private String speed;
	@XStreamAlias(value="Direction")
	private String direction;
	@XStreamAlias(value="GeoRef")
	private String geoRef;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String iD) {
		this.id = iD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getGeoRef() {
		return geoRef;
	}

	public void setGeoRef(String geoRef) {
		this.geoRef = geoRef;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehicle [");
		if (id != null) {
			builder.append("iD=");
			builder.append(id);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (brand != null) {
			builder.append("brand=");
			builder.append(brand);
			builder.append(", ");
		}
		if (model != null) {
			builder.append("model=");
			builder.append(model);
			builder.append(", ");
		}
		if (year != null) {
			builder.append("year=");
			builder.append(year);
			builder.append(", ");
		}
		if (dateTime != null) {
			builder.append("dateTime=");
			builder.append(dateTime);
			builder.append(", ");
		}
		if (latitude != null) {
			builder.append("latitude=");
			builder.append(latitude);
			builder.append(", ");
		}
		if (longitude != null) {
			builder.append("longitude=");
			builder.append(longitude);
			builder.append(", ");
		}
		if (speed != null) {
			builder.append("speed=");
			builder.append(speed);
			builder.append(", ");
		}
		if (direction != null) {
			builder.append("direction=");
			builder.append(direction);
			builder.append(", ");
		}
		if (geoRef != null) {
			builder.append("geoRef=");
			builder.append(geoRef);
		}
		builder.append(']');
		return builder.toString();
	}
	
	
}
package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias(value="PolygonalSecureZone")
public class PolygonalSecureZone implements Serializable {

	private static final long serialVersionUID = 594597535382996379L;

	@XStreamAlias(value="description")
	private String description;
	
	@XStreamAlias(value="poins")
	private List<PolygonalPoint> points;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<PolygonalPoint> getPoints() {
		return points;
	}
	public void setPoints(List<PolygonalPoint> points) {
		this.points = points;
	}
	
}

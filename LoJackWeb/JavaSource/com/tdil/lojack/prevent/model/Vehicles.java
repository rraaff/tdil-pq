package com.tdil.lojack.prevent.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="Vehicles")
public class Vehicles implements Serializable {

	private static final long serialVersionUID = 5656816052690262250L;

	@XStreamAlias(value="VehiclesCollection")
	private List<Vehicle> vehiclesCollection;
	@XStreamAlias(value="NextStatus")
	private String nextStatus;
	@XStreamAlias(value="NextColor")
	private String nextColor;
	@XStreamAlias(value="NextIndex")
	private String nextIndex;
	@XStreamAlias(value="PreviousStatus")
	private String previousStatus;
	@XStreamAlias(value="PreviousColor")
	private String previousColor;
	@XStreamAlias(value="PreviousIndex")
	private String previousIndex;
	
	public List<Vehicle> getVehiclesCollection() {
		return vehiclesCollection;
	}
	public void setVehiclesCollection(List<Vehicle> vehiclesCollection) {
		this.vehiclesCollection = vehiclesCollection;
	}
	public String getNextStatus() {
		return nextStatus;
	}
	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}
	public String getNextColor() {
		return nextColor;
	}
	public void setNextColor(String nextColor) {
		this.nextColor = nextColor;
	}
	public String getNextIndex() {
		return nextIndex;
	}
	public void setNextIndex(String nextIndex) {
		this.nextIndex = nextIndex;
	}
	public String getPreviousStatus() {
		return previousStatus;
	}
	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}
	public String getPreviousColor() {
		return previousColor;
	}
	public void setPreviousColor(String previousColor) {
		this.previousColor = previousColor;
	}
	public String getPreviousIndex() {
		return previousIndex;
	}
	public void setPreviousIndex(String previousIndex) {
		this.previousIndex = previousIndex;
	}
}

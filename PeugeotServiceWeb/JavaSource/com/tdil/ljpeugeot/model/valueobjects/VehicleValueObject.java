package com.tdil.ljpeugeot.model.valueobjects;

import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.Vehicle;

public class VehicleValueObject {

	private Vehicle vehicle;
	private Model model;
	
	public VehicleValueObject(Vehicle vehicle, Model model) {
		super();
		this.vehicle = vehicle;
		this.model = model;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	public Model getModel() {
		return model;
	}
}

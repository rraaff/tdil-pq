package com.tdil.ljpeugeot.model.valueobjects;

import com.tdil.ljpeugeot.model.Advice;
import com.tdil.ljpeugeot.model.Vehicle;

public class AdviceValueObject {

	private Advice advice;
	private Vehicle vehicle;
	
	public AdviceValueObject(Advice advice, Vehicle vehicle) {
		super();
		this.advice = advice;
		this.vehicle = vehicle;
	}
	
	public Advice getAdvice() {
		return advice;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	
}

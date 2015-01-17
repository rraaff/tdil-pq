package com.tdil.peugeotservice.android.car;

import android.view.View;

import com.tdil.peugeotservice.android.car.menu.ToggleCarsMenuOnClick;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.RESTClientTaskOpt;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.model.prevent.VehicleCollection;

public final class CarsSpeedOnClick implements View.OnClickListener {
	private ActivityCars activityCars;
	private ToggleCarsMenuOnClick menuListener;
	
	public CarsSpeedOnClick(ActivityCars activityCars, ToggleCarsMenuOnClick menuListener) {
		super();
		this.activityCars = activityCars;
		this.menuListener = menuListener;
	}

	@Override
	public void onClick(View v) {
		menuListener.toggle();
		activityCars.option = VehicleOption.SPEED;
		if (activityCars.vehicles == null) {
			new RESTClientTaskOpt<VehicleCollection>(activityCars, HttpMethod.GET, activityCars.selectVehicleSpeedObserver, RESTConstants.GET_VEHICLES, null,null,VehicleCollection.class).executeSerial((Void) null);
		} else {
			activityCars.selectVehicleAndContinue();
		}
	}
}
package com.tdil.thalamus.android.car;

import android.view.View;

import com.tdil.thalamus.android.menu.cars.ToggleCarsMenuOnClick;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.prevent.VehicleCollection;

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
			new RESTClientTaskOpt<VehicleCollection>(activityCars, HttpMethod.GET, activityCars.selectVehicleSpeedObserver, RESTConstants.GET_VEHICLES, null,null,VehicleCollection.class).execute((Void) null);
		} else {
			activityCars.selectVehicleAndContinue();
		}
	}
}
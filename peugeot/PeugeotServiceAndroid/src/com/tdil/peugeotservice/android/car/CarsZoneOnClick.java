package com.tdil.peugeotservice.android.car;

import android.view.View;

import com.tdil.peugeotservice.android.car.menu.ToggleCarsMenuOnClick;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.RESTClientTaskOpt;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.model.prevent.VehicleCollection;

public final class CarsZoneOnClick implements View.OnClickListener {
	private ActivityCars activity;
	private ToggleCarsMenuOnClick menuListener;
	
	public CarsZoneOnClick(ActivityCars activity, ToggleCarsMenuOnClick menuListener) {
		super();
		this.activity = activity;
		this.menuListener = menuListener;
	}

	@Override
	public void onClick(View v) {
		menuListener.toggle();
		activity.option = VehicleOption.ZONE;
		if (activity.vehicles == null) {
			new RESTClientTaskOpt<VehicleCollection>(activity, HttpMethod.GET, activity.selectVehicleSpeedObserver, RESTConstants.GET_VEHICLES, null,null, VehicleCollection.class).executeSerial((Void) null);
		} else {
			activity.selectVehicleAndContinue();
		}
	}
}
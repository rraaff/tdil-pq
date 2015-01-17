package com.tdil.peugeotservice.android.car;

import android.view.View;

import com.tdil.peugeotservice.android.car.menu.ToggleCarsMenuOnClick;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.RESTClientTaskOpt;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.model.prevent.VehicleCollection;

public final class CarsPositionsOnClick implements View.OnClickListener {
	private ActivityCars activity;
	private ToggleCarsMenuOnClick menuListener;
	
	public CarsPositionsOnClick(ActivityCars activity, ToggleCarsMenuOnClick menuListener) {
		super();
		this.activity = activity;
		this.menuListener = menuListener;
	}

	@Override
	public void onClick(View v) {
		menuListener.toggle();
		new RESTClientTaskOpt<VehicleCollection>(activity, HttpMethod.GET, activity.positionsObserver, RESTConstants.GET_VEHICLES, null,null,VehicleCollection.class).executeSerial((Void) null);
	}
}
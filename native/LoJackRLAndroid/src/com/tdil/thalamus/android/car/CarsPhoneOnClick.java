package com.tdil.thalamus.android.car;

import android.view.View;

import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.prevent.VehicleCollection;

public final class CarsPhoneOnClick implements View.OnClickListener {
	private ActivityCars activity;
	
	public CarsPhoneOnClick(ActivityCars activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		activity.option = VehicleOption.PHONE_NUMBERS;
		if (activity.vehicles == null) {
			new RESTClientTaskOpt<VehicleCollection>(activity, HttpMethod.GET, activity.selectVehicleSpeedObserver, RESTConstants.GET_VEHICLES, null,null,VehicleCollection.class).execute((Void) null);
		} else {
			activity.selectVehicleAndContinue();
		}
	}
}
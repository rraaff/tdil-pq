package com.tdil.thalamus.android.car;

import android.view.View;

import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.prevent.VehicleCollection;

public final class CarsPositionsOnClick implements View.OnClickListener {
	private ActivityCars activity;
	
	public CarsPositionsOnClick(ActivityCars activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		new RESTClientTaskOpt<VehicleCollection>(activity, HttpMethod.GET, activity.positionsObserver, RESTConstants.GET_VEHICLES, null,null,VehicleCollection.class).execute((Void) null);
	}
}
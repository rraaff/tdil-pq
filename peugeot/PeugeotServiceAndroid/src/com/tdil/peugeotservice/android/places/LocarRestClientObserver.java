package com.tdil.peugeotservice.android.places;

import com.tdil.peugeotservice.android.car.ActivityCars;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.utils.Messages;

public abstract class LocarRestClientObserver implements IRestClientObserver {

	protected ActivityCars activity;
	
	public LocarRestClientObserver(ActivityCars activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(activity);
	}
}

package com.tdil.thalamus.android.places;

import com.tdil.thalamus.android.car.ActivityCars;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.utils.Messages;

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

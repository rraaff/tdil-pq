package com.tdil.thalamus.android;

import android.app.Activity;

import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.utils.Messages;

public abstract class ActivityRestClientObserver implements IRestClientObserver {

	protected Activity activity;
	
	public ActivityRestClientObserver(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(activity);
	}
}

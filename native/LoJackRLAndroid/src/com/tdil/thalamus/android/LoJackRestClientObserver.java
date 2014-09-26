package com.tdil.thalamus.android;

import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.utils.Messages;

public abstract class LoJackRestClientObserver implements IRestClientObserver {

	protected LoJackActivity activity;
	
	public LoJackRestClientObserver(LoJackActivity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(activity);
	}
}

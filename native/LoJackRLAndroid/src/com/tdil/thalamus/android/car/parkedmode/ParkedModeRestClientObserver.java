package com.tdil.thalamus.android.car.parkedmode;

import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.utils.Messages;

public abstract class ParkedModeRestClientObserver implements IRestClientObserver {

	protected LoJackActivity activity;
	
	public ParkedModeRestClientObserver(LoJackActivity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(activity);
	}
}

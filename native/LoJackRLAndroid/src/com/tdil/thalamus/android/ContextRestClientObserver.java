package com.tdil.thalamus.android;

import android.content.Context;

import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.utils.Messages;

public abstract class ContextRestClientObserver implements IRestClientObserver {

	protected Context activity;
	
	public ContextRestClientObserver(Context activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(activity);
	}
}

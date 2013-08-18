package com.tdil.thalamus.android;

import com.tdil.thalamus.android.rest.client.RESTClientTask;

public interface IRestClientObserver {

	public void sucess(RESTClientTask restClientTask);
	public void error(RESTClientTask restClientTask);
}

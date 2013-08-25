package com.tdil.thalamus.android.rest.client;


public interface IRestClientObserver {

	public void sucess(RESTClientTask restClientTask);
	public void error(RESTClientTask restClientTask);
}

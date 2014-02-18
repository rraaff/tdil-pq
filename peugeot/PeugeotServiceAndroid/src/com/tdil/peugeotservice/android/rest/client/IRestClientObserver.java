package com.tdil.peugeotservice.android.rest.client;


public interface IRestClientObserver {

	public void sucess(IRestClientTask restClientTask);
	public void error(IRestClientTask restClientTask);
}

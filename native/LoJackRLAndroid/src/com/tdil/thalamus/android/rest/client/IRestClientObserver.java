package com.tdil.thalamus.android.rest.client;


public interface IRestClientObserver {

	public void sucess(IRestClientTask restClientTask);
	public void error(IRestClientTask restClientTask);
	
	public IRestClientObserver dummy = new IRestClientObserver() {
		
		@Override
		public void sucess(IRestClientTask restClientTask) {
		}
		
		@Override
		public void error(IRestClientTask restClientTask) {
		}
	};
}

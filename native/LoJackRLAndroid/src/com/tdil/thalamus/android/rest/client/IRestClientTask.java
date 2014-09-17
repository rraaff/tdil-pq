package com.tdil.thalamus.android.rest.client;

import com.tdil.thalamus.android.rest.model.prevent.SpeedLimitCollection;

public interface IRestClientTask {

	public abstract String getResult();

	public abstract int getStatusCode();

	public abstract void setStatusCode(int status);

}
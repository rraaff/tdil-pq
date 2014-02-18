package com.tdil.peugeotservice.android.rest.client;

public interface IRestClientTask {

	public abstract String getResult();

	public abstract int getStatusCode();

	public abstract void setStatusCode(int status);

}
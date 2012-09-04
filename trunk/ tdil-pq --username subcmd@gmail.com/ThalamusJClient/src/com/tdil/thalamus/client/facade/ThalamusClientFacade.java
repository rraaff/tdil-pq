package com.tdil.thalamus.client.facade;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.core.ThalamusServices;

public class ThalamusClientFacade {

	public static JSON login(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException {
		return ThalamusClient.login(username, password);
	}
	
	public static JSON logout(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException {
		return ThalamusClient.executeGet(username, password, ThalamusServices.LOGOUT);
	}

	public static JSON getCountries(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException {
		return ThalamusClient.executeGet(username, password, ThalamusServices.GET_COUNTRIES);
	}
	
	
	public static JSON register(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException {
		return ThalamusClient.executePost(jsonObject, ThalamusServices.REGISTER_PERSON);
	}
	
}

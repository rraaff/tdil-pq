package com.tdil.thalamus.client.facade;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.core.ThalamusServices;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class ThalamusClientFacade {

	public static JSON login(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.login(username, password);
	}
	
	public static JSON getProfile(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(username, password, ThalamusServices.GET_PERSON_PROFILE);
	}
	
	public static JSON logout(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(username, password, ThalamusServices.LOGOUT);
	}

	public static JSON getCountries() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_COUNTRIES);
	}
	
	public static JSON getStates(int country) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_STATES_START + country + ThalamusServices.GET_STATES_END);
	}
	
	public static JSON getBrands() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_BRANDS);
	}
	
	public static JSON register(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePost(jsonObject, ThalamusServices.REGISTER_PERSON);
	}
	
	public static JSON registerPersonAndConsumer(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePost(jsonObject, ThalamusServices.REGISTER_PERSON_CONSUMER);
	}
	
	public static JSON loginToActivity(String username, String password, int activity) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("true");
		return ThalamusClient.executePost(jsonArray, username, password, ThalamusServices.LOGIN_ACTIVITY_START + activity + ThalamusServices.LOGIN_ACTIVITY_END);
	}
	
}

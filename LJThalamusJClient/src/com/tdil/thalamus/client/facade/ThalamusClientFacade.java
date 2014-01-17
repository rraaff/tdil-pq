package com.tdil.thalamus.client.facade;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.NameValuePair;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.core.ThalamusServices;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;
import com.tdil.thalamus.client.core.method.PutMethodCreator;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;

public class ThalamusClientFacade {

	public static boolean isAlive() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			JSON isAlive = ThalamusClient.executeGet(null, ThalamusServices.GET_IS_ALIVE, true, (NameValuePair[])null).getResult();
			return ((JSONObject)isAlive).getString("isAlive").equals("1");
		} catch (Throwable e) {
			// LOG
			return false;
		}
	}
	
	public static JSON login(String username, String password) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.login(username, password);
	}
	
	public static LoginResult login(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		TokenHolder tokenHolder = new TokenHolder();
		JSON result = ThalamusClient.execute(jsonObject, tokenHolder, ThalamusServices.LOGIN_V2, PostMethodCreator.INSTANCE).getResult();
		LoginResult loginResult = new LoginResult();
		loginResult.setTokenHolder(tokenHolder);
		loginResult.setResult(result);
		return loginResult;
	}
	
	public static JSON logout() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(null, ThalamusServices.LOGOUT, true, (NameValuePair[])null).getResult();
	}

	public static JSON requestResetPassword(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePost(jsonObject, ThalamusServices.REQUEST_RESET_PASSWORD).getResult();
	}
	
	public static ThalamusResponse changePassword(TokenHolder tokenHolder, JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.execute(jsonObject, tokenHolder, ThalamusServices.CHANGE_PASSWORD, PutMethodCreator.INSTANCE);
	}
	
	public static JSON resetPassword(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePut(jsonObject, ThalamusServices.RESET_PASSWORD).getResult();
	}
	
	public static ThalamusResponse validatePassword(TokenHolder tokenHolder, JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.execute(jsonObject, tokenHolder, ThalamusServices.VALIDATE_PASSWORD, PutMethodCreator.INSTANCE);
	}
	
	public static JSON getPersonFields() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_PERSON_FIELDS).getResult();
	}
	
	public static JSON getPerson(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(tokenHolder, ThalamusServices.GET_PERSON, true, (NameValuePair[])null).getResult();
	}
	
	public static JSON getCountries() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_COUNTRIES).getResult();
	}
	
	public static JSON getStates(int country) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_STATES_START + country + ThalamusServices.GET_STATES_END).getResult();
	}
	
	public static JSON getBrands() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_BRANDS).getResult();
	}
	
	public static JSON getBrandsFamilies() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_BRANDSFAMILIES).getResult();
	}
	
	public static JSON getDocumentTypes() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_DOCUMENTTYPES).getResult();
	}
	
	public static JSON getChannels() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(ThalamusServices.GET_CHANNELS).getResult();
	}
	
	public static JSON registerPerson(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePost(jsonObject, ThalamusServices.REGISTER_PERSON).getResult();
	}
	
	public static JSON signInFacebook() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePost(null, ThalamusServices.SIGN_IN_FACEBOOK).getResult();
	}
	
	public static LoginResult signInFacebook(String code, TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		ThalamusResponse resp = ThalamusClient.executeGet(tokenHolder, ThalamusServices.SIGN_IN_FACEBOOK, false, new NameValuePair("code", code));
		LoginResult loginResult = new LoginResult();
		loginResult.setTokenHolder(tokenHolder);
		loginResult.setResponse(resp);
		return loginResult;
	}
	
	public static JSON addFacebook(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.execute(null, tokenHolder, ThalamusServices.CONNECT_FACEBOOK, PostMethodCreator.INSTANCE).getResult();
	}
	
	public static ThalamusResponse addFacebook(TokenHolder tokenHolder, String code) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(tokenHolder, ThalamusServices.CONNECT_FACEBOOK, true, new NameValuePair("code", code));
	}
	
	public static JSON removeFacebook(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeDelete(tokenHolder, ThalamusServices.CONNECT_FACEBOOK, true, null).getResult();
	}

	public static JSON addTwitter(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.execute(null, tokenHolder, ThalamusServices.CONNECT_TWITTER, PostMethodCreator.INSTANCE).getResult();
	}
	
	public static ThalamusResponse addTwitter(TokenHolder tokenHolder, String code, String oauth_verifier) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeGet(tokenHolder, ThalamusServices.CONNECT_TWITTER, true, new NameValuePair("oauth_token", code),new NameValuePair("oauth_verifier", oauth_verifier));
	}
	
	public static JSON removeTwitter(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executeDelete(tokenHolder, ThalamusServices.CONNECT_TWITTER, true, null).getResult();
	}
	
	public static ThalamusResponse signInTwitter() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		TokenHolder tokenHolder = new TokenHolder();
		ThalamusResponse response = ThalamusClient.execute(null, tokenHolder,  ThalamusServices.SIGN_IN_TWITTER, PostMethodCreator.INSTANCE);
		response.setTokenHolder(tokenHolder);
		return response;
	}
	
	public static LoginResult signInTwitter(String code, String oauth_verifier, TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		ThalamusResponse resp = ThalamusClient.executeGet(tokenHolder, ThalamusServices.SIGN_IN_TWITTER, false, new NameValuePair("oauth_token", code), new NameValuePair("oauth_verifier", oauth_verifier));
		LoginResult loginResult = new LoginResult();
		loginResult.setTokenHolder(tokenHolder);
		loginResult.setResponse(resp);
		return loginResult;
	}
	
	public static JSON getSocial(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		ThalamusResponse resp = ThalamusClient.executeGet(tokenHolder, ThalamusServices.GET_SOCIAL_CONNECT, true, (NameValuePair[])null);
		return resp.getResult();
	}
	
	public static ThalamusResponse updatePerson(TokenHolder tokenHolder, JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.execute(jsonObject, tokenHolder, ThalamusServices.REGISTER_PERSON, PutMethodCreator.INSTANCE);
	}
	
	public static ThalamusResponse registerPersonAndConsumer(JSONObject jsonObject) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePost(jsonObject, ThalamusServices.REGISTER_PERSON_CONSUMER);
	}
	
	public static JSON loginToActivity(TokenHolder tokenHolder, String activity) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("true");
		return ThalamusClient.execute(jsonArray, tokenHolder, ThalamusServices.LOGIN_ACTIVITY_START + activity + ThalamusServices.LOGIN_ACTIVITY_END, PostMethodCreator.INSTANCE).getResult();
	}
	
}

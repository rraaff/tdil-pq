package com.tdil.lojack.prevent;

import com.tdil.lojack.prevent.model.UserLogin;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class Main {

	/**
	 * @param args
	 * @throws UnauthorizedException 
	 * @throws CommunicationException 
	 * @throws InvalidResponseException 
	 * @throws HttpStatusException 
	 */
	public static void main(String[] args) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		UserLogin userLogin = new UserLogin();
		userLogin.setUser("Prevent2");
		userLogin.setPassword("1234");
		PreventConnector.login(userLogin);
	}

}

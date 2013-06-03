package com.tdil.lojack.temp;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.lojack.prevent.model.UserLogin;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class TestAndroid {

	public static void login(HttpSession session) {
		try {
			com.tdil.lojack.struts.forms.LoginForm login = new com.tdil.lojack.struts.forms.LoginForm();
			login.setUsername("user1@mail.com");
			login.setPassword("user1@mail.com");
			WebsiteUser user = (WebsiteUser) login.executeLogin();
			user.createUserJobCollection(session);
			session.setAttribute("user", user);
			UserLogin userLogin = new UserLogin();
			userLogin.setUser("Prevent2");
			userLogin.setPassword("1234");
			XMLResponse resp = PreventConnector.login(userLogin);
			LoginResponse loginResponse = (LoginResponse)resp.getResult();
			user.setPreventPassword(login.getPassword());
			user.setPreventAccessToken(loginResponse.getUserToken());
			user.setPreventLoginResponse(loginResponse);
			user.setPreventLogged(true);
			session.setAttribute("user", user);
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

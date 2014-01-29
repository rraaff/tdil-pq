package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.lojack.prevent.model.UserLogin;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class PreventLoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String username;
	private String password;
	private String preventAccessToken;
	private LoginResponse preventLoginResponse;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(PreventLoginForm.class);

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		username = null;
		password = null;
	}

	public boolean executeLogin() throws SQLException, ValidationException {
		try {
			UserLogin userLogin = new UserLogin();
			userLogin.setUser(this.getUsername());
			userLogin.setPassword(this.getPassword());
			XMLResponse resp = PreventConnector.login(userLogin);
			LoginResponse loginResponse = (LoginResponse)resp.getResult();
			if (loginResponse.getStatus().equals("OK")) {
				setPreventAccessToken(loginResponse.getUserToken());
				setPreventLoginResponse(loginResponse);
				return true;
			} else {
				return false;
			}
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreventAccessToken() {
		return preventAccessToken;
	}

	public void setPreventAccessToken(String preventAccessToken) {
		this.preventAccessToken = preventAccessToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LoginResponse getPreventLoginResponse() {
		return preventLoginResponse;
	}

	public void setPreventLoginResponse(LoginResponse preventLoginResponse) {
		this.preventLoginResponse = preventLoginResponse;
	}

}

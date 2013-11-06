package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class ThalamusPreventLoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String jsessionid;
	private String awselb;
	private int timezone;
	private String preventAccessToken;
	private LoginResponse preventLoginResponse;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ThalamusPreventLoginForm.class);

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}

	public boolean executeLogin() throws SQLException, ValidationException {
		try {
			XMLResponse resp = PreventConnector.login(this.getJsessionid(), this.getAwselb(), this.getTimezone());
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

	public String getPreventAccessToken() {
		return preventAccessToken;
	}

	public void setPreventAccessToken(String preventAccessToken) {
		this.preventAccessToken = preventAccessToken;
	}

	public LoginResponse getPreventLoginResponse() {
		return preventLoginResponse;
	}

	public void setPreventLoginResponse(LoginResponse preventLoginResponse) {
		this.preventLoginResponse = preventLoginResponse;
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public String getAwselb() {
		return awselb;
	}

	public void setAwselb(String awselb) {
		this.awselb = awselb;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

}

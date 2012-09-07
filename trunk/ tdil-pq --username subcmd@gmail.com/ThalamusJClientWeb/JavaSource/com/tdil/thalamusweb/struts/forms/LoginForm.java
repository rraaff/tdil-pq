package com.tdil.thalamusweb.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ProfileResponse;
import com.tdil.thalamus.client.facade.RegistrationParameters;
import com.tdil.thalamus.client.facade.StandardResponse;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.utils.ThalamusUtils;
import com.tdil.thalamusweb.utils.WebsiteUser;

public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		username = null;
		password = null;
	}
	public Object executeLogin() throws SQLException, ValidationException {
		if (StringUtils.isEmpty(this.getUsername())) {
			throw new ValidationException(new ValidationError("LoginForm.GENERAL_ERROR"));
		}
		if (StringUtils.isEmpty(this.getPassword())) {
			throw new ValidationException(new ValidationError("LoginForm.GENERAL_ERROR"));
		}
		try {
			JSONObject getProfile = (JSONObject)ThalamusClientFacade.getProfile(this.getUsername(), this.getPassword());
			JSONObject data = getProfile.getJSONObject(StandardResponse.DATA);
			String firstName = ((JSONObject)data).getJSONObject(ProfileResponse.PROFILE).getString(RegistrationParameters.firstName);
			String lastName = ((JSONObject)data).getJSONObject(ProfileResponse.PROFILE).getString(RegistrationParameters.lastName);
			
			WebsiteUser user = new WebsiteUser(firstName + " " + lastName, this.getUsername(), this.getPassword());
			user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
			return user;
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError("LoginForm.HttpStatusException"));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError("LoginForm.InvalidResponseException"));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError("LoginForm.CommunicationException"));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError("LoginForm.UnauthorizedException"));
		}
	}
	
}

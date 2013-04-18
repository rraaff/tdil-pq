package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.LoginBean;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.PersonResult;
import com.tdil.thalamus.client.utils.ThalamusUtils;

public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String timezoneOffset;
	private String timezoneName;
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
			return login(this.getUsername(), this.getPassword(), this.getTimezoneOffset(), this.getTimezoneName());
		} catch (HttpStatusException e) {
			if (e.getStatus() == HttpStatus.SC_UNAUTHORIZED) {
				throw new ValidationException(new ValidationError("LoginForm.HttpStatusException.401"));
			} else {
				throw new ValidationException(new ValidationError("LoginForm.HttpStatusException"));
			}
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError("LoginForm.InvalidResponseException"));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError("LoginForm.CommunicationException"));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError("LoginForm.UnauthorizedException"));
		}
	}
	
	public static WebsiteUser login(String username, String pasword, String timezoneOffset, String timezoneName) throws HttpStatusException, InvalidResponseException,
			CommunicationException, UnauthorizedException {
		LoginBean loginBean = new LoginBean(username, pasword);
		LoginResult result = ThalamusClientBeanFacade.login(loginBean);
		PersonResult getProfile = ThalamusClientBeanFacade.getPerson(result.getTokenHolder());
		
		
		String firstName = getProfile.getFirstName();
		String lastName = getProfile.getLastName();
		WebsiteUser user = new WebsiteUser(firstName + " " + lastName, result.getTokenHolder(), timezoneOffset, timezoneName);
		user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
		return user;
	}
	public String getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(String timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public String getTimezoneName() {
		return timezoneName;
	}
	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}
	
}

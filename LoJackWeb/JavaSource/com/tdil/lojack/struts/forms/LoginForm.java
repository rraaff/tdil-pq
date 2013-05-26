package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.utils.LoJackConfig;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ProfileResponse;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean;
import com.tdil.thalamus.client.facade.json.beans.LoginBean;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.PersonResult;
import com.tdil.thalamus.client.utils.ThalamusUtils;

public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String timezoneOffset;
	private String timezoneName;
	private int documentType;
	private String username;
	private String password;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LoginForm.class);

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
	
	public static Collection<DocumentTypeBean> getDocumentTypes() {
		try {
			return ThalamusClientBeanFacade.getDocumentTypes();
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<DocumentTypeBean>();
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
			if (LoJackConfig.getFRONT_LOGIN_DELAY() != 0) {
				try {
					Thread.sleep(LoJackConfig.getFRONT_LOGIN_DELAY());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return login(this.getDocumentType() + ":" + this.getUsername(), this.getPassword(), this.getTimezoneOffset(), this.getTimezoneName());
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

	public static WebsiteUser login(String username, String pasword, String timezoneOffset, String timezoneName)
			throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		LoginBean loginBean = new LoginBean(username, pasword);
		LoginResult result = ThalamusClientBeanFacade.login(loginBean);
		return getUserLogged(/* timezoneOffset, timezoneName, */result);
	}

	public static WebsiteUser getUserLogged(LoginResult result/*
															 * , String
															 * timezoneOffset,
															 * String
															 * timezoneName
															 */) throws HttpStatusException, InvalidResponseException,
			CommunicationException, UnauthorizedException {
		PersonResult getProfile = ThalamusClientBeanFacade.getPerson(result.getTokenHolder());

		String firstName = getProfile.getFirstName();
		String lastName = getProfile.getLastName();
		WebsiteUser user = new WebsiteUser(firstName + " " + lastName, result.getTokenHolder(), "", ""); // TODO
		setAccess(user, getProfile);

		user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
		user.setModelUser(WebsiteUserUtils.getWebSiteUserUpdatingData(user.getLojackUserId(), user.getHomeUserId(), user.getPreventUserId(), user.getPetUserId()));
		return user;
	}

	private static void setAccess(WebsiteUser user, PersonResult getProfile) {
		JSONObject profile = getProfile.getProfile().getJSONObject("person").getJSONObject(ProfileResponse.PROFILE);
		if (profile.containsKey("document") && profile.get("document") != JSONNull.getInstance()) {
			JSONObject document = profile.getJSONObject("document");
			user.setLojackUserId(document.getInt("type") + ":" + document.getString("number"));
		}
		if (profile.containsKey("homeIsClient") && profile.get("homeIsClient") != JSONNull.getInstance()) {
			user.setHomeUser(profile.getBoolean("homeIsClient"));
			user.setHomeUserId(profile.getString("homeUser"));
		}
		if (profile.containsKey("preventIsClient") && profile.get("preventIsClient") != JSONNull.getInstance()) {
			user.setPreventUser(profile.getBoolean("preventIsClient"));
			user.setPreventUserId(profile.getString("preventUser"));
		}
		if (profile.containsKey("petIsClient") && profile.get("petIsClient") != JSONNull.getInstance()) {
			user.setPetUser(profile.getBoolean("petIsClient"));
			user.setPreventUserId(profile.getString("petUser"));
		}
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

	public int getDocumentType() {
		return documentType;
	}

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

}

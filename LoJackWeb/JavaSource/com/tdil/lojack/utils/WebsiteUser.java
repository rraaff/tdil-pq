package com.tdil.lojack.utils;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.UpdateMiddlewareJobsThread;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.lojack.struts.forms.PreventLoginForm;
import com.tdil.lojack.struts.forms.ThalamusPreventLoginForm;
import com.tdil.lojack.web.jobs.UserJobCollection;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;
import com.tdil.users.User;

public class WebsiteUser extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = -6066615316635634331L;

	private TokenHolder token;
	private String name;
	private String lojackUserId;
	private int timezoneOffset;
	private String timezoneName;

	private boolean isHomeUser;
	private String homeUserId;
	private boolean isPreventUser;
	private String preventUserId;
	private boolean isPetUser;
	private String petUserId;
	private boolean vluIsClient;
	private int vluMessages;
	
	private boolean isClientClubLoJack = true;
	private int clubLoJackLevel;
	
	private boolean preventLogged;
	private String preventPassword;
	private String preventAccessToken;
	private LoginResponse preventLoginResponse;

	private UserJobCollection userJobCollection;

	private com.tdil.lojack.model.WebsiteUser modelUser;

	private Set<String> appliedActivities = new HashSet<String>();
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(WebsiteUser.class);

	public WebsiteUser(String name, TokenHolder tokenHolder, int timezoneOffset, String timezoneName) {
		super();
		this.name = name;
		this.token = tokenHolder;
		this.timezoneOffset = timezoneOffset;
		this.timezoneName = timezoneName;
	}

	@Override
	public Integer getId() {
		return this.getModelUser().getId();
	}

	public String getJSESSIONID() {
		return String.valueOf(this.getToken().getCookie("JSESSIONID").getValue());
	}

	public String getAWSELB() {
		return String.valueOf(this.getToken().getCookie("AWSELB").getValue());
	}
	
	public String getHomeUserId() {
		return this.homeUserId;
	}

	public boolean isLogged() {
		return this.getToken() != null && this.getToken().hasToken();
	}

	public String getName() {
		return name;
	}

	public Set<String> getAppliedActivities() {
		return appliedActivities;
	}

	public void setAppliedActivities(Set<String> appliedActivities) {
		this.appliedActivities = appliedActivities;
	}

	public boolean appliesToActivity(String code) {
		return appliedActivities.contains(code);
	}

	public TokenHolder getToken() {
		return token;
	}

	@Override
	public Set<String> getRoles() {
		Set<String> roles = new HashSet<String>();
		roles.add(com.tdil.lojack.roles.WebsiteUser.INSTANCE.getName());
		return roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(int timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	public String getLojackUserId() {
		return lojackUserId;
	}

	public void setLojackUserId(String lojackUserId) {
		this.lojackUserId = lojackUserId;
	}

	public com.tdil.lojack.model.WebsiteUser getModelUser() {
		if (modelUser == null) {
			modelUser = new com.tdil.lojack.model.WebsiteUser();
			modelUser.setLojackuserid(lojackUserId);
		}
		return modelUser;
	}

	public void setModelUser(com.tdil.lojack.model.WebsiteUser modelUser) {
		this.modelUser = modelUser;
	}

	public boolean isHomeUser() {
		return isHomeUser;
	}

	public void setHomeUser(boolean isHomeUser) {
		this.isHomeUser = isHomeUser;
	}

	public boolean isPreventUser() {
		return isPreventUser;
	}

	public void setPreventUser(boolean isPreventUser) {
		this.isPreventUser = isPreventUser;
	}

	public boolean isPetUser() {
		return isPetUser;
	}

	public void setPetUser(boolean isPetUser) {
		this.isPetUser = isPetUser;
	}

	public void createUserJobCollection(HttpSession session) {
		this.userJobCollection = new UserJobCollection(session, this);
		UpdateMiddlewareJobsThread.getUpdateMiddlewareJobsThread().addUserJobCollection(this.userJobCollection);
	}

	public List<AsyncJob> getAndRemoveFinishedJobs() {
		return this.userJobCollection.getAndRemoveFinishedJobs();
	}

	public void addAsyncJob(AsyncJob asyncJob) {
		this.userJobCollection.addJob(asyncJob);
	}

	public AsyncJob getPendingJob(int idEntidad, int idLuz) {
		return this.userJobCollection.getPendingJob(idEntidad, idLuz);
	}

	public boolean hasJobInProgress(Alarm alarm) {
		return this.userJobCollection.getPendingJob(alarm.getIdEntidad(), 0) != null;
	}

	public boolean hasJobInProgress(Light light) {
		return this.userJobCollection.getPendingJob(light.getIdEntidad(), light.getIdLuz()) != null;
	}

	public String getPreventUserId() {
		return preventUserId;
	}

	public void setPreventUserId(String preventUserId) {
		this.preventUserId = preventUserId;
	}

	public String getPetUserId() {
		return petUserId;
	}

	public void setPetUserId(String petUserId) {
		this.petUserId = petUserId;
	}

	public void setHomeUserId(String homeUserId) {
		this.homeUserId = homeUserId;
	}

	public boolean isPreventLogged() {
		return preventLogged;
	}

	public void setPreventLogged(boolean preventLogged) {
		this.preventLogged = preventLogged;
	}

	public String getPreventPassword() {
		return preventPassword;
	}

	public void setPreventPassword(String preventPassword) {
		this.preventPassword = preventPassword;
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
	
	public void reloginPrevent() {
		ThalamusPreventLoginForm login = new ThalamusPreventLoginForm();
		login.setJsessionid(this.getJSESSIONID());
		login.setAwselb(this.getAWSELB());
		login.setTimezone(this.getTimezoneOffset());
		try {
			boolean logged = login.executeLogin();
			if (logged) {
				this.setPreventAccessToken(login.getPreventAccessToken());
				this.setPreventLoginResponse(login.getPreventLoginResponse());
				this.setPreventLogged(true);
			} else {
				this.setPreventLogged(false);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			this.setPreventLogged(false);
		} catch (ValidationException e) {
			LOG.error(e.getMessage(), e);
			this.setPreventLogged(false);
		}
		
	}
	
	public void reloginPrevent1() {
		PreventLoginForm login = new PreventLoginForm();
		login.setUsername(this.getPreventUserId());
		login.setPassword(this.getPreventPassword());
		try {
			boolean logged = login.executeLogin();
			if (logged) {
				this.setPreventAccessToken(login.getPreventAccessToken());
				this.setPreventLoginResponse(login.getPreventLoginResponse());
				this.setPreventLogged(true);
			} else {
				this.setPreventLogged(false);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			this.setPreventLogged(false);
		} catch (ValidationException e) {
			LOG.error(e.getMessage(), e);
			this.setPreventLogged(false);
		}
		
	}

	public boolean isClientClubLoJack() {
		return isClientClubLoJack;
	}

	public void setClientClubLoJack(boolean isClientClubLoJack) {
		this.isClientClubLoJack = isClientClubLoJack;
	}

	public int getClubLoJackLevel() {
		return clubLoJackLevel;
	}

	public void setClubLoJackLevel(int clubLojackLevel) {
		this.clubLoJackLevel = clubLojackLevel;
	}

	public boolean vluIsClient() {
		return vluIsClient;
	}

	public void setVLUClient(boolean vluIsClient) {
		this.vluIsClient = vluIsClient;
	}

	public int getVluMessages() {
		return vluMessages;
	}

	public void setVluMessages(int vluMessages) {
		this.vluMessages = vluMessages;
	}
	
	public String getDni() {
		if (modelUser.getLojackuserid().contains(":")) {
			return modelUser.getLojackuserid().split(":")[1];
		} else {
			return modelUser.getLojackuserid();
		}
	}

}

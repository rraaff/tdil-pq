package com.tdil.ljpeugeot.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.prevent.URLParams;
import com.tdil.ljpeugeot.prevent.model.Vehicles;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.LJPeugeotConfig;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.ljpeugeot.utils.WebsiteUserUtils;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
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
	private int documentType = 1;
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
		documentType = 1;
		username = null;
		password = null;
	}

	public Object executeLogin() throws SQLException, ValidationException {
		if (StringUtils.isEmpty(this.getUsername())) {
			throw new ValidationException(new ValidationError("LoginForm.emptyusername"));
		}
		if (StringUtils.isEmpty(this.getPassword())) {
			throw new ValidationException(new ValidationError("LoginForm.emptypassword"));
		}
		try {
			if (LJPeugeotConfig.getFRONT_LOGIN_DELAY() != 0) {
				try {
					Thread.sleep(LJPeugeotConfig.getFRONT_LOGIN_DELAY());
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
		WebsiteUser user = getUserLogged(/* timezoneOffset, timezoneName, */result);
		if(user.isPreventUser()) {
			List<Vehicle> vehicles = PeugeotService.getVehicles(user.getModelUser().getId());
			Set<String> domains = new HashSet<String>();
			for (Vehicle v : vehicles) {
				domains.add(v.getDomain());
			}
			List<com.tdil.ljpeugeot.prevent.model.Vehicle> preventVehicles = new ArrayList<com.tdil.ljpeugeot.prevent.model.Vehicle>();
			if (!user.isPreventLogged()) {
				user.reloginPrevent();
			}
			if (user.isPreventLogged()) {
				URLParams getVehicles = new URLParams(user.getPreventLoginResponse()).index("0");
				Vehicles preventvehicles = (Vehicles)PreventConnector.getVehicles(getVehicles).getResult();
				preventVehicles = preventvehicles.getVehiclesCollection();
			} 
			for (com.tdil.ljpeugeot.prevent.model.Vehicle v : preventVehicles) {
				if (v.getStatus().equalsIgnoreCase("true")) {
					if (!domains.contains(v.getDescription())) {
						addVehicle(user.getModelUser(),v);
					}
					domains.add(v.getDescription());
				}
			}
		}
		return user;
	}
	
	private static final class CreateVehicle implements TransactionalAction {
		private com.tdil.ljpeugeot.model.WebsiteUser modelUser;
		private com.tdil.ljpeugeot.prevent.model.Vehicle vehicle;
		
		public CreateVehicle(com.tdil.ljpeugeot.model.WebsiteUser modelUser, com.tdil.ljpeugeot.prevent.model.Vehicle v) {
			super();
			this.modelUser = modelUser;
			this.vehicle = v;
		}

		public void executeInTransaction() throws SQLException {
			Vehicle v = new Vehicle();
			v.setDomain(vehicle.getDescription());
			v.setIdWebsiteuser(modelUser.getId());
			v.setAdvice1sent(0);
			v.setAdvice2sent(0);
			v.setAdvice3sent(0);
			v.setNeedsadvice(0);
			v.setNeedsadvice1(0);
			v.setNeedsadvice2(0);
			v.setNeedsadvice3(0);
			v.setWarrantyexpired(0);
			v.setDeleted(0);
			DAOManager.getVehicleDAO().insertVehicle(v);
		}
	}

	private static void addVehicle(com.tdil.ljpeugeot.model.WebsiteUser modelUser, com.tdil.ljpeugeot.prevent.model.Vehicle v) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new CreateVehicle(modelUser, v));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} catch (ValidationException e) {
			LOG.error(e.getMessage(), e);
		} 
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
		WebsiteUser user = new WebsiteUser(firstName + " " + lastName, result.getTokenHolder(), -3, ""); // TODO
		setAccess(user, getProfile);

		user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
		user.setModelUser(WebsiteUserUtils.getWebSiteUserUpdatingData(user.getLojackUserId(), user.getHomeUserId(), user.getPreventUserId(), user.getPetUserId()));
		return user;
	}

	private static void setAccess(WebsiteUser user, PersonResult getProfile) {
		JSONObject profile = getProfile.getProfile().getJSONObject("person").getJSONObject(ProfileResponse.PROFILE);
		String documentKey = "document";
		JSONObject document = null;
		if (jsonHasValueForKey(profile, documentKey)) {
			document = profile.getJSONObject(documentKey);
			user.setLojackUserId(document.getInt("type") + ":" + document.getString("number"));
		}
		String homeIsClientKey = "homeIsClient";
		if (jsonHasValueForKey(profile, homeIsClientKey)) {
			user.setHomeUser(profile.getBoolean(homeIsClientKey));
			if (jsonHasValueForKey(profile, "homeUser")) {
				user.setHomeUserId(profile.getString("homeUser"));
			}
		}
		String preventIsClientKey = "preventIsClient";
		if (jsonHasValueForKey(profile, preventIsClientKey)) {
			user.setPreventUser(profile.getBoolean(preventIsClientKey));
			if (jsonHasValueForKey(profile, "preventUser")) {
				user.setPreventUserId(profile.getString("preventUser"));
			}
		}
		String petIsClientKey = "petIsClient";
		if (jsonHasValueForKey(profile, petIsClientKey)) {
			user.setPetUser(profile.getBoolean(petIsClientKey));
			if (jsonHasValueForKey(profile, "petUser")) {
				user.setPetUserId(profile.getString("petUser"));
			}
		}
		
		String clubLoJackIsClient = "ClubLjsClient";
		if (jsonHasValueForKey(profile, clubLoJackIsClient)) {
			user.setClientClubLoJack(profile.getBoolean(clubLoJackIsClient));
		}
		
		String clubLoJackLevel = "clubLoJackLevel";
		if (jsonHasValueForKey(profile, clubLoJackLevel)) {
			user.setClubLoJackLevel(profile.getInt(clubLoJackLevel));
		}
		
		String peugeotIsClientKey = "PeugeotIsClient";
		if (jsonHasValueForKey(profile, peugeotIsClientKey)) {
			user.setPeugeotIsClient(profile.getBoolean(peugeotIsClientKey));
			if (jsonHasValueForKey(profile, "peugeotUser")) {
				user.setPeugeotUser(profile.getString("peugeotUser"));
			}
		}
	}

	private static boolean jsonHasValueForKey(JSONObject profile, String key) {
		return profile.containsKey(key) && profile.get(key) != JSONNull.getInstance();
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

package com.tdil.ljpeugeot.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.Relation;
import com.tdil.ljpeugeot.rest.model.BeanCollection;
import com.tdil.ljpeugeot.rest.model.ChangePasswordBean;
import com.tdil.ljpeugeot.rest.model.ContactDataBean;
import com.tdil.ljpeugeot.rest.model.LoginResponse;
import com.tdil.ljpeugeot.rest.model.PersonBean;
import com.tdil.ljpeugeot.rest.model.RelationBean;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.struts.forms.ChangePasswordForm;
import com.tdil.ljpeugeot.struts.forms.LoginForm;
import com.tdil.ljpeugeot.struts.forms.RegisterForm;
import com.tdil.ljpeugeot.struts.forms.RequestResetPasswordForm;
import com.tdil.ljpeugeot.utils.AddressType;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.AddressTypeBean;
import com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean;
import com.tdil.thalamus.client.facade.json.beans.StateBean;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordBean;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;
import com.tdil.utils.DateUtils;

@Path("/users")
public class UsersService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(UsersService.class);
	
	public UsersService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}
	
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("documentType") String documentType, @QueryParam("documentNumber") String documentNumber, @QueryParam("password") String password) {
		WebsiteUser user;
		try {
			user = LoginForm.login(documentType + ":" + documentNumber, password, "", "");
			getSession(true).setAttribute("user", user);
			
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setLogged(true);
			loginResponse.setName(user.getName());
			loginResponse.setLojackUserId(user.getLojackUserId());
			loginResponse.setHomeUser(user.isHomeUser());
			loginResponse.setHomeUserId(user.getHomeUserId());
			loginResponse.setPetUser(user.isPetUser());
			loginResponse.setPetUserId(user.getPetUserId());
			loginResponse.setPreventUser(user.isPreventUser());
			loginResponse.setPreventUserId(user.getPreventUserId());
			loginResponse.setVluClient(user.vluIsClient());
			String apkToken = ApkLoginCache.add(user);
			loginResponse.setApkToken(apkToken);
			return response(loginResponse);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(LoginResponse.failed());
		} 
	}
	
	@GET
	@Path("/requestResetPassword")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestResetPassword(@QueryParam("documentType") String documentType, @QueryParam("documentNumber") String documentNumber) {
		try {
			RequestResetPasswordForm form = new RequestResetPasswordForm();
			form.setDocumentType(Integer.valueOf(documentType));
			form.setUsername(documentNumber);
			if (form.resetPassword() == 201) {
				return okResponse();
			} else {
				return failResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		} 
	}
	
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout() {
		HttpSession session = getSession(false);
		if (session != null) {
			getSession().invalidate();
		}
		String apkToken = request.getHeader("apkToken");
		ApkLoginCache.remove(apkToken);
		return okResponse();
	}
	
	@GET
	@Path("/documentTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDocumentTypes() {
		try {
			return createResponse(201, new BeanCollection<DocumentTypeBean>(ThalamusClientBeanFacade.getDocumentTypes()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/states")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listStates() {
		try {
			return createResponse(201, new BeanCollection<StateBean>(ThalamusClientBeanFacade.getStates(1)));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/addressTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAddressTypes() {
		try {
			List<AddressTypeBean> result = new ArrayList<AddressTypeBean>();
			for (String adt : AddressType.types) {
				result.add(new AddressTypeBean(adt, ApplicationResources.getMessage(adt)));
			}
			return createResponse(201, new BeanCollection<AddressTypeBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/relations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRelations() {
		try {
			List<RelationBean> result = new ArrayList<RelationBean>();
			for (Relation adt : Relation.values()) {
				result.add(new RelationBean(adt.name(), ApplicationResources.getMessage("relation_" + adt.name())));
			}
			return createResponse(201, new BeanCollection<RelationBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		validateLogged();
		try {
			JSON response = ThalamusClientFacade.getPerson(getUser().getToken());
			return createResponse(201, getPersonBean(response));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@GET
	@Path("/contactData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response contactData() {
		validateLogged();
		try {
			ContactData contactData = PeugeotService.getContactData(getUser().getId());
			if (contactData == null) {
				return createResponse(201, new ContactDataBean());
			} else {
				return createResponse(201, new ContactDataBean(contactData));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@POST
	@Path("/contactData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveContactData(String body) {
		validateLogged();
		try {
			ContactDataBean personBean = extractObjectFromJSON(body, ContactDataBean.class);
			ContactData contactData = ContactDataBean.asContactData(personBean);
			ContactData original = PeugeotService.getContactData(getUser().getId());
			contactData.setIdWebsiteuser(getUser().getId());
			contactData.setId(original.getId());
			contactData.setDeleted(0);
			PeugeotService.udpateContactData(contactData);
			if (PeugeotService.udpateContactData(contactData)) {
				return okResponse();
			} else {
				return failResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	
	private PersonBean getPersonBean(JSON gral) {
		PersonBean result = new PersonBean();
		JSONObject person = ((JSONObject) gral).getJSONObject("person");
		JSONObject profile = person.getJSONObject("profile");
		if (profile.containsKey("document") && profile.get("document") != JSONNull.getInstance()) {
			JSONObject document = profile.getJSONObject("document");
			result.setDocument(document.getString("number"));
		}

		if (profile.containsKey(PersonFieldNames.firstName) && profile.get(PersonFieldNames.firstName) != JSONNull.getInstance()) {
			result.setFirstName(profile.getString(PersonFieldNames.firstName));
		}
		if (profile.containsKey(PersonFieldNames.lastName) && profile.get(PersonFieldNames.lastName) != JSONNull.getInstance()) {
			result.setLastName(profile.getString(PersonFieldNames.lastName));
		}
		if (profile.containsKey(PersonFieldNames.email) && profile.get(PersonFieldNames.email) != JSONNull.getInstance()) {
			result.setEmail(profile.getString(PersonFieldNames.email));
		}
		if (profile.containsKey(PersonFieldNames.gender) && profile.get(PersonFieldNames.gender) != JSONNull.getInstance()) {
			result.setGender(profile.getString(PersonFieldNames.gender));
		}
		if (profile.containsKey(PersonFieldNames.birthDate) && profile.get(PersonFieldNames.birthDate) != JSONNull.getInstance()) {
			Date date = new Date(profile.getLong(PersonFieldNames.birthDate));
			result.setBirthDate(DateUtils.formatDate(date));
		}
		if (profile.containsKey(PersonFieldNames.phone)) {
			JSONObject phone = profile.getJSONObject(PersonFieldNames.phone);
			if (phone.containsKey(PersonFieldNames.phoneIntCode) && phone.get(PersonFieldNames.phoneIntCode) != JSONNull.getInstance()) {
				result.setPhoneIntCode(phone.getString(PersonFieldNames.phoneIntCode));
			}
			if (phone.containsKey(PersonFieldNames.phoneAreaCode) && phone.get(PersonFieldNames.phoneAreaCode) != JSONNull.getInstance()) {
				result.setPhoneAreaCode(phone.getString(PersonFieldNames.phoneAreaCode));
			}
			if (phone.containsKey(PersonFieldNames.phoneNumber) && phone.get(PersonFieldNames.phoneNumber) != JSONNull.getInstance()) {
				result.setPhoneNumber(phone.getString(PersonFieldNames.phoneNumber));
			}
			if (phone.containsKey(PersonFieldNames.phoneType) && phone.get(PersonFieldNames.phoneType) != JSONNull.getInstance()) {
				result.setPhoneType(phone.getString(PersonFieldNames.phoneType));
			}
		}
		if (profile.containsKey(PersonFieldNames.address)) {
			JSONObject address = profile.getJSONObject(PersonFieldNames.address);
			if (address.containsKey(PersonFieldNames.countryId) && address.get(PersonFieldNames.countryId) != JSONNull.getInstance()) {
				result.setCountryId(address.getInt(PersonFieldNames.countryId));
			}
			if (address.containsKey(PersonFieldNames.stateId) && address.get(PersonFieldNames.stateId) != JSONNull.getInstance()) {
				result.setStateId(address.getInt(PersonFieldNames.stateId));
			}
			if (address.containsKey(PersonFieldNames.street1) && address.get(PersonFieldNames.street1) != JSONNull.getInstance()) {
				result.setStreet1(address.getString(PersonFieldNames.street1));
			}
			if (address.containsKey(PersonFieldNames.street2) && address.get(PersonFieldNames.street2) != JSONNull.getInstance()) {
				result.setStreet2(address.getString(PersonFieldNames.street2));
			}
			if (address.containsKey(PersonFieldNames.postalCode) && address.get(PersonFieldNames.postalCode) != JSONNull.getInstance()) {
				result.setPostalCode(address.getString(PersonFieldNames.postalCode));
			}
			if (address.containsKey(PersonFieldNames.addressType) && address.get(PersonFieldNames.addressType) != JSONNull.getInstance()) {
				result.setAddressType(address.getString(PersonFieldNames.addressType));
			}
			if (address.containsKey(PersonFieldNames.city) && address.get(PersonFieldNames.city) != JSONNull.getInstance()) {
				result.setCity(address.getString(PersonFieldNames.city));
			}
		}
		if (person.containsKey("optIns")) {
			JSONArray optIns = person.getJSONArray("optIns");
			Iterator iter = optIns.iterator();
			while (iter.hasNext()) {
				JSONObject optIn = (JSONObject) iter.next();
				int brandFamilyId = optIn.getInt("brandFamilyId");
				int channel = optIn.getInt("channel");
				boolean accepted = optIn.getBoolean("accepted");
				result.setOptIn(accepted);
			}
		}
		return result;
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String body) {
		PersonBean personBean = extractObjectFromJSON(body, PersonBean.class);
		JSONObject general = RegisterForm.getPersonJSON(false, personBean);
		try {
			ThalamusResponse response = ThalamusClientFacade.registerPersonAndConsumer(general);
			if (response.isBadRequest()) {
				return failResponse(response);
			} else {
				return okResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String body) {
		validateLogged();
		PersonBean personBean = extractObjectFromJSON(body, PersonBean.class);
		JSONObject general = RegisterForm.getPersonJSON(true, personBean);
		try {
			ThalamusResponse response = ThalamusClientFacade.updatePerson(getUser().getToken(), general);
			if (response.isBadRequest()) {
				response.setTokenHolder(null);
				return failResponse(response);
			} else {
				return okResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@POST
	@Path("/password")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(String body) {
		validateLogged();
		ChangePasswordBean changePasswordBean = extractObjectFromJSON(body, ChangePasswordBean.class);
		JSONObject general = ChangePasswordForm.getChangePasswordJSON(changePasswordBean.getOldpassword(), changePasswordBean.getNewPassword(), changePasswordBean.getConfirmNewPassword());
		try {
			ThalamusResponse response = ThalamusClientFacade.changePassword(this.getUser().getToken(), general);
			if (response.isBadRequest()) {
				return failResponse(response);
			} else {
				return okResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		} 
	}

	@POST
	@Path("/password/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validatePassword(@QueryParam("password") String password) {
		validateLogged();
		try {
			ThalamusClientBeanFacade.validatePassword(getTokenHolder(), new ValidatePasswordBean(password));
			return okResponse();
		} catch (Exception e) {
			return failResponse();
		}
	}
	
	@POST
	@Path("/logApkError")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logApkError(String body) {
		LOG.error("APK Error " + body);
		return okResponse();
	}
}
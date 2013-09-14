package com.tdil.lojack.rest;

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

import net.sf.json.JSONObject;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.rest.model.BeanCollection;
import com.tdil.lojack.rest.model.ChangePasswordBean;
import com.tdil.lojack.rest.model.LoginResponse;
import com.tdil.lojack.rest.model.PersonBean;
import com.tdil.lojack.struts.forms.ChangePasswordForm;
import com.tdil.lojack.struts.forms.LoginForm;
import com.tdil.lojack.struts.forms.RegisterForm;
import com.tdil.lojack.struts.forms.RequestResetPasswordForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordBean;

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
			user.createUserJobCollection(getSession(true));
			getSession().setAttribute("user", user);
			
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
		getSession().invalidate();
		return okResponse();
	}
	
	@GET
	@Path("/documentTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		try {
			return createResponse(201, new BeanCollection<DocumentTypeBean>(ThalamusClientBeanFacade.getDocumentTypes()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String body) {
		validateLogged();
		PersonBean personBean = extractObjectFromJSON(body, PersonBean.class);
		JSONObject general = RegisterForm.getPersonJSON(false, personBean);
		try {
			ThalamusResponse response = ThalamusClientFacade.updatePerson(getUser().getToken(), general);
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

	public <T> T extractObjectFromJSON(String body, Class<T> aClass) {
		return (T)JSONObject.toBean((JSONObject)extractJSONObject(body), aClass);
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
}
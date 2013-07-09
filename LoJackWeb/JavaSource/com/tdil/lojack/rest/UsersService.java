package com.tdil.lojack.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.LightAgenda;
import com.tdil.lojack.rest.model.AlarmCollection;
import com.tdil.lojack.rest.model.ChangePasswordBean;
import com.tdil.lojack.struts.forms.LoginForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
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

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("documentType") String documentType, @QueryParam("documentNumber") String documentNumber, @QueryParam("password") String password) {
		WebsiteUser user;
		try {
			user = LoginForm.login(documentType + ":" + documentNumber, password, "", "");
			getSession().setAttribute("user", user);
			return okResponse();
		} catch (Exception e) {
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
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update() {
		validateLogged();
		/*JSONObject obj = (JSONObject)extractJSONObjectResponse(json);
		LightAgenda alarmAgenda = (LightAgenda)JSONObject.toBean(obj, LightAgenda.class);*/
		return null;
	}
	
	@POST
	@Path("/password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(ChangePasswordBean changePasswordBean) {
		validateLogged();
		Collection<Alarm> intermediate = LoJackServicesConnector.getAlarms(this.getUser());
		return Response.status(201).entity(new AlarmCollection(intermediate)).build();
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
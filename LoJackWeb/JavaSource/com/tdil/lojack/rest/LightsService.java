package com.tdil.lojack.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.rest.model.LightCollection;
import com.tdil.lojack.rest.model.LogCollection;
import com.tdil.lojack.struts.action.ActivateLightEmailNotificationAjaxAction.ActivateLightEmailNotification;
import com.tdil.lojack.struts.action.DeactivateLightEmailNotificationAjaxAction.DeactivateLightEmailNotification;
import com.tdil.lojack.struts.action.RenameLightAjaxAction.RenameLightAction;

@Path("/lights")
public class LightsService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LightsService.class);
	
	public LightsService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		validateLogged();
		Collection<Light> intermediate = LoJackServicesConnector.getLights(this.getUser());
		return Response.status(201).entity(new LightCollection(intermediate)).build();
	}
	
	@GET
	@Path("/{idEntidad}/{idLuz}/rename")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rename(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz, @QueryParam("description") String description) {
		validateLogged();
		try {
			TransactionProvider.executeInTransaction(new RenameLightAction(getUser(), idEntidad, idLuz, description));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{idEntidad}/{idLuz}/activate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activate(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.activateLight(this.getUser(), idEntidad, idLuz);
		if(jobResponse.getJobId() != 0) {
			return asyncOkResponse();
		} else {
			return asyncFailResponse();
		}
	}

	@GET
	@Path("/{idEntidad}/{idLuz}/deactivate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactivate(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.deactivateLight(this.getUser(), idEntidad, idLuz);
		if(jobResponse.getJobId() != 0) {
			return asyncOkResponse();
		} else {
			return asyncFailResponse();
		}
	}
	
	@GET
	@Path("/{idEntidad}/{idLuz}/randomOn")
	@Produces(MediaType.APPLICATION_JSON)
	public Response randomOn(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.activateLightRandomSequence(this.getUser(), idEntidad, idLuz);
		if(jobResponse.getJobId() != 0) {
			return asyncOkResponse();
		} else {
			return asyncFailResponse();
		}
	}
	@GET
	@Path("/{idEntidad}/{idLuz}/randomOff")
	@Produces(MediaType.APPLICATION_JSON)
	public Response randomOff(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.deactivateLightRandomSequence(this.getUser(), idEntidad, idLuz);
		if(jobResponse.getJobId() != 0) {
			return asyncOkResponse();
		} else {
			return asyncFailResponse();
		}
	}
	
	@GET
	@Path("/{idEntidad}/log")
	@Produces(MediaType.APPLICATION_JSON)
	public Response log(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		Collection<ChangeLog> log = LoJackServicesConnector.getAlarmLog(this.getUser(), idEntidad);
		return Response.status(201).entity(new LogCollection(log)).build();
	}
	
	@GET
	@Path("/{idEntidad}/{idLuz}/emailNotification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response emailNotification(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		try {
			TransactionProvider.executeInTransaction(new ActivateLightEmailNotification(getUser(), idEntidad, idLuz));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

	@GET
	@Path("/{idEntidad}/{idLuz}/noemailNotification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response noemailNotification(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		try {
			TransactionProvider.executeInTransaction(new DeactivateLightEmailNotification(getUser(), idEntidad, idLuz));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

}
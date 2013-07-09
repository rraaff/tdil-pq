package com.tdil.lojack.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.LightAgenda;
import com.tdil.lojack.rest.model.LightAgendaCollection;

@Path("/lightAgendas")
public class LightAgendasService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LightAgendasService.class);
	
	public LightAgendasService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}

	@GET
	@Path("/{idEntidad}/{idLuz}/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz) {
		validateLogged();
		Collection<LightAgenda> intermediate = LoJackServicesConnector.getLightAgendas(this.getUser(), idEntidad, idLuz);
		return Response.status(201).entity(new LightAgendaCollection(intermediate)).build();
	}
	
	@PUT
	@Path("/{idEntidad}/{idLuz}")
	@Consumes("application/json") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAgenda(@PathParam("idEntidad") int idEntidad, LightAgenda json) {
		return asResponse(LoJackServicesConnector.addLightAgenda(getUser(), idEntidad, json));
	}

	@POST
	@Path("/{idEntidad}/{idLuz}")
	@Consumes("application/json") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifyAgenda(@PathParam("idEntidad") int idEntidad, LightAgenda json) {
		return asResponse(LoJackServicesConnector.saveLightAgenda(getUser(), json));
	}
	
	@DELETE
	@Path("/{idEntidad}/{idAgenda}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAgenda(@PathParam("idEntidad") int idEntidad, @PathParam("idAgenda") int idAgenda) {
		return asResponse(LoJackServicesConnector.deleteLightAgenda(getUser(), idAgenda));
	}
	
	@GET
	@Path("/{idEntidad}/{idAgenda}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAgenda(@PathParam("idEntidad") int idEntidad, @PathParam("idAgenda") int idAgenda) {
		return asResponse(LoJackServicesConnector.activateLightAgenda(getUser(), idAgenda));
	}

}
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
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.rest.model.AlarmAgendaCollection;

@Path("/alarmAgendas")
public class AlarmAgendasService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AlarmAgendasService.class);
	
	public AlarmAgendasService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}

	@GET
	@Path("/{idEntidad}/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		Collection<AlarmAgenda> intermediate = LoJackServicesConnector.getAlarmAgendas(this.getUser(), idEntidad);
		return Response.status(201).entity(new AlarmAgendaCollection(intermediate)).build();
	}
	
	@PUT
	@Path("/{idEntidad}")
	@Consumes("application/json") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAgenda(@PathParam("idEntidad") int idEntidad, String json) {
		JSONObject obj = (JSONObject)extractJSONObjectResponse(json);
		AlarmAgenda alarmAgenda = (AlarmAgenda)JSONObject.toBean(obj, AlarmAgenda.class);
		return asResponse(LoJackServicesConnector.addAlarmAgenda(getUser(), idEntidad, alarmAgenda));
	}

	@POST
	@Path("/{idEntidad}")
	@Consumes("application/json") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifyAgenda(@PathParam("idEntidad") int idEntidad, String json) {
		JSONObject obj = (JSONObject)extractJSONObjectResponse(json);
		AlarmAgenda alarmAgenda = (AlarmAgenda)JSONObject.toBean(obj, AlarmAgenda.class);
		return asResponse(LoJackServicesConnector.saveAlarmAgenda(getUser(), alarmAgenda));
	}
	
	@DELETE
	@Path("/{idEntidad}/{idAgenda}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAgenda(@PathParam("idEntidad") int idEntidad, @PathParam("idAgenda") int idAgenda) {
		return asResponse(LoJackServicesConnector.deleteAlarmAgenda(getUser(), idAgenda));
	}
	
	@GET
	@Path("/{idEntidad}/{idAgenda}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAgenda(@PathParam("idEntidad") int idEntidad, @PathParam("idAgenda") int idAgenda) {
		return asResponse(LoJackServicesConnector.activateAlarmAgenda(getUser(), idAgenda));
	}

}
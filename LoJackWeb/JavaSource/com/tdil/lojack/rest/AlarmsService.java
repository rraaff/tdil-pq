package com.tdil.lojack.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.rest.model.AlarmCollection;
import com.tdil.lojack.rest.model.AsyncJobResponse;
import com.tdil.lojack.rest.model.LogCollection;

@Path("/alarms")
public class AlarmsService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	public AlarmsService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		validateLogged();
		Collection<Alarm> intermediate = LoJackServicesConnector.getAlarms(this.getUser());
		return Response.status(201).entity(new AlarmCollection(intermediate)).build();
	}
	
	@GET
	@Path("/{alarmid}/sendPanic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendPanic(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.sendPanicSignal(this.getUser(), idEntidad);
		if(jobResponse.getJobId() != 0) {
			return Response.status(201).entity(new AsyncJobResponse(true)).build();
		} else {
			return Response.status(201).entity(new AsyncJobResponse(false)).build();
		}
	}
	
	@GET
	@Path("/{alarmid}/activate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activate(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.activateAlarm(this.getUser(), idEntidad);
		if(jobResponse.getJobId() != 0) {
			return Response.status(201).entity(new AsyncJobResponse(true)).build();
		} else {
			return Response.status(201).entity(new AsyncJobResponse(false)).build();
		}
	}
	
	@GET
	@Path("/{alarmid}/deactivate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactivate(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.deactivateAlarm(this.getUser(), idEntidad);
		if(jobResponse.getJobId() != 0) {
			return Response.status(201).entity(new AsyncJobResponse(true)).build();
		} else {
			return Response.status(201).entity(new AsyncJobResponse(false)).build();
		}
	}
	
	@GET
	@Path("/{alarmid}/log")
	@Produces(MediaType.APPLICATION_JSON)
	public Response log(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		Collection<ChangeLog> log = LoJackServicesConnector.getAlarmLog(this.getUser(), idEntidad);
		return Response.status(201).entity(new LogCollection(log)).build();
	}

}
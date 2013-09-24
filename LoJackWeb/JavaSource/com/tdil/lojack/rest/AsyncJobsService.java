package com.tdil.lojack.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.lojack.rest.model.AlarmJobStatus;
import com.tdil.lojack.rest.model.AlarmJobStatusCollection;
import com.tdil.lojack.rest.model.LightJobStatus;
import com.tdil.lojack.rest.model.LightJobStatusCollection;
import com.tdil.lojack.servlet.GetAlarmJobSatesServlet;
import com.tdil.lojack.servlet.GetLightJobSatesServlet;
import com.tdil.lojack.utils.WebsiteUser;

@Path("/jobs")
public class AsyncJobsService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AsyncJobsService.class);
	
	public AsyncJobsService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}

	@GET
	@Path("/lights/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listLights() {
		validateLogged();
		final WebsiteUser user = getUser();
		try {
			List<AsyncJob> finished = user.getAndRemoveFinishedJobs();
			List<LightJobStatus> result = new ArrayList<LightJobStatus>();
			if (!finished.isEmpty()) {
				Collection<Light> lights = LoJackServicesConnector.getLights(user);
				for (AsyncJob asyncJob : finished) {
					Light light = GetLightJobSatesServlet.getLightFor(lights, asyncJob.getIdentidad(), asyncJob.getIdluz());
					LightJobStatus lightJobStatus = new LightJobStatus();
					lightJobStatus.setIdEntidad(asyncJob.getIdentidad());
					lightJobStatus.setIdLuz(asyncJob.getIdluz());
					lightJobStatus.setStatus(light.getStatusDescription());
					lightJobStatus.setUnknown(light.isStatusUnknown());
					lightJobStatus.setRan(light.isInRandomMode());
					lightJobStatus.setOn(light.isOn());
					result.add(lightJobStatus);
				}
			}
			return createResponse(201, new LightJobStatusCollection(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

	@GET
	@Path("/alarms/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAlarms() {
		validateLogged();
		final WebsiteUser user = getUser();
		try {
			List<AsyncJob> finished = user.getAndRemoveFinishedJobs();
			List<AlarmJobStatus> result = new ArrayList<AlarmJobStatus>();
			if (!finished.isEmpty()) {
				Collection<Alarm> alarms = LoJackServicesConnector.getAlarms(user);
				for (AsyncJob asyncJob : finished) {
					Alarm alarm = GetAlarmJobSatesServlet.getAlarmFor(alarms, asyncJob.getIdentidad());
					AlarmJobStatus lightJobStatus = new AlarmJobStatus();
					lightJobStatus.setIdEntidad(asyncJob.getIdentidad());
					lightJobStatus.setStatus(alarm.getStatus());
					lightJobStatus.setArmada(alarm.getStatus().equalsIgnoreCase(Alarm.ACTIVE));
					result.add(lightJobStatus);
				}
			}
			return createResponse(201, new AlarmJobStatusCollection(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
}
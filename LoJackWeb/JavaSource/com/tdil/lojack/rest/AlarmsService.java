package com.tdil.lojack.rest;

import java.sql.SQLException;
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
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.rest.model.AlarmCollection;
import com.tdil.lojack.rest.model.LogCollection;
import com.tdil.lojack.struts.action.ActivateAlarmEmailNotificationAjaxAction.ActivateAlarmEmailNotification;
import com.tdil.lojack.struts.action.DeactivateAlarmEmailNotificationAjaxAction.DeactivateAlarmEmailNotification;
import com.tdil.lojack.struts.action.RenameAlarmAjaxAction.RenameAlarmAction;
import com.tdil.lojack.struts.forms.AlarmsForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.TransactionalActionWithResult;

@Path("/alarms")
public class AlarmsService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AlarmsService.class);
	
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
		final WebsiteUser user = getUser();
		try {
			Collection<Alarm> intermediate = (Collection<Alarm>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				@Override
				public Object executeInTransaction() throws SQLException {
					return AlarmsForm.getAlarms(user);
				}
			});
			return createResponse(201, new AlarmCollection(intermediate));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

	@GET
	@Path("/{idEntidad}/rename")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rename(@PathParam("idEntidad") int idEntidad, @QueryParam("description") String description) {
		validateLogged();
		try {
			TransactionProvider.executeInTransaction(new RenameAlarmAction(idEntidad, getUser(), description));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{idEntidad}/sendPanic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendPanic(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.sendPanicSignal(this.getUser(), idEntidad);
		if(jobResponse.getJobId() != 0) {
			return asyncOkResponse();
		} else {
			return asyncFailResponse();
		}
	}
	
	@GET
	@Path("/{idEntidad}/activate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activate(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.activateAlarm(this.getUser(), idEntidad);
		if(jobResponse.getJobId() != 0) {
			return asyncOkResponse();
		} else {
			return asyncFailResponse();
		}
	}

	@GET
	@Path("/{idEntidad}/deactivate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactivate(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		com.tdil.lojack.gis.model.AsyncJobResponse jobResponse = LoJackServicesConnector.deactivateAlarm(this.getUser(), idEntidad);
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
		return createResponse(201, new LogCollection(log));
	}
	
	@GET
	@Path("/{idEntidad}/emailNotification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response emailNotification(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		try {
			TransactionProvider.executeInTransaction(new ActivateAlarmEmailNotification(getUser(), idEntidad));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

	@GET
	@Path("/{idEntidad}/noemailNotification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response noemailNotification(@PathParam("idEntidad") int idEntidad) {
		validateLogged();
		try {
			TransactionProvider.executeInTransaction(new DeactivateAlarmEmailNotification(getUser(), idEntidad));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

}
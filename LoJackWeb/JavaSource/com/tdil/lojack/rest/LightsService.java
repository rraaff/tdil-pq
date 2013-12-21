package com.tdil.lojack.rest;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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

import org.apache.commons.lang.StringUtils;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.model.WebsiteUserExample;
import com.tdil.lojack.rest.model.LightCollection;
import com.tdil.lojack.rest.model.LogCollection;
import com.tdil.lojack.struts.action.ActivateLightEmailNotificationAjaxAction.ActivateLightEmailNotification;
import com.tdil.lojack.struts.action.DeactivateLightEmailNotificationAjaxAction.DeactivateLightEmailNotification;
import com.tdil.lojack.struts.action.RenameLightAjaxAction.RenameLightAction;
import com.tdil.lojack.struts.forms.LightsForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

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
		final WebsiteUser user = getUser();
		try {
			Collection<Light> intermediate = GenericTransactionExecutionService.getInstance().execute(new TransactionalActionWithResult<Collection<Light>>() {
				@Override
				public Collection<Light> executeInTransaction() throws SQLException {
					Collection<Light> lights = LightsForm.getLights(user);
					for (Light light : lights) {
						if (StringUtils.isEmpty(light.getLastChangeLojackUserID())) {
							light.setLastChangeLojackUserID("images/skin_lj_rl/logos/avatarBase.png");
						} else {
							com.tdil.lojack.model.WebsiteUser logUsr = WebsiteUserUtils.getWebSiteUserByHomeUserId(light.getLastChangeLojackUserID());
							if (logUsr != null) {
								if (user != null && WebsiteUserUtils.hasAvatar(logUsr)) {
									light.setLastChangeLojackUserID("./download.st?id=" + logUsr.getIdAvatar() + "&type=PUBLIC&ext=" + logUsr.getExtAvatar());
								} else {
									light.setLastChangeLojackUserID("images/skin_lj_rl/logos/avatarBase.png");
								}
							} else {
								light.setLastChangeLojackUserID("images/skin_lj_rl/logos/avatarBase.png");
							}
						}
					}
					return lights;
				}
			});
			return createResponse(201, new LightCollection(intermediate));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{idEntidad}/{idLuz}/rename")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rename(@PathParam("idEntidad") int idEntidad, @PathParam("idLuz") int idLuz, @QueryParam("description") String description) {
		validateLogged();
		try {
			GenericTransactionExecutionService.getInstance().execute(new RenameLightAction(getUser(), idEntidad, idLuz, description));
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
	@Path("/{idEntidad}/{idLuz}/log")
	@Produces(MediaType.APPLICATION_JSON)
	public Response log(@PathParam("idEntidad") final int idEntidad,@PathParam("idLuz") final int idLuz) {
		validateLogged();
		final WebsiteUser user = getUser();
		try {
			Collection<ChangeLog> intermediate = GenericTransactionExecutionService.getInstance().execute(new TransactionalActionWithResult<Collection<ChangeLog>>() {
				@Override
				public Collection<ChangeLog> executeInTransaction() throws SQLException {
					Collection<ChangeLog> logs = LoJackServicesConnector.getLightLog(user, idEntidad, idLuz);
					for (ChangeLog alarm : logs) {
						if (StringUtils.isEmpty(alarm.getLojackUserId())) {
							alarm.setLojackUserId("images/skin_lj_rl/logos/avatarBase.png");
						} else {
							com.tdil.lojack.model.WebsiteUser logUsr = WebsiteUserUtils.getWebSiteUserByHomeUserId(alarm.getLojackUserId());
							if (logUsr != null) {
								if (user != null && WebsiteUserUtils.hasAvatar(logUsr)) {
									alarm.setLojackUserId("./download.st?id=" + logUsr.getIdAvatar() + "&type=PUBLIC&ext=" + logUsr.getExtAvatar());
								} else {
									alarm.setLojackUserId("images/skin_lj_rl/logos/avatarBase.png");
								}
							} else {
								alarm.setLojackUserId("images/skin_lj_rl/logos/avatarBase.png");
							}
						}
					}
					return logs;
				}
			});
			return createResponse(201, new LogCollection(intermediate));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
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
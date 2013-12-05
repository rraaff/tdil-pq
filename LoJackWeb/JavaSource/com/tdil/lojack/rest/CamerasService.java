package com.tdil.lojack.rest;

import java.sql.SQLException;
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

import org.apache.commons.lang.StringUtils;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.Camera;
import com.tdil.lojack.model.CameraConf;
import com.tdil.lojack.model.CameraConfExample;
import com.tdil.lojack.model.WebsiteUserExample;
import com.tdil.lojack.rest.model.CameraCollection;
import com.tdil.lojack.struts.forms.AlarmsForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

@Path("/cameras")
public class CamerasService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(CamerasService.class);
	
	public CamerasService() {
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
			
			Collection<Camera> intermediate = GenericTransactionExecutionService.getInstance().execute(new TransactionalActionWithResult<Collection<Camera>>() {
				@Override
				public Collection<Camera> executeInTransaction() throws SQLException {
					Collection<Camera> cameras = LoJackServicesConnector.getCameras(user);
					CameraConfExample example = new CameraConfExample();
					example.createCriteria().andIdwebsiteuserEqualTo(user.getId());
					List<CameraConf> cameraConfs = DAOManager.getCameraConfDAO().selectCameraConfByExample(example);
					for (Camera cam : cameras) {
						enhance(cam, cameraConfs);
					}
					return cameras;
				}
			});
			return createResponse(201, new CameraCollection(intermediate));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}

	private static void enhance(Camera camera, List<CameraConf> alarmConf) {
		for (CameraConf ac : alarmConf) {
			if (ac.getUrl().equals(camera.getUrl())) {
				camera.setDescription(ac.getDescription());
				return;
			}
		}
	}

}
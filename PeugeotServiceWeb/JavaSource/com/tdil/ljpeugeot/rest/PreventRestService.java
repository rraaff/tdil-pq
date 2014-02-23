package com.tdil.ljpeugeot.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.prevent.URLParams;
import com.tdil.ljpeugeot.prevent.model.PhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.PhoneNumbersReponse;
import com.tdil.ljpeugeot.prevent.model.SecureZone;
import com.tdil.ljpeugeot.prevent.model.SecureZoneResponse;
import com.tdil.ljpeugeot.prevent.model.SecureZones;
import com.tdil.ljpeugeot.prevent.model.SpeedLimit;
import com.tdil.ljpeugeot.prevent.model.SpeedLimitResponse;
import com.tdil.ljpeugeot.prevent.model.SpeedLimits;
import com.tdil.ljpeugeot.prevent.model.UpdatePhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.Vehicle;
import com.tdil.ljpeugeot.prevent.model.Vehicles;
import com.tdil.ljpeugeot.rest.model.BeanCollection;
import com.tdil.ljpeugeot.rest.prevent.model.PhoneNumbersBean;
import com.tdil.ljpeugeot.rest.prevent.model.SecureZoneBean;
import com.tdil.ljpeugeot.rest.prevent.model.SpeedLimitBean;
import com.tdil.ljpeugeot.rest.prevent.model.VehicleBean;
import com.tdil.log4j.LoggerProvider;

@Path("/prevent")
public class PreventRestService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(PreventRestService.class);
	
	public PreventRestService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}
	
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}
	
	@GET
	@Path("/vehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listVehicles() {
		validateLogged();
		try {
			Collection<VehicleBean> result = new ArrayList<VehicleBean>();
			URLParams getVehicles = new URLParams(getUser().getPreventLoginResponse()).index("0");
			for (Vehicle state : ((Vehicles)PreventConnector.getVehicles(getVehicles).getResult()).getVehiclesCollection()) {
				result.add(new VehicleBean(state));
			}
			return createResponse(201, new BeanCollection<VehicleBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{vehicleId}/secureZones")
	@Produces(MediaType.APPLICATION_JSON)
	public Response secureZones(@PathParam("vehicleId") String vehicleId) {
		validateLogged();
		try {
			Collection<SecureZoneBean> result = new ArrayList<SecureZoneBean>();
			Vehicle vehicle = new Vehicle();
			vehicle.setId(vehicleId);
			for (SecureZone state : ((SecureZones)PreventConnector.getVehicleSecureZones(getUser().getPreventLoginResponse(), vehicle).getResult()).getSecureZones()) {
				result.add(new SecureZoneBean(state));
			}
			
			return createResponse(201, new BeanCollection<SecureZoneBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@POST
	@Path("/{vehicleId}/secureZone")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setSecureZone(@PathParam("vehicleId") String vehicleId, String body) {
		validateLogged();
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(vehicleId);
			SecureZoneBean personBean = extractObjectFromJSON(body, SecureZoneBean.class);
			SecureZone secureZone = SecureZoneBean.asSecureZone(personBean);
			SecureZoneResponse resp = (SecureZoneResponse)PreventConnector.setVehicleSecureZone(getUser().getPreventLoginResponse(), vehicle, secureZone).getResult();
			if ("OK".equals(resp.getStatus())) {
				return okResponse();
			} else {
				return failResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@GET
	@Path("/{vehicleId}/speedLimits")
	@Produces(MediaType.APPLICATION_JSON)
	public Response speedLimits(@PathParam("vehicleId") String vehicleId) {
		validateLogged();
		try {
			Collection<SpeedLimitBean> result = new ArrayList<SpeedLimitBean>();
			Vehicle vehicle = new Vehicle();
			vehicle.setId(vehicleId);
			for (SpeedLimit state : ((SpeedLimits)PreventConnector.getVehicleSpeedLimit(getUser().getPreventLoginResponse(), vehicle).getResult()).getLimits()) {
				result.add(new SpeedLimitBean(state));
			}
			
			return createResponse(201, new BeanCollection<SpeedLimitBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@POST
	@Path("/{vehicleId}/speedLimit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setSpeedLimits(@PathParam("vehicleId") String vehicleId, String body) {
		validateLogged();
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(vehicleId);
			SpeedLimitBean personBean = extractObjectFromJSON(body, SpeedLimitBean.class);
			SpeedLimit secureZone = SpeedLimitBean.asSpeedLimit(personBean);
			SpeedLimitResponse resp = (SpeedLimitResponse)PreventConnector.setVehicleSpeedLimit(getUser().getPreventLoginResponse(), vehicle, secureZone).getResult();
			if ("OK".equals(resp.getStatus())) {
				return okResponse();
			} else {
				return failResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@GET
	@Path("/{vehicleId}/phoneNumbers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response phoneNumbers(@PathParam("vehicleId") String vehicleId) {
		validateLogged();
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(vehicleId);
			PhoneNumbers pn = (PhoneNumbers)PreventConnector.getVehiclePhones(getUser().getPreventLoginResponse(), vehicle).getResult();
			return createResponse(201, new PhoneNumbersBean(pn));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@POST
	@Path("/{vehicleId}/phoneNumber")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setPhoneNumber(@PathParam("vehicleId") String vehicleId, String body) {
		validateLogged();
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(vehicleId);
			PhoneNumbersBean personBean = extractObjectFromJSON(body, PhoneNumbersBean.class);
			UpdatePhoneNumbers secureZone = PhoneNumbersBean.asPhoneNumbers(personBean);
			secureZone.setVehicleID(vehicleId);
			secureZone.setUserToken(getUser().getPreventLoginResponse().getUserToken());
			PhoneNumbersReponse resp = (PhoneNumbersReponse)PreventConnector.setVehiclePhones(getUser().getPreventLoginResponse(), secureZone).getResult();
			if ("OK".equals(resp.getStatus())) {
				return okResponse();
			} else {
				return failResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
}
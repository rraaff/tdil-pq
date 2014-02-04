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

import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.rest.model.BeanCollection;
import com.tdil.ljpeugeot.rest.model.ContactDataBean;
import com.tdil.ljpeugeot.rest.model.DealerBean;
import com.tdil.ljpeugeot.rest.model.ModelBean;
import com.tdil.ljpeugeot.rest.model.ServiceBean;
import com.tdil.ljpeugeot.rest.model.VehicleBean;
import com.tdil.ljpeugeot.services.DealersService;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.log4j.LoggerProvider;

@Path("/vehicles")
public class VehiclesRestService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(VehiclesRestService.class);
	
	public VehiclesRestService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}
	
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listVehicles() {
//		validateLogged();
		try {
			Collection<VehicleBean> result = new ArrayList<VehicleBean>();
			for (Vehicle state : PeugeotService.getVehicles(getUser().getId())) {
				result.add(new VehicleBean(state));
			}
			return createResponse(201, new BeanCollection<VehicleBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{vehicleId}/changeDealer/{dealerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeDealer(@PathParam("vehicleId") String vehicleId, @PathParam("dealerId") String dealerId) {
//		validateLogged();
		try {
			Vehicle vehicle = PeugeotService.getVehicle(Integer.parseInt(vehicleId));
			vehicle.setIdDealer(Integer.parseInt(dealerId));
			PeugeotService.udpateVehicle(vehicle);
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{vehicleId}/services")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServices(@PathParam("vehicleId") String vehicleId) {
//		validateLogged();
		try {
			Collection<ServiceBean> result = new ArrayList<ServiceBean>();
			for (Service state : PeugeotService.getServices(Integer.valueOf(vehicleId))) {
				result.add(new ServiceBean(state));
			}
			return createResponse(201, new BeanCollection<ServiceBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@POST
	@Path("/{vehicleId}/service")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addService(String body) {
		validateLogged();
		try {
			ServiceBean personBean = extractObjectFromJSON(body, ServiceBean.class);
			Service service = ServiceBean.asService(personBean);
			service.setDeleted(0);
			if (PeugeotService.insertService(service)) {
				return okResponse();
			} else {
				return failResponse();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}
	
	@POST
	@Path("/{vehicleId}/updateService/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateService(@PathParam("serviceId") String serviceId, String body) {
		validateLogged();
		try {
			ServiceBean personBean = extractObjectFromJSON(body, ServiceBean.class);
			Service service = ServiceBean.asService(personBean);
			service.setId(Integer.parseInt(serviceId));
			service.setDeleted(0);
			if (PeugeotService.updateService(service)) {
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
	@Path("/dealers/{cityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDealers(@PathParam("cityId") String cityId) {
//		validateLogged();
		try {
			Collection<DealerBean> result = new ArrayList<DealerBean>();
			for (Dealer dealer : DealersService.getDealers(Integer.parseInt(cityId))) {
				result.add(new DealerBean(dealer));
			}
			return createResponse(201, new BeanCollection<DealerBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listModels() {
//		validateLogged();
		try {
			Collection<ModelBean> result = new ArrayList<ModelBean>();
			for (Model dealer : PeugeotService.getModels()) {
				result.add(new ModelBean(dealer));
			}
			return createResponse(201, new BeanCollection<ModelBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
}
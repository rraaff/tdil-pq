package com.tdil.ljpeugeot.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.valueobjects.VehicleValueObject;
import com.tdil.ljpeugeot.rest.model.BeanCollection;
import com.tdil.ljpeugeot.rest.model.DealerBean;
import com.tdil.ljpeugeot.rest.model.EmailBean;
import com.tdil.ljpeugeot.rest.model.ModelBean;
import com.tdil.ljpeugeot.rest.model.ServiceBean;
import com.tdil.ljpeugeot.rest.prevent.model.VehicleValueObjectBean;
import com.tdil.ljpeugeot.services.DealersService;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.struts.forms.prevent.ChangeDealerForm;
import com.tdil.ljpeugeot.utils.WebsiteUserUtils;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

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
	@Path("/emailForAdvice")
	@Produces(MediaType.APPLICATION_JSON)
	public Response emailForAdvice() {
//		validateLogged();
		try {
			String email = WebsiteUserUtils.getWebSiteUserById(this.getUser().getModelUser().getId()).getEmail();
			return createResponse(201, new EmailBean(email));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listVehicles() {
//		validateLogged();
		try {
			List<VehicleValueObject> vehicleValueObjects = PeugeotService.getMyVehicles(getUser().getModelUser().getId());
			Collection<VehicleValueObjectBean> result = new ArrayList<VehicleValueObjectBean>();
			for (VehicleValueObject vehicle : vehicleValueObjects) {
				result.add(new VehicleValueObjectBean(vehicle));
			}
			return createResponse(201, new BeanCollection<VehicleValueObjectBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/{vehicleId}/changeDealer/{dealerId}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeDealer(@PathParam("vehicleId") String vehicleId, @PathParam("dealerId") String dealerId, @PathParam("email") String email) {
//		validateLogged();
		try {
			final ChangeDealerForm changeDealerForm = new ChangeDealerForm();
			changeDealerForm.setUser(this.getUser());
			changeDealerForm.setEmail(email);
			changeDealerForm.setIdDealer(Integer.parseInt(dealerId));
			changeDealerForm.setIdVehicle(Integer.parseInt(vehicleId));
			GenericTransactionExecutionService.getInstance().execute(new TransactionalAction() {
				@Override
				public void executeInTransaction() throws SQLException, ValidationException {
					changeDealerForm.save();
				}
			});
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
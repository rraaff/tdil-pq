package com.tdil.ljpeugeot.rest;

import java.util.ArrayList;
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

import net.sf.json.JSONObject;

import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.rest.model.BeanCollection;
import com.tdil.ljpeugeot.rest.model.CityBean;
import com.tdil.ljpeugeot.rest.model.DealerBean;
import com.tdil.ljpeugeot.rest.model.StateBean;
import com.tdil.ljpeugeot.services.DealersService;
import com.tdil.log4j.LoggerProvider;

@Path("/dealers")
public class DealersRestService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(DealersRestService.class);
	
	public DealersRestService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}
	
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}
	
	@GET
	@Path("/states")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listStates() {
//		validateLogged();
		try {
			Collection<StateBean> result = new ArrayList<StateBean>();
			for (State state : DealersService.getStates()) {
				result.add(new StateBean(state));
			}
			return createResponse(201, new BeanCollection<StateBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/cities/{stateId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listCities(@PathParam("stateId") String stateId) {
//		validateLogged();
		try {
			Collection<CityBean> result = new ArrayList<CityBean>();
			for (City city : DealersService.getCities(Integer.parseInt(stateId))) {
				result.add(new CityBean(city));
			}
			return createResponse(201, new BeanCollection<CityBean>(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}
	
	@GET
	@Path("/list/{cityId}")
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
	

	public <T> T extractObjectFromJSON(String body, Class<T> aClass) {
		return (T)JSONObject.toBean((JSONObject)extractJSONObject(body), aClass);
	}
	
}
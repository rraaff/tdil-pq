package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.URLParams;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.prevent.model.Vehicles;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public abstract class VehiclesForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private List<Vehicle> vehicles;
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}

	public void reset() {
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(VehiclesForm.class);
	}
	
	public boolean hasOnlyOne() {
		return this.getVehicles().size() == 1;
	}
	
	public String getVehicleId() {
		return this.getVehicles().get(0).getId();
	}

	public void initWith(WebsiteUser user) throws CommunicationException, HttpStatusException, InvalidResponseException, UnauthorizedException {
		setUser(user);
		try {
			basicinitWith();
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicinitWith();
		}
	}

	private void basicinitWith() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		URLParams getVehicles = new URLParams(user.getPreventLoginResponse()).index("0");
		Vehicles vehicles = (Vehicles)PreventConnector.getVehicles(getVehicles).getResult();
		List<Vehicle> toset = new ArrayList<Vehicle>();
		for (Vehicle v : vehicles.getVehiclesCollection()) {
			if (v.getStatus().equals("true")) {
				toset.add(v);
			}
		}
		setVehicles(toset);
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


}

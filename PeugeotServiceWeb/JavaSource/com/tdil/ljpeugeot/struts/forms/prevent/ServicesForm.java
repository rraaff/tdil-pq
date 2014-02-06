package com.tdil.ljpeugeot.struts.forms.prevent;

import java.util.List;

import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class ServicesForm extends VehiclesForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private com.tdil.ljpeugeot.prevent.model.Vehicle selectedVehicle;
	private com.tdil.ljpeugeot.model.Vehicle vehicle;
	
	private List<Service> services;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ServicesForm.class);
	
	public void initWith(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		super.initWith(user);
		selectVehicle(id);
	}

	public void selectVehicle(String id) {
		for(com.tdil.ljpeugeot.prevent.model.Vehicle itervehicle : getVehicles()) {
			if (itervehicle.getId().equals(id)) {
				selectedVehicle = itervehicle;
				vehicle = PeugeotService.getVehicle(this.getUser().getId(), itervehicle.getDescription());
				if (vehicle == null) {
					vehicle = new com.tdil.ljpeugeot.model.Vehicle();
				} 
				this.setServices(PeugeotService.getServices(vehicle.getId()));
				return;
			}
		}
	}
	
	
	public com.tdil.ljpeugeot.model.Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(com.tdil.ljpeugeot.model.Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}


}

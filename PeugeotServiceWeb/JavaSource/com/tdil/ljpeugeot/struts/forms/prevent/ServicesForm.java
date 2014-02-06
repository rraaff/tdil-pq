package com.tdil.ljpeugeot.struts.forms.prevent;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.struts.forms.ITransactionalActionForm;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.utils.DateUtils;

public class ServicesForm extends VehiclesForm implements ITransactionalActionForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private com.tdil.ljpeugeot.prevent.model.Vehicle selectedVehicle;
	private com.tdil.ljpeugeot.model.Vehicle vehicle;
	
	private int serviceId;
	private String km;
	private String date;
	
	private List<Service> services;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ServicesForm.class);
	
	@Override
	public void reset() {
		serviceId = 0;
		km = null;
		date = null;
	}
	
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
	
	public void save() throws SQLException {
		if (serviceId != 0) {
			Service service = DAOManager.getServiceDAO().selectServiceByPrimaryKey(serviceId);
			service.setKm(Integer.parseInt(this.getKm()));
			service.setServicedate(DateUtils.parseDateSp(this.getDate()));
			DAOManager.getServiceDAO().updateServiceByPrimaryKey(service);
		} else {
			Service service = new Service();
			service.setKm(Integer.parseInt(this.getKm()));
			service.setServicedate(DateUtils.parseDateSp(this.getDate()));
			service.setDeleted(0);
			service.setIdVehicle(getVehicle().getId());
			DAOManager.getServiceDAO().insertService(service);
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

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String getOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() throws SQLException {
		this.setServices(PeugeotService.getServices(vehicle.getId()));
	}

	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public ValidationError validate() {
		// TODO Auto-generated method stub
		return new ValidationError();
	}


}

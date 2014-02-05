package com.tdil.ljpeugeot.struts.forms.prevent;

import java.util.ArrayList;
import java.util.List;

import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class EditVehicleDataForm extends VehiclesForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private com.tdil.ljpeugeot.model.Vehicle vehicle;
	private int idState;
	private int idCity;
	private int idDealer;
	private List<State> states;
	private List<City> cities;
	private List<Dealer> dealers;

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(EditVehicleDataForm.class);
	
	@Override
	public void initWith(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		super.initWith(user);
		try {
			basicinitWith(user);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicinitWith(user);
		}
	}

	private void basicinitWith(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException,
			UnauthorizedException {
		if (hasOnlyOne()) {
			// busco los datos del vehiculo
			vehicle = PeugeotService.getVehicle(this.getUser().getId(), getVehicles().get(0).getDescription());
			if (vehicle == null) {
				vehicle = new com.tdil.ljpeugeot.model.Vehicle();
			} 
		} 
		initCombos();
	}

	private void initCombos() {
		if (this.getVehicle().getId() != null) {
			Dealer dealer = PeugeotService.getDealer(this.vehicle.getIdDealer());
			this.idDealer = dealer.getId(); 
			City city = PeugeotService.getCity(dealer.getIdCity());
			this.idCity = city.getId();
			this.idState = city.getIdState();
			this.cities = PeugeotService.getCities(city.getIdState());
		} else {
			this.cities = new ArrayList<City>();
			this.dealers = new ArrayList<Dealer>();
		}
		this.states = PeugeotService.getStates();
	}

	public void selectVehicle(String id) {
		for(com.tdil.ljpeugeot.prevent.model.Vehicle itervehicle : getVehicles()) {
			if (itervehicle.getId().equals(id)) {
				vehicle = PeugeotService.getVehicle(this.getUser().getId(), itervehicle.getDescription());
				if (vehicle == null) {
					vehicle = new com.tdil.ljpeugeot.model.Vehicle();
				} else {
					initCombos();
				}
				return;
			}
		}
	}
	
	public void save() {
		
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(List<Dealer> dealers) {
		this.dealers = dealers;
	}

	public com.tdil.ljpeugeot.model.Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(com.tdil.ljpeugeot.model.Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getIdState() {
		return idState;
	}

	public void setIdState(int idState) {
		this.idState = idState;
	}

	public int getIdCity() {
		return idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public int getIdDealer() {
		return idDealer;
	}

	public void setIdDealer(int idDealer) {
		this.idDealer = idDealer;
	}


}

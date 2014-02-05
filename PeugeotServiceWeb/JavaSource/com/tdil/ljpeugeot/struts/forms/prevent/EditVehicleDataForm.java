package com.tdil.ljpeugeot.struts.forms.prevent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.services.DealersService;
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

	private com.tdil.ljpeugeot.prevent.model.Vehicle selectedVehicle;
	private com.tdil.ljpeugeot.model.Vehicle vehicle;
	private int idState;
	private int idCity;
	private int idDealer;
	private List<State> states;
	private List<City> cities;
	private Collection<Dealer> dealers;

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
			selectedVehicle = getVehicles().get(0);
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
			this.dealers = DealersService.getDealers(dealer.getIdCity());
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
				selectedVehicle = itervehicle;
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
	
	public void save() throws SQLException {
		if (this.getVehicle().getId() != null) {
			vehicle.setIdDealer(getIdDealer());
			DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		} else {
			Vehicle vehicle = new Vehicle();
			vehicle.setIdWebsiteuser(getUser().getId());
			vehicle.setDomain(selectedVehicle.getDescription());
			vehicle.setAdvice1sent(0);
			vehicle.setAdvice2sent(0);
			vehicle.setAdvice3sent(0);
			vehicle.setDeleted(0);
			vehicle.setIdDealer(getIdDealer());
			vehicle.setKm(0); // TODO ESTo tiene que venir de prevent
			vehicle.setIdModel(PeugeotService.getModels().get(0).getId()); // IDEM
			vehicle.setLastservicedate(new Date()); // TODO ESTO debe ir con el de laconcecionaria
			vehicle.setLastservicekm(0);
			vehicle.setNeedsadvice(0);
			vehicle.setNeedsadvice1(0);
			vehicle.setNeedsadvice2(0);
			vehicle.setNeedsadvice3(0);
			vehicle.setPurchasedate(new Date()); // idem
			vehicle.setWarrantyexpired(0);
			DAOManager.getVehicleDAO().insertVehicle(vehicle);
		}
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

	public Collection<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(Collection<Dealer> dealers) {
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

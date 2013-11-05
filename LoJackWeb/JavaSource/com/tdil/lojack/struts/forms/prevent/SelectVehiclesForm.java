package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.List;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.PhoneNumbers;
import com.tdil.lojack.prevent.model.SatellitePosition;
import com.tdil.lojack.prevent.model.UpdatePhoneNumbers;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class SelectVehiclesForm extends VehiclesForm {

	/**
	 *
	 */
	private static final long serialVersionUID = 3752656266263380512L;

	private List<Vehicle> selectList;
	private List<SatellitePosition> selectedVehiclePosition;

	private String alertPhone;
	private String crashPhone;
	private String otherPhone;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SelectVehiclesForm.class);

	public void selectVehicleForMap(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		setUser(user);
		try {
			basicselectVehicleForMap(user, id);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicselectVehicleForMap(user, id);
		}
	}
	
	public void loadAllVehiclesPositions(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		List<SatellitePosition> allPos = new ArrayList<SatellitePosition>();
		for(Vehicle vehicle : this.getVehicles()) {
			allPos.add((SatellitePosition)PreventConnector.getVehicleSatPosition(user.getPreventLoginResponse(), vehicle).getResult());
		}
		selectedVehiclePosition = allPos;
	}
	
	private void basicselectVehicleForMap(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				setSelectedVehiclePosition((SatellitePosition)PreventConnector.getVehicleSatPosition(user.getPreventLoginResponse(), vehicle).getResult());
				return;
			}
		}
	}

	public void selectVehicleForPhone(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		setUser(user);
		try {
			basicselectVehicleForPhone(user, id);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicselectVehicleForPhone(user, id);
		}
	}
	
	private void basicselectVehicleForPhone(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				PhoneNumbers pn = (PhoneNumbers)(PreventConnector.getVehiclePhones(user.getPreventLoginResponse(), vehicle).getResult());
				setAlertPhone(pn.getAlert());
				setCrashPhone(pn.getCrash());
				setOtherPhone(pn.getOther());
				return;
			}
		}
	}

	public Vehicle getSelected() {
		return selectList.get(0);
	}
	public void setSelected(Vehicle selected) {
		List<Vehicle> list = new ArrayList<Vehicle>();
		list.add(selected);
		this.selectList = list;
	}
	public List<SatellitePosition> getSelectedVehiclePosition() {
		return selectedVehiclePosition;
	}
	public void setSelectedVehiclePosition(SatellitePosition selectedVehiclePosition) {
		List<SatellitePosition> list = new ArrayList<SatellitePosition>();
		list.add(selectedVehiclePosition);
		this.selectedVehiclePosition = list;
	}
	public void setSelectedVehiclePosition(List<SatellitePosition> selectedVehiclePosition) {
		this.selectedVehiclePosition = selectedVehiclePosition;
	}

	public String getAlertPhone() {
		return alertPhone;
	}

	public void setAlertPhone(String alertPhone) {
		this.alertPhone = alertPhone;
	}

	public String getCrashPhone() {
		return crashPhone;
	}

	public void setCrashPhone(String crashPhone) {
		this.crashPhone = crashPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public boolean savePhones() throws ValidationException, HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			return this.basicsavePhones();
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			return this.basicsavePhones();
		}
	}

	private boolean basicsavePhones() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		UpdatePhoneNumbers phoneNumbers = new UpdatePhoneNumbers();
		phoneNumbers.setAlert(this.getAlertPhone());
		phoneNumbers.setCrash(this.getCrashPhone());
		phoneNumbers.setOther(this.getOtherPhone());
		phoneNumbers.setVehicleID(this.getSelected().getId());
		phoneNumbers.setUserToken(this.getUser().getPreventLoginResponse().getUserToken());
		XMLResponse setSpeed = PreventConnector.setVehiclePhones(this.getUser().getPreventLoginResponse(), phoneNumbers);
		PhoneNumbers resp = (PhoneNumbers)setSpeed.getResult();
		if ("OK".equals(resp.getStatus())) {
			return true;
		} else {
			return false;
		}
	}

	public List<Vehicle> getSelectList() {
		return selectList;
	}

	public void setSelectList(List<Vehicle> selectList) {
		this.selectList = selectList;
	}

}

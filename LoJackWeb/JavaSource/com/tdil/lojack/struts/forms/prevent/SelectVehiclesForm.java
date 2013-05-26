package com.tdil.lojack.struts.forms.prevent;

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

	private Vehicle selected;
	private SatellitePosition selectedVehiclePosition;

	private String alertPhone;
	private String crashPhone;
	private String otherPhone;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SelectVehiclesForm.class);

	public void selectVehicleForMap(WebsiteUser user, String id) {
		setUser(user);
		try {
			basicselectVehicleForMap(user, id);
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			try {
				user.reloginPrevent();
				basicselectVehicleForMap(user, id);
			} catch (HttpStatusException e1) {
				LOG.error(e.getMessage(), e);
			} catch (InvalidResponseException e1) {
				LOG.error(e.getMessage(), e);
			} catch (CommunicationException e1) {
				LOG.error(e.getMessage(), e);
			} catch (UnauthorizedException e1) {
				LOG.error(e.getMessage(), e);
			}
		}
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

	public void selectVehicleForPhone(WebsiteUser user, String id) {
		setUser(user);
		try {
			basicselectVehicleForPhone(user, id);
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			try {
				user.reloginPrevent();
				basicselectVehicleForPhone(user, id);
			} catch (HttpStatusException e1) {
				LOG.error(e.getMessage(), e);
			} catch (InvalidResponseException e1) {
				LOG.error(e.getMessage(), e);
			} catch (CommunicationException e1) {
				LOG.error(e.getMessage(), e);
			} catch (UnauthorizedException e1) {
				LOG.error(e.getMessage(), e);
			}
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
		return selected;
	}
	public void setSelected(Vehicle selected) {
		this.selected = selected;
	}
	public SatellitePosition getSelectedVehiclePosition() {
		return selectedVehiclePosition;
	}
	public void setSelectedVehiclePosition(SatellitePosition selectedVehiclePosition) {
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

	public boolean savePhones() throws ValidationException {
		try {
			return this.basicsavePhones();
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			try {
				this.getUser().reloginPrevent();
				return this.basicsavePhones();
			} catch (HttpStatusException e1) {
				LOG.error(e.getMessage(), e);
			} catch (InvalidResponseException e1) {
				LOG.error(e.getMessage(), e);
			} catch (CommunicationException e1) {
				LOG.error(e.getMessage(), e);
			} catch (UnauthorizedException e1) {
				LOG.error(e.getMessage(), e);
			}
		}
		return false;
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

}

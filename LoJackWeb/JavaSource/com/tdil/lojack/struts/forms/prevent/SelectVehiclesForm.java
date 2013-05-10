package com.tdil.lojack.struts.forms.prevent;

import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.PhoneNumbers;
import com.tdil.lojack.prevent.model.SatellitePosition;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.struts.forms.beans.SpeedSelectionBean;
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

	public void selectVehicleForMap(String id) {
		try {
			for(Vehicle vehicle : this.getVehicles()) {
				if (vehicle.getId().equals(id)) {
					setSelected(vehicle);
					if (resp == null) {
						resp = PreventConnector.getLogin();
					}
					setSelectedVehiclePosition((SatellitePosition)PreventConnector.getVehicleSatPosition(resp, vehicle).getResult());
					return;
				}
			}
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectVehicleForPhone(String id) {
		try {
			for(Vehicle vehicle : this.getVehicles()) {
				if (vehicle.getId().equals(id)) {
					setSelected(vehicle);
					if (resp == null) {
						resp = PreventConnector.getLogin();
					}
					PhoneNumbers pn = (PhoneNumbers)(PreventConnector.getVehiclePhones(resp, vehicle).getResult());
					setAlertPhone(pn.getAlert());
					setCrashPhone(pn.getCrash());
					setOtherPhone(pn.getOther());
					return;
				}
			}
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public void savePhones() throws ValidationException {
		if (resp == null) {
			resp = PreventConnector.getLogin();
		}
			try {
				PhoneNumbers phoneNumbers = new PhoneNumbers();
				phoneNumbers.setAlert(this.getAlertPhone());
				phoneNumbers.setCrash(this.getCrashPhone());
				phoneNumbers.setOther(this.getOtherPhone());
				phoneNumbers.setVehicleID(this.getSelected().getId());
				phoneNumbers.setUserToken(resp.getUserToken());
				XMLResponse setSpeed = PreventConnector.setVehiclePhones(resp, phoneNumbers);
				System.out.println(setSpeed.getResult());
				// TODO Capturar los errores SpeedLimitResponse slr = (SpeedLimitResponse)resp.getResult();
			} catch (HttpStatusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CommunicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnauthorizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

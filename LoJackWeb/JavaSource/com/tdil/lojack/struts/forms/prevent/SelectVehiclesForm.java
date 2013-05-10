package com.tdil.lojack.struts.forms.prevent;

import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.model.SatellitePosition;
import com.tdil.lojack.prevent.model.Vehicle;
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

}

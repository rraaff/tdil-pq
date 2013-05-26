package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.SecureZones;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.struts.forms.beans.SecureZoneSelectionBean;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class VehiclesSecureZoneForm extends VehiclesForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private Map<String, SecureZones> vehicleIdToSecureZones;
	private List<SecureZoneSelectionBean> secureZones;

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(VehiclesSecureZoneForm.class);
	
	@Override
	public void initWith(WebsiteUser user) {
		// TODO Auto-generated method stub
		super.initWith(user);
		if (resp == null) {
			resp = PreventConnector.getLogin();
		}
		vehicleIdToSecureZones = new HashMap<String, SecureZones>();
		secureZones = new ArrayList<SecureZoneSelectionBean>();
		for (Vehicle vehicle : this.getVehicles()) {
			try {
				SecureZones limits = (SecureZones)PreventConnector.getVehicleSecureZones(resp, vehicle).getResult();
				vehicleIdToSecureZones.put(vehicle.getId(), limits);
				secureZones.add(new SecureZoneSelectionBean(vehicle, limits));
			} catch (HttpStatusException e) {
				LOG.error(e.getMessage(), e);
			} catch (InvalidResponseException e) {
				LOG.error(e.getMessage(), e);
			} catch (CommunicationException e) {
				LOG.error(e.getMessage(), e);
			} catch (UnauthorizedException e) {
				LOG.error(e.getMessage(), e);
			}
		}

	}

	public List<SecureZoneSelectionBean> getSecureZones() {
		return secureZones;
	}
	
	public SecureZoneSelectionBean getSelectedSecureZone(int index) {
		return this.secureZones.get(index);
	}

	public void setSecureZones(List<SecureZoneSelectionBean> speedLimits) {
		this.secureZones = speedLimits;
	}

	public void save() throws ValidationException {
		if (resp == null) {
			resp = PreventConnector.getLogin();
		}
		for (SecureZoneSelectionBean speedSelectionBean : secureZones) {
			try {
				XMLResponse setSpeed = PreventConnector.setVehicleSecureZone(resp, speedSelectionBean.getVehicle(), speedSelectionBean.getSelectedSecureZone());
				System.out.println(setSpeed.getResult());
				// TODO Capturar los errores SpeedLimitResponse slr = (SpeedLimitResponse)resp.getResult();
			} catch (HttpStatusException e) {
				LOG.error(e.getMessage(), e);
			} catch (InvalidResponseException e) {
				LOG.error(e.getMessage(), e);
			} catch (CommunicationException e) {
				LOG.error(e.getMessage(), e);
			} catch (UnauthorizedException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		
	}

}

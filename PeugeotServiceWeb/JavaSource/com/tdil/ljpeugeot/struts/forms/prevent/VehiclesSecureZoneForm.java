package com.tdil.ljpeugeot.struts.forms.prevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.prevent.XMLResponse;
import com.tdil.ljpeugeot.prevent.model.SecureZoneResponse;
import com.tdil.ljpeugeot.prevent.model.SecureZones;
import com.tdil.ljpeugeot.prevent.model.Vehicle;
import com.tdil.ljpeugeot.struts.forms.beans.SecureZoneSelectionBean;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
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
	public void initWith(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		super.initWith(user);
		vehicleIdToSecureZones = new HashMap<String, SecureZones>();
		secureZones = new ArrayList<SecureZoneSelectionBean>();
		try {
			basicinitWith(user);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicinitWith(user);
		}

	}

	private void basicinitWith(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException,
			UnauthorizedException {
		for (Vehicle vehicle : this.getVehicles()) {
				SecureZones limits = (SecureZones)PreventConnector.getVehicleSecureZones(user.getPreventLoginResponse(), vehicle).getResult();
				vehicleIdToSecureZones.put(vehicle.getId(), limits);
				secureZones.add(new SecureZoneSelectionBean(vehicle, limits));
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

	public boolean save() throws ValidationException, HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			return basicsave();
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			return basicsave();
		}
	}

	private boolean basicsave() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		boolean result = true;
		for (SecureZoneSelectionBean speedSelectionBean : secureZones) {
				XMLResponse setSpeed = PreventConnector.setVehicleSecureZone(getUser().getPreventLoginResponse(), speedSelectionBean.getVehicle(), speedSelectionBean.getSelectedSecureZone());
				SecureZoneResponse resp = (SecureZoneResponse)setSpeed.getResult();
				result = result && ("OK".equals(resp.getStatus()));
		}
		return result;
	}

}

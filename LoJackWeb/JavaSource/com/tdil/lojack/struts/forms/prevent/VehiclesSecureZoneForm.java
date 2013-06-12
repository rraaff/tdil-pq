package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.SecureZoneResponse;
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
		super.initWith(user);
		vehicleIdToSecureZones = new HashMap<String, SecureZones>();
		secureZones = new ArrayList<SecureZoneSelectionBean>();
		try {
			basicinitWith(user);
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			try {
				user.reloginPrevent();
				basicinitWith(user);
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

	public boolean save() throws ValidationException {
		try {
			return basicsave();
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			try {
				this.getUser().reloginPrevent();
				return basicsave();
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
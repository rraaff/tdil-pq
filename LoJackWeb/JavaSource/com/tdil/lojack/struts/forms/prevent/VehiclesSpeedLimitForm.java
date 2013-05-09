package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.model.SpeedLimits;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.struts.forms.beans.SpeedSelectionBean;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class VehiclesSpeedLimitForm extends VehiclesForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private Map<String, SpeedLimits> vehicleIdToSpeedLimit;
	private List<SpeedSelectionBean> speedLimits;

	private static Logger getLog() {
		return LoggerProvider.getLogger(VehiclesSpeedLimitForm.class);
	}

	@Override
	public void initWith(WebsiteUser user) {
		// TODO Auto-generated method stub
		super.initWith(user);
		if (resp == null) {
			resp = PreventConnector.getLogin();
		}
		vehicleIdToSpeedLimit = new HashMap<String, SpeedLimits>();
		speedLimits = new ArrayList<SpeedSelectionBean>();
		for (Vehicle vehicle : this.getVehicles()) {
			try {
				SpeedLimits limits = (SpeedLimits)PreventConnector.getVehicleSpeedLimit(resp, vehicle).getResult();
				vehicleIdToSpeedLimit.put(vehicle.getId(), limits);
				speedLimits.add(new SpeedSelectionBean(vehicle, limits));
			} catch (HttpStatusException e) {
				getLog().error(e.getMessage(), e);
			} catch (InvalidResponseException e) {
				getLog().error(e.getMessage(), e);
			} catch (CommunicationException e) {
				getLog().error(e.getMessage(), e);
			} catch (UnauthorizedException e) {
				getLog().error(e.getMessage(), e);
			}
		}

	}

	public List<SpeedSelectionBean> getSpeedLimits() {
		return speedLimits;
	}

	public void setSpeedLimits(List<SpeedSelectionBean> speedLimits) {
		this.speedLimits = speedLimits;
	}

}

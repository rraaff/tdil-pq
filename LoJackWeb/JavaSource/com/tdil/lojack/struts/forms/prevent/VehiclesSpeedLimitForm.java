package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.lojack.prevent.model.SpeedLimitResponse;
import com.tdil.lojack.prevent.model.SpeedLimits;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.struts.forms.beans.SpeedSelectionBean;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationException;
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

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(VehiclesSpeedLimitForm.class);
	private static Logger getLog() {
		return LoggerProvider.getLogger(VehiclesSpeedLimitForm.class);
	}

	@Override
	public void initWith(WebsiteUser user) {
		super.initWith(user);
		vehicleIdToSpeedLimit = new HashMap<String, SpeedLimits>();
		speedLimits = new ArrayList<SpeedSelectionBean>();
		try {
			basicinitWith(user);
		} catch (HttpStatusException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			getLog().error(e.getMessage(), e);
		} catch (CommunicationException e) {
			getLog().error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			try {
				user.reloginPrevent();
				basicinitWith(user);
			} catch (HttpStatusException e1) {
				getLog().error(e.getMessage(), e);
			} catch (InvalidResponseException e1) {
				getLog().error(e.getMessage(), e);
			} catch (CommunicationException e1) {
				getLog().error(e.getMessage(), e);
			} catch (UnauthorizedException e1) {
				getLog().error(e.getMessage(), e);
			}
		}

	}

	private void basicinitWith(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException,
			UnauthorizedException {
		for (Vehicle vehicle : this.getVehicles()) {
				SpeedLimits limits = (SpeedLimits)PreventConnector.getVehicleSpeedLimit(user.getPreventLoginResponse(), vehicle).getResult();
				vehicleIdToSpeedLimit.put(vehicle.getId(), limits);
				speedLimits.add(new SpeedSelectionBean(vehicle, limits));
		}
	}

	public List<SpeedSelectionBean> getSpeedLimits() {
		return speedLimits;
	}
	
	public SpeedSelectionBean getSelectedSpeedLimit(int index) {
		return this.speedLimits.get(index);
	}

	public void setSpeedLimits(List<SpeedSelectionBean> speedLimits) {
		this.speedLimits = speedLimits;
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
		for (SpeedSelectionBean speedSelectionBean : speedLimits) {
				XMLResponse setSpeed = PreventConnector.setVehicleSpeedLimit(getUser().getPreventLoginResponse(), speedSelectionBean.getVehicle(), speedSelectionBean.getSelectedSpeedLimit());
				SpeedLimitResponse resp = (SpeedLimitResponse)setSpeed.getResult();
				result = result && ("OK".equals(resp.getStatus()));
		}
		return result;
	}

}
package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.AsyncJobResponse;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordBean;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordResultBean;

public class ActivateAlarmForm extends AlarmForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017868774992458659L;
	
	private String password;
	private ValidatePasswordResultBean validatePasswordResultBean;

	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub
		password = null;
		validatePasswordResultBean = null;
	}

	@Override
	public void init() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ValidationError validate() {
		return new ValidationError();
	}

	@Override
	public void save() throws SQLException, ValidationException {
		try {
			validatePasswordResultBean = ThalamusClientBeanFacade.validatePassword(this.getSessionUser().getToken(), new ValidatePasswordBean(password));
			if (!validatePasswordResultBean.isOk()) {
				throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ActivateAlarm.ERR")));
			}
			Alarm alarm = new Alarm();
			alarm.setIdEntidad(idEntidad);
			if (getSessionUser().hasJobInProgress(alarm)) {
				throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ActivateAlarm.JOB_IN_PROGRESS")));
			} else {
				AsyncJobResponse jobResponse = LoJackServicesConnector.activateAlarm(getSessionUser(), idEntidad);
				if(jobResponse.getJobId() == 0) {
					throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ActivateAlarm.ERR")));
				} 
			}
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.HttpStatusException")));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.InvalidResponseException")));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.CommunicationException")));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.UnauthorizedException")));
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ValidatePasswordResultBean getValidatePasswordResultBean() {
		return validatePasswordResultBean;
	}

	public void setValidatePasswordResultBean(ValidatePasswordResultBean validatePasswordResultBean) {
		this.validatePasswordResultBean = validatePasswordResultBean;
	}


}

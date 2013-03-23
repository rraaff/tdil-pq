package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AlarmAlertConfiguration;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationException;

public class AlarmAlertConfigurationForm extends ExternalServiceForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private String alarmId;
	private boolean activateDeactivate;
	private boolean activateDeactivateByOther;
	private boolean powerSupplyLost;
	private boolean alarmActivation;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}
	
	@Override
	public int save() throws SQLException, ValidationException {
		// TODO Auto-generated method stub
		return 200;
	}

	public void initWith(WebsiteUser user, String alarmId) {
		setUser(user);
		setAlarmId(alarmId);
		AlarmAlertConfiguration conf = LoJackServicesConnector.getAlarmAlertConfiguration(String.valueOf(user.getId()), alarmId);
		this.setActivateDeactivate(conf.isActivateDeactivate());
		this.setActivateDeactivateByOther(conf.isActivateDeactivateByOther());
		this.setAlarmActivation(conf.isAlarmActivation());
		this.setPowerSupplyLost(conf.isPowerSupplyLost());
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public boolean isActivateDeactivate() {
		return activateDeactivate;
	}

	public void setActivateDeactivate(boolean activateDeactivate) {
		this.activateDeactivate = activateDeactivate;
	}

	public boolean isActivateDeactivateByOther() {
		return activateDeactivateByOther;
	}

	public void setActivateDeactivateByOther(boolean activateDeactivateByOther) {
		this.activateDeactivateByOther = activateDeactivateByOther;
	}

	public boolean isPowerSupplyLost() {
		return powerSupplyLost;
	}

	public void setPowerSupplyLost(boolean powerSupplyLost) {
		this.powerSupplyLost = powerSupplyLost;
	}

	public boolean isAlarmActivation() {
		return alarmActivation;
	}

	public void setAlarmActivation(boolean alarmActivation) {
		this.alarmActivation = alarmActivation;
	}

}

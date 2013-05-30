package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.LightAlertConfiguration;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationException;

public class LightAlertConfigurationForm extends ExternalServiceForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private String lightId;
	private boolean activateDeactivate;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}
	
	@Override
	public boolean save() throws SQLException, ValidationException {
		LightAlertConfiguration configuration = new LightAlertConfiguration();
		configuration.setUserId(String.valueOf(user.getId()));
		configuration.setLightId(this.getLightId());
		configuration.setActivateDeactivate(this.isActivateDeactivate());
		return LoJackServicesConnector.saveLightAlertConfiguration(user.getJSESSIONID(), configuration);
	}

	public void initWith(WebsiteUser user, String lightId) {
		setUser(user);
		setLightId(lightId);
		LightAlertConfiguration conf = LoJackServicesConnector.getLightAlertConfiguration(String.valueOf(user.getId()), lightId);
		this.setActivateDeactivate(conf.isActivateDeactivate());
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public String getLightId() {
		return lightId;
	}

	public void setLightId(String alarmId) {
		this.lightId = alarmId;
	}

	public boolean isActivateDeactivate() {
		return activateDeactivate;
	}

	public void setActivateDeactivate(boolean activateDeactivate) {
		this.activateDeactivate = activateDeactivate;
	}

}

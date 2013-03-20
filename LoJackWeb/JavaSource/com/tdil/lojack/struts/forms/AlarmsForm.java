package com.tdil.lojack.struts.forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.GISConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.utils.WebsiteUser;

public class AlarmsForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private Collection<Alarm> alarms;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user) {
		setUser(user);
		setAlarms(GISConnector.getAlarms(String.valueOf(user.getId())));
	}

	public Collection<Alarm> getAlarms() {
		return alarms;
	}


	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}
	
}

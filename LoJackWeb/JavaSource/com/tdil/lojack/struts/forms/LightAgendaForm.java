package com.tdil.lojack.struts.forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.GISConnector;
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.gis.model.LightAgenda;
import com.tdil.lojack.utils.WebsiteUser;

public class LightAgendaForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private String lightId;
	private Collection<LightAgenda> lightAgendas;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user, String alarmId) {
		setUser(user);
		setLightId(alarmId);
		// TODO setLightAgendas(GISConnector.getLightAgendas(alarmId));
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

	public void setLightId(String lightId) {
		this.lightId = lightId;
	}

	public Collection<LightAgenda> getLightAgendas() {
		return lightAgendas;
	}

	public void setLightAgendas(Collection<LightAgenda> lightAgendas) {
		this.lightAgendas = lightAgendas;
	}

	
}

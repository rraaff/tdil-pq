package com.tdil.lojack.struts.forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.utils.WebsiteUser;

public class AlarmAgendaForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private String alarmId;
	private Collection<AlarmAgenda> alarmAgendas;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user, String alarmId) {
		setUser(user);
		setAlarmId(alarmId);
		setAlarmAgendas(LoJackServicesConnector.getAlarmAgendas(alarmId));
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

	public Collection<AlarmAgenda> getAlarmAgendas() {
		return alarmAgendas;
	}

	public void setAlarmAgendas(Collection<AlarmAgenda> alarmAgendas) {
		this.alarmAgendas = alarmAgendas;
	}
	
}

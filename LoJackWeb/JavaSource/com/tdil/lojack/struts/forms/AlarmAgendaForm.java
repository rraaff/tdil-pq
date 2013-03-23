package com.tdil.lojack.struts.forms;

import java.util.ArrayList;
import java.util.List;

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
	
	private String id;
	private String description;
	private String from; // Fechas en formato YYYY-MM-DD
	private String to;
	private String type;
	private String customDays; //Sa,Do,Lu,Ma,Mi,Ju,Vi
	// Hora en formato HH en 00-24:MM:SS, ejemplo 10:30:00 18:30:00
	private String activateTime;
	private String deactivateTime;
	
	
	// TODO
	private List<AlarmAgenda> alarmAgendas;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user, String alarmId) {
		setUser(user);
		setAlarmId(alarmId);
		setAlarmAgendas(new ArrayList<AlarmAgenda>(LoJackServicesConnector.getAlarmAgendas(alarmId)));
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

	public List<AlarmAgenda> getAlarmAgendas() {
		return alarmAgendas;
	}

	public void setAlarmAgendas(List<AlarmAgenda> alarmAgendas) {
		this.alarmAgendas = alarmAgendas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomDays() {
		return customDays;
	}

	public void setCustomDays(String customDays) {
		this.customDays = customDays;
	}

	public String getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(String activateTime) {
		this.activateTime = activateTime;
	}

	public String getDeactivateTime() {
		return deactivateTime;
	}

	public void setDeactivateTime(String deactivateTime) {
		this.deactivateTime = deactivateTime;
	}

	public void edit(String id) {
		AlarmAgenda alarmAgenda = getAlarmAgenda(id);
		if (alarmAgenda != null) {
			this.setId(id);
			this.setDescription(alarmAgenda.getDescription());
			this.setFrom(alarmAgenda.getFrom());
			this.setTo(alarmAgenda.getTo());
			this.setType(alarmAgenda.getType());
			this.setCustomDays(alarmAgenda.getCustomDays());
			this.setActivateTime(alarmAgenda.getActivateTime());
			this.setDeactivateTime(alarmAgenda.getDeactivateTime());
		}
	}

	private AlarmAgenda getAlarmAgenda(String id) {
		for (AlarmAgenda agenda : this.getAlarmAgendas()) {
			if (agenda.getId().equals(id)) {
				return agenda;
			}
		}
		return null;
	}

	public void toggleActivation(String id) {
		AlarmAgenda alarmAgenda = getAlarmAgenda(id);
		if (alarmAgenda != null) {
			if (alarmAgenda.isActive()) {
				boolean result = LoJackServicesConnector.deleteAlarmAgenda(id);
				if (result) {
					alarmAgenda.setActive(false);
				}
			} else {
				boolean result = LoJackServicesConnector.activateAlarmAgenda(id);
				if (result) {
					alarmAgenda.setActive(true);
				}
			}
		}
		
	}
	
}

package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AgendaType;
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public class AlarmAgendaForm extends AgendaForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private String alarmId;
	
	private String id;
	
	// TODO
	private List<AlarmAgenda> alarmAgendas;
	
	public boolean isEdition() {
		return !org.apache.commons.lang.StringUtils.isEmpty(this.getId());
	}
	
	public void reset() {
		// TODO 
		super.reset();
		this.id = null;
	}

	public void initWith(WebsiteUser user, String alarmId) {
		setUser(user);
		setAlarmId(alarmId);
		setAlarmAgendas(new ArrayList<AlarmAgenda>(LoJackServicesConnector.getAlarmAgendas(user.getGuid(), alarmId)));
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
				boolean result = LoJackServicesConnector.deleteAlarmAgenda(user.getGuid(), id);
				if (result) {
					alarmAgenda.setActive(false);
				}
			} else {
				boolean result = LoJackServicesConnector.activateAlarmAgenda(user.getGuid(), id);
				if (result) {
					alarmAgenda.setActive(true);
				}
			}
		}
		
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO Auto-generated method stub
	}

	public void save() throws SQLException, ValidationException {
		AlarmAgenda alarmAgenda = new AlarmAgenda();
		alarmAgenda.setId(this.getId());
		alarmAgenda.setFrom(this.getFrom());
		alarmAgenda.setTo(this.getTo());
		alarmAgenda.setType(this.getType());
		if (AgendaType.CUSTOM.equals(this.getType())) {
			alarmAgenda.setCustomDays(this.getCustomDays());
		} else {
			alarmAgenda.setCustomDays("");
		}
		alarmAgenda.setActivateTime(this.getActivateTime());
		alarmAgenda.setDeactivateTime(this.getDeactivateTime());
		boolean saved = false;
		if (isEdition()) {
			saved = LoJackServicesConnector.saveAlarmAgenda(user.getGuid(), alarmAgenda);
		} else {
			saved = LoJackServicesConnector.addAlarmAgenda(user.getGuid(), alarmAgenda);
		}
		if (!saved) {
			// levantar una validation Exception
		} else {
			this.reset();
		}
	}
	
}

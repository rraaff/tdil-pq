package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AgendaType;
import com.tdil.lojack.gis.model.AlarmAgenda;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.validations.FieldValidation;

public class AlarmAgendaForm extends AgendaForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private int idEntidad;
	
	private String password;
	
	private int id;
	
	private List<AlarmAgenda> alarmAgendas;
	
	public boolean isEdition() {
		return this.getId() != 0;
	}
	
	public void reset() {
		super.reset();
		this.id = 0;
	}

	public void initWith(WebsiteUser user, int idEntidad) {
		setUser(user);
		setIdEntidad(idEntidad);
		setAlarmAgendas(new ArrayList<AlarmAgenda>(LoJackServicesConnector.getAlarmAgendas(user, idEntidad)));
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int alarmId) {
		this.idEntidad = alarmId;
	}

	public List<AlarmAgenda> getAlarmAgendas() {
		return alarmAgendas;
	}

	public void setAlarmAgendas(List<AlarmAgenda> alarmAgendas) {
		this.alarmAgendas = alarmAgendas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void edit(int id) {
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

	private AlarmAgenda getAlarmAgenda(int id) {
		for (AlarmAgenda agenda : this.getAlarmAgendas()) {
			if (agenda.getIdAgenda() == id) {
				return agenda;
			}
		}
		return null;
	}

	public void toggleActivation(int id) {
		AlarmAgenda alarmAgenda = getAlarmAgenda(id);
		if (alarmAgenda != null) {
			if (alarmAgenda.isActive()) {
				boolean result = LoJackServicesConnector.deleteAlarmAgenda(user, id);
				if (result) {
					alarmAgenda.setActive(false);
				}
			} else {
				boolean result = LoJackServicesConnector.activateAlarmAgenda(user, id);
				if (result) {
					alarmAgenda.setActive(true);
				}
			}
		}
		
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		if (AgendaType.ONE_DAY.equals(this.getType())) {
			if (StringUtils.isEmpty(this.getTo())) {
				this.setTo(this.getFrom());
			}
		}
		Date from = FieldValidation.validateDate(this.getFrom(), "from", "yyyy-MM-dd", true, validationError);
		Date to = FieldValidation.validateDate(this.getTo(), "to", "yyyy-MM-dd", true, validationError);
		if (from != null && to != null) {
			if (from.after(to)) {
				validationError.setFieldError("from", "AFTER_TO");
			}
			if (AgendaType.ONE_DAY.equals(this.getType()) && !from.equals(to)) {
				validationError.setFieldError("from", "one_day.DIFFERENT");
			}
		}
	}

	public void save() throws SQLException, ValidationException {
		AlarmAgenda alarmAgenda = new AlarmAgenda();
		alarmAgenda.setIdAgenda(this.getId());
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
			// TODO password contra thalamus...
			saved = LoJackServicesConnector.saveAlarmAgenda(user, alarmAgenda);
		} else {
			saved = LoJackServicesConnector.addAlarmAgenda(user, this.getIdEntidad(), alarmAgenda);
		}
		if (!saved) {
			// levantar una validation Exception
		} else {
			this.reset();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

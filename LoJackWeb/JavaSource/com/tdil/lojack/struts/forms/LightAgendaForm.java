package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AgendaType;
import com.tdil.lojack.gis.model.LightAgenda;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.validations.FieldValidation;

public class LightAgendaForm extends AgendaForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private int idEntidad;
	private int idLuz;
	
	private String id;
	private List<LightAgenda> lightAgendas;
	
		
	public boolean isEdition() {
		return !org.apache.commons.lang.StringUtils.isEmpty(this.getId());
	}
	
	public void reset() {
		super.reset();
		this.id = null;
	}

	public void initWith(WebsiteUser user, int idEntidad, int idLuz) {
		setUser(user);
		setIdLuz(idLuz);
		setLightAgendas(new ArrayList<LightAgenda>(LoJackServicesConnector.getLightAgendas(user, this.idEntidad, this.idLuz)));
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public int getIdLuz() {
		return idLuz;
	}

	public void setIdLuz(int lightId) {
		this.idLuz = lightId;
	}

	public List<LightAgenda> getLightAgendas() {
		return lightAgendas;
	}

	public void setLightAgendas(List<LightAgenda> lightAgendas) {
		this.lightAgendas = lightAgendas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void edit(String id) {
		LightAgenda lightAgenda = getLightAgenda(id);
		if (lightAgenda != null) {
			this.setId(id);
			this.setDescription(lightAgenda.getDescription());
			this.setFrom(lightAgenda.getFrom());
			this.setTo(lightAgenda.getTo());
			this.setType(lightAgenda.getType());
			this.setCustomDays(lightAgenda.getCustomDays());
			this.setActivateTime(lightAgenda.getActivateTime());
			this.setDeactivateTime(lightAgenda.getDeactivateTime());
		}
	}

	private LightAgenda getLightAgenda(String id) {
		for (LightAgenda agenda : this.getLightAgendas()) {
			if (agenda.getId().equals(id)) {
				return agenda;
			}
		}
		return null;
	}

	public void toggleActivation(String id) {
		LightAgenda lightAgenda = getLightAgenda(id);
		if (lightAgenda != null) {
			if (lightAgenda.isActive()) {
				boolean result = LoJackServicesConnector.deleteLightAgenda(user, id);
				if (result) {
					lightAgenda.setActive(false);
				}
			} else {
				boolean result = LoJackServicesConnector.activateLightAgenda(user, id);
				if (result) {
					lightAgenda.setActive(true);
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
		LightAgenda lightAgenda = new LightAgenda();
		lightAgenda.setId(this.getId());
		lightAgenda.setFrom(this.getFrom());
		lightAgenda.setTo(this.getTo());
		lightAgenda.setType(this.getType());
		lightAgenda.setCustomDays(this.getCustomDays());
		lightAgenda.setActivateTime(this.getActivateTime());
		lightAgenda.setDeactivateTime(this.getDeactivateTime());
		boolean saved = false;
		if (isEdition()) {
			saved = LoJackServicesConnector.saveLightAgenda(user, lightAgenda);
		} else {
			saved = LoJackServicesConnector.addLightAgenda(user, this.getIdLuz(), lightAgenda);
		}
		if (!saved) {
			// levantar una validation Exception
		} else {
			this.reset();
		}
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	
}

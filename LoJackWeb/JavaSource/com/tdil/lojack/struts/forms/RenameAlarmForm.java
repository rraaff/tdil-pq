package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.AlarmConf;
import com.tdil.lojack.model.AlarmConfExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public class RenameAlarmForm extends AlarmForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2556966768158490777L;
	
	private String description;
	
	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() throws SQLException {
		AlarmConfExample example = new AlarmConfExample();
		example.createCriteria().andIdentidadEqualTo(idEntidad).andIdwebsiteuserEqualTo(getSessionUser().getModelUser().getId());
		List<AlarmConf> result = DAOManager.getAlarmConfDAO().selectAlarmConfByExample(example);
		if (!result.isEmpty()) {
			setDescription(result.get(0).getDescription());
		} else {
			setDescription("");
		}
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
		AlarmConfExample example = new AlarmConfExample();
		example.createCriteria().andIdentidadEqualTo(idEntidad).andIdwebsiteuserEqualTo(getSessionUser().getModelUser().getId());
		List<AlarmConf> result = DAOManager.getAlarmConfDAO().selectAlarmConfByExample(example);
		if (result.isEmpty()) {
			// insert
			AlarmConf alarmConf = new AlarmConf();
			alarmConf.setIdentidad(idEntidad);
			alarmConf.setIdwebsiteuser(getSessionUser().getModelUser().getId());
			alarmConf.setDescription(description);
			alarmConf.setEmailnotification(0);
			alarmConf.setDeleted(0);
			DAOManager.getAlarmConfDAO().insertAlarmConf(alarmConf);
		} else {
			// update
			AlarmConf alarmConf = result.get(0);
			alarmConf.setDescription(description);
			DAOManager.getAlarmConfDAO().updateAlarmConfByPrimaryKeySelective(alarmConf);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

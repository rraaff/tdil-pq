package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.Collection;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public class AlarmLogForm extends AlarmForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8106005500772541630L;
	
	private Collection<ChangeLog> logCollection;

	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() throws SQLException {
		logCollection = LoJackServicesConnector.getAlarmLog(getSessionUser(), getIdEntidad());
	}

	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ValidationError validate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() throws SQLException, ValidationException {
		// TODO Auto-generated method stub

	}

	public Collection<ChangeLog> getLogCollection() {
		return logCollection;
	}

	public void setLogCollection(Collection<ChangeLog> logCollection) {
		this.logCollection = logCollection;
	}

}

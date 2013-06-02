package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.Collection;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public class SendPanicForm extends LoJackForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8106005500772541630L;
	
	private Collection<Alarm> alarms;
	
	private int sendPanicIdEntidad;

	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() throws SQLException {
		alarms = LoJackServicesConnector.getAlarms(this.getSessionUser());
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
	}


	public int getSendPanicIdEntidad() {
		return sendPanicIdEntidad;
	}

	public void setSendPanicIdEntidad(int sendPanicIdEntidad) {
		this.sendPanicIdEntidad = sendPanicIdEntidad;
	}

	public Collection<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}

}

package com.tdil.lojack.gis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

/**
 * Armada, Desarmada, Disparada
 * @author mgodoy
 *
 */
@XmlRootElement
public class Alarm implements Serializable {

	private static final long serialVersionUID = -1359406353418640290L;

	// campos del middleware
	private int idEntidad;
	private String status;
	private String lastChangeDate;
	private String lastChangeAction;
	private String lastChangeUser;
	private String lastChangeLojackUserID;

	// campos de alarm conf
	private int idAlarmConf;
	private String description;
	private boolean emailnotification;
	
	public static final String ACTIVE = "Armada";
	public static final String INACTIVE = "Desarmada";
	public static final String TRIGGERED = "Disparada";
	
	public Alarm() {
	}
	
	public boolean isActive() {
		return ACTIVE.equalsIgnoreCase(this.getStatus());
	}
	
	public boolean isInactive() {
		return INACTIVE.equalsIgnoreCase(this.getStatus());
	}
	
	public boolean isTriggered() {
		return TRIGGERED.equalsIgnoreCase(this.getStatus());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		if (StringUtils.isEmpty(description)) {
			description = "alarma " + String.valueOf(this.idEntidad);
		}
		return description;
	}

	public boolean hasChangeData() {
		return getLastChangeDate() != null;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public String getLastChangeAction() {
		return lastChangeAction;
	}

	public void setLastChangeAction(String lastChangeAction) {
		this.lastChangeAction = lastChangeAction;
	}

	public String getLastChangeUser() {
		return lastChangeUser;
	}

	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getLastChangeLojackUserID() {
		return lastChangeLojackUserID;
	}

	public void setLastChangeLojackUserID(String lastChangeLojackUserID) {
		this.lastChangeLojackUserID = lastChangeLojackUserID;
	}

	public int getIdAlarmConf() {
		return idAlarmConf;
	}

	public void setIdAlarmConf(int idAlarmConf) {
		this.idAlarmConf = idAlarmConf;
	}

	public boolean isEmailnotification() {
		return emailnotification;
	}

	public void setEmailnotification(boolean emailnotification) {
		this.emailnotification = emailnotification;
	}

}

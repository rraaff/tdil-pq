package com.tdil.peugeotservice.android.rest.model;

import java.io.Serializable;

/**
 * 0: Apagada
1: Encendida
2: Estado desconocido
3: Modo random habilitado
 * */
public class Light implements Serializable {

	private static final long serialVersionUID = -8954431125700653499L;

	private int idEntidad;
	private int idLuz;
	private int status;
	private String lastChangeDate;
	private String lastChangeAction;
	private String lastChangeUser;
	private String lastChangeLojackUserID;

	// campos de light conf
	private int idLightConf;
	private String description;
	private boolean emailnotification;

	public static final int OFF = 0;
	public static final int ON = 1;
	public static final int UNKNOWN = 2;
	public static final int RANDOM = 3;


	public Light() {
	}

	public boolean isInRandomMode() {
		return this.getStatus() == RANDOM;
	}

	public boolean isOff() {
		return this.getStatus() == OFF;
	}

	public boolean isOn() {
		return this.getStatus() == ON;
	}

	public boolean isStatusUnknown() {
		return this.getStatus() == UNKNOWN;
	}

	public String getStatusDescription() {
		if (this.getStatus() == RANDOM) {
			return "Modo random";
		} else {
			if (this.getStatus() == ON) {
				return "Encendida";
			} else {
				if (this.getStatus() == OFF) {
					return "Apagada";
				} else {
					return "Desconocido";
				}
			}
		}
	}

	public String getDescription() {
		if (description == null || description.length() == 0) {
			description = "luz " + String.valueOf(this.idEntidad) + "-" + String.valueOf(this.idLuz);
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdLuz() {
		return idLuz;
	}

	public void setIdLuz(int idLuz) {
		this.idLuz = idLuz;
	}

	public int getIdLightConf() {
		return idLightConf;
	}

	public void setIdLightConf(int idLightConf) {
		this.idLightConf = idLightConf;
	}

	public boolean isEmailnotification() {
		return emailnotification;
	}

	public void setEmailnotification(boolean emailnotification) {
		this.emailnotification = emailnotification;
	}

}

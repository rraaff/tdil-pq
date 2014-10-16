package com.tdil.thalamus.android.rest.model;

import java.io.Serializable;

public class LightAgenda implements Serializable {

	private static final long serialVersionUID = -822422216180257981L;
	
	public static final String ALL_DAYS = "ALL_DAYS";
	public static final String BUSINESS_DAYS = "BUSINESS_DAYS";
	public static final String WEEKENDS = "WEEKENDS";
	public static final String CUSTOM = "CUSTOM";
	public static final String Lu = "Lu";
	public static final String Ma = "Ma";
	public static final String Mi = "Mi";
	public static final String Ju = "Ju";
	public static final String Vi = "Vi";
	public static final String Sa = "Sa";
	public static final String Do = "Do";

	private String idAgenda;
	private String description;
	
	private String from; // Fechas en formato YYYY-MM-DD
	private String to;
	
	private boolean active;
	// Tipos validos seran ALL_DAYS, BUSINESS_DAYS, WEEKENDS, CUSTOM
	private String type;
	private String customDays; //Sa,Do,Lu,Ma,Mi,Ju,Vi
	
	// Hora en formato HH en 00-24:MM:SS, ejemplo 10:30:00 18:30:00
	private String activateTime;
	private String deactivateTime;
	
	private int idEntidad;
	private int idLuz;
	
	public String getTypeDescription() {
		if (CUSTOM.equals(this.getType())) {
			return this.getCustomDays();
		} else {
			return this.getType();
		}
	}
	
	public String getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(String id) {
		this.idAgenda = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCustomDays() {
		return customDays;
	}
	public void setCustomDays(String customDays) {
		this.customDays = customDays;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean monday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return true;
		}
		if (WEEKENDS.equals(this.getType())) {
			return false;
		}
		return this.getCustomDays().contains(Lu);
	}
	public boolean tuesday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return true;
		}
		if (WEEKENDS.equals(this.getType())) {
			return false;
		}
		return this.getCustomDays().contains(Ma);
	}
	public boolean wednesday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return true;
		}
		if (WEEKENDS.equals(this.getType())) {
			return false;
		}
		return this.getCustomDays().contains(Mi);
	}
	public boolean thursday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return true;
		}
		if (WEEKENDS.equals(this.getType())) {
			return false;
		}
		return this.getCustomDays().contains(Ju);
	}
	public boolean friday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return true;
		}
		if (WEEKENDS.equals(this.getType())) {
			return false;
		}
		return this.getCustomDays().contains(Vi);
	}
	public boolean saturday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return false;
		}
		if (WEEKENDS.equals(this.getType())) {
			return true;
		}
		return this.getCustomDays().contains(Sa);
	}
	public boolean sunday() {
		if (ALL_DAYS.equals(this.getType())) {
			return true;
		}
		if (BUSINESS_DAYS.equals(this.getType())) {
			return false;
		}
		if (WEEKENDS.equals(this.getType())) {
			return true;
		}
		return this.getCustomDays().contains(Do);
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdLuz() {
		return idLuz;
	}

	public void setIdLuz(int idLuz) {
		this.idLuz = idLuz;
	}


}

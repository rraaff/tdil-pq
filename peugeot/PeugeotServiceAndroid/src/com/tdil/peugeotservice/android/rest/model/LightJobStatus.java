package com.tdil.peugeotservice.android.rest.model;

public class LightJobStatus {

	private int idEntidad;
	private int idLuz;
	private String status;
	private boolean unknown;
	private boolean ran;
	private boolean on;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isUnknown() {
		return unknown;
	}
	public void setUnknown(boolean unknown) {
		this.unknown = unknown;
	}
	public boolean isRan() {
		return ran;
	}
	public void setRan(boolean ran) {
		this.ran = ran;
	}
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	
	
}

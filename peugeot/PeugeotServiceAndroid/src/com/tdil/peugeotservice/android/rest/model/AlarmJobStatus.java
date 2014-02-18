package com.tdil.peugeotservice.android.rest.model;

public class AlarmJobStatus {

	private int idEntidad;
	private String status;
	private boolean armada;
	
	public int getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isArmada() {
		return armada;
	}
	public void setArmada(boolean armada) {
		this.armada = armada;
	}
	
}

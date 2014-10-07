package com.tdil.thalamus.android.rest.model.parkedmode;

import java.io.Serializable;

public class ParkedModeHistoryLogBean implements Serializable {

	private static final long serialVersionUID = 4830779533951262329L;

	/* 1 activacion, 2 desactivacion, 3 movimiento indebido, 4 posicion actual*/
	private int idaccion;
	private String fecha;
	private String longitud;
	private String latitud;
	
	public ParkedModeHistoryLogBean() {
	}
	
	
	public int getIdaccion() {
		return idaccion;
	}

	public void setIdaccion(int idaccion) {
		this.idaccion = idaccion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
}

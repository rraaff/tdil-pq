package com.tdil.lojack.struts.forms;

public abstract class LightForm extends LoJackForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2855378418986892721L;
	
	protected int idEntidad;
	protected int idLuz;

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

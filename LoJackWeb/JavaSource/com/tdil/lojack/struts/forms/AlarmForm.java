package com.tdil.lojack.struts.forms;

public abstract class AlarmForm extends LoJackForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2855378418986892721L;
	
	protected int idEntidad;

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

}

package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.gis.model.LightAgenda;

@XmlRootElement
public class LightAgendaCollection {
	
	private Collection<LightAgenda> lightAgendas;
	
	public LightAgendaCollection() {
	}
	
	public LightAgendaCollection(Collection<LightAgenda> lightAgendas) {
		super();
		this.lightAgendas = lightAgendas;
	}

	public Collection<LightAgenda> getLightAgendas() {
		return lightAgendas;
	}

	public void setLightAgendas(Collection<LightAgenda> lightAgendas) {
		this.lightAgendas = lightAgendas;
	}




}

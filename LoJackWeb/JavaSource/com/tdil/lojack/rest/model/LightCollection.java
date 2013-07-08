package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.gis.model.Light;

@XmlRootElement
public class LightCollection {
	
	private Collection<Light> lights;
	
	public LightCollection() {
	}
	
	public LightCollection(Collection<Light> lights) {
		super();
		this.lights = lights;
	}



	public Collection<Light> getLights() {
		return lights;
	}

	public void setLights(Collection<Light> lights) {
		this.lights = lights;
	}

}

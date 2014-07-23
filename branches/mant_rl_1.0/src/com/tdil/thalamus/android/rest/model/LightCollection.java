package com.tdil.thalamus.android.rest.model;

import java.util.Collection;

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

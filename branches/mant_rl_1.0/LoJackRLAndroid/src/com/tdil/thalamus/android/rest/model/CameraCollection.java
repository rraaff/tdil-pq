package com.tdil.thalamus.android.rest.model;

import java.util.Collection;

public class CameraCollection {
	
	private Collection<Camera> cameras;
	
	public CameraCollection() {
	}
	
	public CameraCollection(Collection<Camera> cameras) {
		super();
		this.cameras = cameras;
	}



	public Collection<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(Collection<Camera> cameras) {
		this.cameras = cameras;
	}

}

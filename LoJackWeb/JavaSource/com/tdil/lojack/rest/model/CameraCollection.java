package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.gis.model.Camera;

@XmlRootElement
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

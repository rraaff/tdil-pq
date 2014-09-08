package com.tdil.thalamus.android.rest.model.poi;

import java.util.Collection;

public class PoiCollection {
	
	private Collection<PointOfInterestBean> list;
	
	public PoiCollection() {
	}
	
	public PoiCollection(Collection<PointOfInterestBean> pois) {
		super();
		this.list = pois;
	}



	public Collection<PointOfInterestBean> getList() {
		return list;
	}

	public void setList(Collection<PointOfInterestBean> pois) {
		this.list = pois;
	}

}

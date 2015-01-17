package com.tdil.peugeotservice.android.rest.model.prevent;

import java.util.Collection;

public class VehicleCollection {
	
	private Collection<VehicleBean> list;
	
	public VehicleCollection() {
	}
	
	public VehicleCollection(Collection<VehicleBean> pois) {
		super();
		this.list = pois;
	}



	public Collection<VehicleBean> getList() {
		return list;
	}

	public void setList(Collection<VehicleBean> pois) {
		this.list = pois;
	}

}

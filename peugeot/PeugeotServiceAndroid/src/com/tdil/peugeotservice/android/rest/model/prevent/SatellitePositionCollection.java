package com.tdil.peugeotservice.android.rest.model.prevent;

import java.util.Collection;

public class SatellitePositionCollection {
	
	private Collection<SatellitePositionBean> list;
	
	public SatellitePositionCollection() {
	}
	
	public SatellitePositionCollection(Collection<SatellitePositionBean> pois) {
		super();
		this.list = pois;
	}



	public Collection<SatellitePositionBean> getList() {
		return list;
	}

	public void setList(Collection<SatellitePositionBean> pois) {
		this.list = pois;
	}

}

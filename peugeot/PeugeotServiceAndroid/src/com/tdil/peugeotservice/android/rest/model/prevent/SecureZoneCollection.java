package com.tdil.peugeotservice.android.rest.model.prevent;

import java.util.Collection;

public class SecureZoneCollection {
	
	private Collection<SecureZoneBean> list;
	
	public SecureZoneCollection() {
	}
	
	public SecureZoneCollection(Collection<SecureZoneBean> pois) {
		super();
		this.list = pois;
	}



	public Collection<SecureZoneBean> getList() {
		return list;
	}

	public void setList(Collection<SecureZoneBean> pois) {
		this.list = pois;
	}

}

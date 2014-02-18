package com.tdil.peugeotservice.android.rest.model;

import java.util.Collection;

public class AddressTypeBeanCollection {
	
	private Collection<AddressTypeBean> list;
	
	public AddressTypeBeanCollection() {
	}
	
	public AddressTypeBeanCollection(Collection<AddressTypeBean> alarms) {
		super();
		this.list = alarms;
	}



	public Collection<AddressTypeBean> getList() {
		return list;
	}

	public void setList(Collection<AddressTypeBean> alarms) {
		this.list = alarms;
	}

}

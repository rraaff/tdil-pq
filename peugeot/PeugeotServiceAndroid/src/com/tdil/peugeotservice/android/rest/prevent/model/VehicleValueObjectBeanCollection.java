package com.tdil.peugeotservice.android.rest.prevent.model;

import java.util.List;

public class VehicleValueObjectBeanCollection {

	private List<VehicleValueObjectBean> list;
	
	public VehicleValueObjectBeanCollection() {
	}
	
	public VehicleValueObjectBeanCollection(List<VehicleValueObjectBean> list) {
		super();
		this.list = list;
	}

	public List<VehicleValueObjectBean> getList() {
		return list;
	}

	public void setList(List<VehicleValueObjectBean> list) {
		this.list = list;
	}
	
	
}

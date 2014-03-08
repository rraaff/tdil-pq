package com.tdil.peugeotservice.android.rest.prevent.model;

import java.util.List;

public class CityBeanCollection {

	private List<CityBean> list;
	
	public CityBeanCollection() {
	}
	
	public CityBeanCollection(List<CityBean> list) {
		super();
		this.list = list;
	}

	public List<CityBean> getList() {
		return list;
	}

	public void setList(List<CityBean> list) {
		this.list = list;
	}
	
	
}

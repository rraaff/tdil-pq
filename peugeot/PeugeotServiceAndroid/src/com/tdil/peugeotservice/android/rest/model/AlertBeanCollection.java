package com.tdil.peugeotservice.android.rest.model;

import java.util.List;

public class AlertBeanCollection {

	private List<AlertBean> list;
	
	public AlertBeanCollection() {
	}
	
	public AlertBeanCollection(List<AlertBean> list) {
		super();
		this.list = list;
	}

	public List<AlertBean> getList() {
		return list;
	}

	public void setList(List<AlertBean> list) {
		this.list = list;
	}
	
	
}

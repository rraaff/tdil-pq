package com.tdil.peugeotservice.android.rest.prevent.model;

import java.util.List;

public class StateBeanCollection {

	private List<StateBean> list;
	
	public StateBeanCollection() {
	}
	
	public StateBeanCollection(List<StateBean> list) {
		super();
		this.list = list;
	}

	public List<StateBean> getList() {
		return list;
	}

	public void setList(List<StateBean> list) {
		this.list = list;
	}
	
	
}

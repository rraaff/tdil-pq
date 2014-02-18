package com.tdil.peugeotservice.android.rest.model;

import java.util.Collection;

public class StateBeanCollection {
	
	private Collection<StateBean> list;
	
	public StateBeanCollection() {
	}
	
	public StateBeanCollection(Collection<StateBean> alarms) {
		super();
		this.list = alarms;
	}



	public Collection<StateBean> getList() {
		return list;
	}

	public void setList(Collection<StateBean> alarms) {
		this.list = alarms;
	}

}

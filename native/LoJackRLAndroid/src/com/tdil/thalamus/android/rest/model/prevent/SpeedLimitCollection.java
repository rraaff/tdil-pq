package com.tdil.thalamus.android.rest.model.prevent;

import java.util.Collection;

public class SpeedLimitCollection {
	
	private Collection<SpeedLimitBean> list;
	
	public SpeedLimitCollection() {
	}
	
	public SpeedLimitCollection(Collection<SpeedLimitBean> pois) {
		super();
		this.list = pois;
	}



	public Collection<SpeedLimitBean> getList() {
		return list;
	}

	public void setList(Collection<SpeedLimitBean> pois) {
		this.list = pois;
	}

}

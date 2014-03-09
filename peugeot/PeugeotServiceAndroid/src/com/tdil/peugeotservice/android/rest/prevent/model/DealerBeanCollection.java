package com.tdil.peugeotservice.android.rest.prevent.model;

import java.util.List;

public class DealerBeanCollection {

	private List<DealerBean> list;
	
	public DealerBeanCollection() {
	}
	
	public DealerBeanCollection(List<DealerBean> list) {
		super();
		this.list = list;
	}

	public List<DealerBean> getList() {
		return list;
	}

	public void setList(List<DealerBean> list) {
		this.list = list;
	}
	
	
}

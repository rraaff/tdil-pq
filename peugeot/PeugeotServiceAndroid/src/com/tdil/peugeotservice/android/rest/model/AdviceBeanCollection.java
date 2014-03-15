package com.tdil.peugeotservice.android.rest.model;

import java.util.List;

public class AdviceBeanCollection {

	private List<AdviceBean> list;
	
	public AdviceBeanCollection() {
	}
	
	public AdviceBeanCollection(List<AdviceBean> list) {
		super();
		this.list = list;
	}

	public List<AdviceBean> getList() {
		return list;
	}

	public void setList(List<AdviceBean> list) {
		this.list = list;
	}
	
	
}

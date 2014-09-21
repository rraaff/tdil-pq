package com.tdil.thalamus.android.rest.model.parkedmode;

import java.util.Collection;

public class HistoryLogBeanCollection {
	
	private Collection<HistoryLogBean> list;
	
	public HistoryLogBeanCollection() {
	}
	
	public HistoryLogBeanCollection(Collection<HistoryLogBean> alarms) {
		super();
		this.list = alarms;
	}



	public Collection<HistoryLogBean> getList() {
		return list;
	}

	public void setList(Collection<HistoryLogBean> alarms) {
		this.list = alarms;
	}

}

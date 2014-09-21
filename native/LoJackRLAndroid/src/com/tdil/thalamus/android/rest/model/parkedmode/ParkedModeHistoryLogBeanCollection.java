package com.tdil.thalamus.android.rest.model.parkedmode;

import java.io.Serializable;
import java.util.Collection;

public class ParkedModeHistoryLogBeanCollection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3819120720452351219L;
	private Collection<ParkedModeHistoryLogBean> list;
	
	public ParkedModeHistoryLogBeanCollection() {
	}
	
	public ParkedModeHistoryLogBeanCollection(Collection<ParkedModeHistoryLogBean> alarms) {
		super();
		this.list = alarms;
	}



	public Collection<ParkedModeHistoryLogBean> getList() {
		return list;
	}

	public void setList(Collection<ParkedModeHistoryLogBean> alarms) {
		this.list = alarms;
	}

}

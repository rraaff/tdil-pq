package com.tdil.thalamus.android.rest.model.parkedmode;

import java.io.Serializable;
import java.util.Collection;

public class ParkedModeStatusCollection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6699865267261173774L;
	private Collection<ParkedModeStatus> list;
	
	public ParkedModeStatusCollection() {
	}
	
	public ParkedModeStatusCollection(Collection<ParkedModeStatus> alarms) {
		super();
		this.list = alarms;
	}



	public Collection<ParkedModeStatus> getList() {
		return list;
	}

	public void setList(Collection<ParkedModeStatus> alarms) {
		this.list = alarms;
	}

}

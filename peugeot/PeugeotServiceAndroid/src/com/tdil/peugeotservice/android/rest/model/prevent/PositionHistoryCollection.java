package com.tdil.peugeotservice.android.rest.model.prevent;

import java.io.Serializable;
import java.util.Collection;

public class PositionHistoryCollection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8890772053404675690L;
	private Collection<PositionHistoryBean> list;
	
	public PositionHistoryCollection() {
	}
	
	public PositionHistoryCollection(Collection<PositionHistoryBean> pois) {
		super();
		this.list = pois;
	}



	public Collection<PositionHistoryBean> getList() {
		return list;
	}

	public void setList(Collection<PositionHistoryBean> pois) {
		this.list = pois;
	}

}

package com.tdil.thalamus.android.rest.model;

import java.io.Serializable;
import java.util.Collection;

public class NotificationBeanCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6054587577459445900L;
	private Collection<NotificationBean> list;
	
	public NotificationBeanCollection() {
	}
	
	public NotificationBeanCollection(Collection<NotificationBean> alarms) {
		super();
		this.list = alarms;
	}



	public Collection<NotificationBean> getList() {
		return list;
	}

	public void setList(Collection<NotificationBean> alarms) {
		this.list = alarms;
	}

}

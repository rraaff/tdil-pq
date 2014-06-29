package com.tdil.peugeotservice.android.rest.model;

import java.util.ArrayList;
import java.util.List;

public class RelationBean {

	private String key;
	private String value;
	
	public RelationBean(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public static List<RelationBean> allRelations() {
		List<RelationBean> result = new ArrayList<RelationBean>();
		result.add(new RelationBean("OWNER", "Titular del Servicio"));
		result.add(new RelationBean("RELATIVE", "Familiar"));
		result.add(new RelationBean("FRIEND", "Amigo"));
		result.add(new RelationBean("COWORKER", "Trabajo"));
		return result;
	}
	
	public static List<RelationBean> filteredRelations() {
		List<RelationBean> result = new ArrayList<RelationBean>();
		result.add(new RelationBean("RELATIVE", "Familiar"));
		result.add(new RelationBean("FRIEND", "Amigo"));
		result.add(new RelationBean("COWORKER", "Trabajo"));
		return result;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}

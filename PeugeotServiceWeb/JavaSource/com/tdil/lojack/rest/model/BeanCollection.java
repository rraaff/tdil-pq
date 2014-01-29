package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanCollection<T> {

	private Collection<T> list;
	
	public BeanCollection() {
	}
	
	public BeanCollection(Collection<T> list) {
		super();
		this.list = list;
	}

	public Collection<T> getList() {
		return list;
	}

	public void setList(Collection<T> list) {
		this.list = list;
	}
	
	
}

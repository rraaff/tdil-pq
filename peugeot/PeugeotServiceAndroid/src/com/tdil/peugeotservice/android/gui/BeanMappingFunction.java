package com.tdil.peugeotservice.android.gui;

public interface BeanMappingFunction<T> {

	public String key(T t);
	
	
	public String value(T t);
}

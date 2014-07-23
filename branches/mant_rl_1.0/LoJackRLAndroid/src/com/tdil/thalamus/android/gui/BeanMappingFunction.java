package com.tdil.thalamus.android.gui;

public interface BeanMappingFunction<T> {

	public String key(T t);
	
	
	public String value(T t);
}

package com.tdil.utils;

public class XMLAlias {
	
	private String alias;
	private Class aClass;
	
	public XMLAlias(String alias, Class aClass) {
		super();
		this.alias = alias;
		this.aClass = aClass;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Class getaClass() {
		return aClass;
	}
	public void setaClass(Class aClass) {
		this.aClass = aClass;
	}
	
}
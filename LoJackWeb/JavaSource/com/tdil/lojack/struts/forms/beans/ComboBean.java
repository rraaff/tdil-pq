package com.tdil.lojack.struts.forms.beans;

import java.io.Serializable;

public class ComboBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6443238617665160971L;
	private int parent;
	private int value;
	private String label;
	
	public ComboBean(int value, String label) {
		super();
		this.value = value;
		this.label = label;
	}
	
	public ComboBean(int value, String label, int parent) {
		super();
		this.value = value;
		this.label = label;
		this.parent = parent;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}
}

package com.tdil.tuafesta.model.valueobjects;

public class CategoryValueObject implements Comparable<CategoryValueObject>{

	public static int PRODUCT = 1;
	public static int SERVICE = 2;
	
	private int id;
	private String name;
	private int type;
	
	public int compareTo(CategoryValueObject o) {
		return this.getName().compareTo(o.getName());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}

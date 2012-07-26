package com.tdil.tuafesta.model.valueobjects;

import org.apache.commons.lang.StringUtils;

public class GeoLevelValueObject {

	private int id;
	
	private String nombre2;
	private String nombre3;
	private String nombre4;
	
	private int parentId;
	private int level;
	private int deleted;
	
	public String getNombre() {
		StringBuffer result = new StringBuffer();
		result.append(nombre2);
		if (nombre3 != null && !StringUtils.isEmpty(nombre3)) {
			result.append(" > ").append(nombre3);
		}
		if (nombre4 != null && !StringUtils.isEmpty(nombre4)) {
			result.append(" > ").append(nombre4);
		}
		return result.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getNombre3() {
		return nombre3;
	}
	public void setNombre3(String nombre3) {
		this.nombre3 = nombre3;
	}
	public String getNombre4() {
		return nombre4;
	}
	public void setNombre4(String nombre4) {
		this.nombre4 = nombre4;
	}
}

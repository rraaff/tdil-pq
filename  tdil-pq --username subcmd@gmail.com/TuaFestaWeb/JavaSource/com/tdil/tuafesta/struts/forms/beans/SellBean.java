package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;

import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.model.SellType;

public class SellBean implements Serializable {

	// TODO a esto le falta considerar que puede ser una edicion
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929326102200140850L;

	private int index;
	
	private int type;
	private int id;
	private String categoryText;
	private String name;
	private String referencePrice;
	
	public String getSellTypeDescription() {
		return ApplicationResources.getMessage(this.type == SellType.PRODUCT ? "PRODUCT" : "SERVICE");
	}
	
	public String getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(String referencePrice) {
		this.referencePrice = referencePrice;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryText() {
		return categoryText;
	}
	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

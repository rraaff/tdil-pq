package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;

public class ServiceBean implements Serializable {

// TODO a esto le falta considerar que puede ser una edicion
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6243798551288350376L;

	private int index;
	
	private int profesionalServiceId;
	private String serviceCategoryText;
	private String profesionalServiceText;
	private String referencePrice;
	
	public int getProfesionalServiceId() {
		return profesionalServiceId;
	}
	public void setProfesionalServiceId(int profesionalServiceId) {
		this.profesionalServiceId = profesionalServiceId;
	}
	public String getServiceCategoryText() {
		return serviceCategoryText;
	}
	public void setServiceCategoryText(String productCategoryText) {
		this.serviceCategoryText = productCategoryText;
	}
	public String getProfesionalServiceText() {
		return profesionalServiceText;
	}
	public void setProfesionalServiceText(String profesionalServiceText) {
		this.profesionalServiceText = profesionalServiceText;
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
}

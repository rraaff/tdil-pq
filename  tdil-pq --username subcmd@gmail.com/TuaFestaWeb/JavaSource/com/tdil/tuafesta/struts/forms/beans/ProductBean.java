package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;

public class ProductBean implements Serializable {

	// TODO a esto le falta considerar que puede ser una edicion
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929326102200140850L;

	private int index;
	
	private int profesionalProductId;
	private String productCategoryText;
	private String profesionalProductText;
	private String referencePrice;
	
	public int getProfesionalProductId() {
		return profesionalProductId;
	}
	public void setProfesionalProductId(int profesionalProductId) {
		this.profesionalProductId = profesionalProductId;
	}
	public String getProductCategoryText() {
		return productCategoryText;
	}
	public void setProductCategoryText(String productCategoryText) {
		this.productCategoryText = productCategoryText;
	}
	public String getProfesionalProductText() {
		return profesionalProductText;
	}
	public void setProfesionalProductText(String profesionalProductText) {
		this.profesionalProductText = profesionalProductText;
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

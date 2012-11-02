package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;

public class SearchSellBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2611624585392019299L;
	
	private String product;
	private String maxPrice;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
}

package com.tdil.tuafesta.model.valueobjects;

import com.tdil.tuafesta.model.Promotion;

public class PromotionValueObject extends Promotion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5640220824488547452L;
	
	private int firstImageid;
	private String firstImageExt;
	
	public int getFirstImageid() {
		return firstImageid;
	}
	public void setFirstImageid(int firstImageid) {
		this.firstImageid = firstImageid;
	}
	public String getFirstImageExt() {
		return firstImageExt;
	}
	public void setFirstImageExt(String firstImageExt) {
		this.firstImageExt = firstImageExt;
	}

}
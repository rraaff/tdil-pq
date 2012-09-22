package com.tdil.tuafesta.model.valueobjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionPhoto;
import com.tdil.tuafesta.struts.forms.beans.PublicImageBlobBean;

public class PromotionValueObject extends Promotion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5640220824488547452L;
	
	private List<SellValueObject> sells = new ArrayList<SellValueObject>();
	
	public PromotionValueObject() {
		// TODO Auto-generated constructor stub
	}
	
	public PromotionValueObject(Promotion promotion) {
		this.setId(promotion.getId());
		this.setName(promotion.getName());
		this.setDescription(promotion.getDescription());
		this.setStartdate(promotion.getStartdate());
		this.setEnddate(promotion.getEnddate());
		this.setPrice(promotion.getPrice());
	}
	public List<String> getUniqueProfesionals() {
		Set<Integer> added = new HashSet<Integer>();
		List<String> result = new ArrayList<String>();
		for (SellValueObject s : getSells()) {
			if (!added.contains(s.getIdProfesional())) {
				added.add(s.getIdProfesional());
				result.add(s.getProfesionalbusinessname());
			}
		}
		return result;
	}
	
	private int firstImageid;
	private String firstImageExt;
	
	private List<PromotionPhoto> allImages = new ArrayList<PromotionPhoto>();
	
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
	public List<SellValueObject> getSells() {
		return sells;
	}
	public void setSells(List<SellValueObject> sells) {
		this.sells = sells;
	}
	public List<PromotionPhoto> getAllImages() {
		return allImages;
	}
	public void setAllImages(List<PromotionPhoto> allImages) {
		this.allImages = allImages;
	}

}
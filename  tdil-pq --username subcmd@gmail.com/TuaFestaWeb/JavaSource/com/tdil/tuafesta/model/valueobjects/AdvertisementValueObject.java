package com.tdil.tuafesta.model.valueobjects;

import java.math.BigDecimal;

import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.model.AdType;
import com.tdil.tuafesta.model.Advertisement;

public class AdvertisementValueObject extends Advertisement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2849791323318107750L;
	private String profesionalBusinessName;
	private String profesionalDescription;
	private String sellName;
	private String sellDescription;
	private BigDecimal sellPrice;
	private String categoryName;

	public String getProfesionalBusinessName() {
		return profesionalBusinessName;
	}
	public String getTranslatedType() {
		return ApplicationResources.getMessage("AD_" + (this.getType().equals(AdType.NORMAL) ? "NORMAL" : "SUPER"));		
	}
	
	public void setProfesionalBusinessName(String profesionalBusinessName) {
		this.profesionalBusinessName = profesionalBusinessName;
	}
	
	public String getFormatedfromdate() {
		return com.tdil.utils.DateUtils.formatDateSp(this.getFromdate());
	}
	
	public String getFormatedtodate() {
		return com.tdil.utils.DateUtils.formatDateSp(this.getTodate());
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public boolean isProfesionalAd() {
		return this.getIdSell() == null || this.getIdSell() == 0;
	}

	public String getProfesionalDescription() {
		return profesionalDescription;
	}

	public void setProfesionalDescription(String profesionalDescription) {
		this.profesionalDescription = profesionalDescription;
	}
	
	public boolean hasImage() {
		return this.getIdBlobData() != null && this.getIdBlobData() != 0;
	}
	
	public String getSellDescription() {
		return sellDescription;
	}
	public void setSellDescription(String sellDescription) {
		this.sellDescription = sellDescription;
	}
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public String getProfesionalDescriptionUpTo(int chars) {
		if (this.getProfesionalDescription() == null) {
			return "";
		} else {
			if (this.getProfesionalDescription().length() > chars) {
				return this.getProfesionalDescription().substring(0, chars - 3) + "...";
			} else {
				return this.getProfesionalDescription();
			}
		}
	}
}

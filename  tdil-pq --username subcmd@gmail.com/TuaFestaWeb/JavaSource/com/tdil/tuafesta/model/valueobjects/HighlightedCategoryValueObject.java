package com.tdil.tuafesta.model.valueobjects;

import com.tdil.tuafesta.model.HighlightedCategory;
import com.tdil.tuafesta.utils.DateUtils;

public class HighlightedCategoryValueObject extends HighlightedCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6265303261384541874L;
	
	private String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getFormatedfromdate() {
		return DateUtils.formatDate(this.getFromdate());
	}
	
	public String getFormatedtodate() {
		return DateUtils.formatDate(this.getTodate());
	}
}

package com.tdil.struts.pagination;

import java.util.Comparator;

public abstract class CriterionComparator implements Comparator<Object> {

	private String criterion;
	private boolean asc;

	protected CriterionComparator() {
		super();
	}

	public String getCriterion() {
		return criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	
}

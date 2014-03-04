package com.tdil.web.breadcrum;

import org.apache.commons.lang.StringUtils;

public class BreadcrumItem {

	private String title;
	private String page;
	
	private boolean last;
	
	public BreadcrumItem(String title, String page) {
		super();
		this.title = title;
		this.page = page;
	}

	public String getTitle() {
		return title;
	}
	
	public boolean hasPage() {
		return !StringUtils.isEmpty(this.page) && !this.isLast();
	}

	public String getPage() {
		return page;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
	
}

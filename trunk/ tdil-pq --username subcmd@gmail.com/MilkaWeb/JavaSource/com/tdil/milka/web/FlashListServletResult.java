package com.tdil.milka.web;

import java.util.List;

public class FlashListServletResult<T> {

	private String rawInsert;
	private List<T> list;
	
	public FlashListServletResult(String rawInsert, List<T> list) {
		super();
		this.rawInsert = rawInsert;
		this.list = list;
	}

	public String getRawInsert() {
		return rawInsert;
	}
	
	public List<T> getList() {
		return list;
	}
	
}

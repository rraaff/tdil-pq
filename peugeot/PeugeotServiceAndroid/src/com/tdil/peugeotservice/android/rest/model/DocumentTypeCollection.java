package com.tdil.peugeotservice.android.rest.model;

import java.util.List;

public class DocumentTypeCollection {

	private List<DocumentTypeBean> list;
	
	public DocumentTypeCollection() {
	}
	
	public DocumentTypeCollection(List<DocumentTypeBean> list) {
		super();
		this.list = list;
	}

	public List<DocumentTypeBean> getList() {
		return list;
	}

	public void setList(List<DocumentTypeBean> list) {
		this.list = list;
	}
	
	
}

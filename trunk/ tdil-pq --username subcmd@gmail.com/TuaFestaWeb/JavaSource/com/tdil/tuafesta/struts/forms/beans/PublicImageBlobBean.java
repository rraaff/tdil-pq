package com.tdil.tuafesta.struts.forms.beans;

public class PublicImageBlobBean {

	private int blobid;
	private String blobExt;
	
	public PublicImageBlobBean(int blobid, String blobExt) {
		super();
		this.blobid = blobid;
		this.blobExt = blobExt;
	}
	
	public int getBlobid() {
		return blobid;
	}
	public void setBlobid(int blobid) {
		this.blobid = blobid;
	}
	public String getBlobExt() {
		return blobExt;
	}
	public void setBlobExt(String blobExt) {
		this.blobExt = blobExt;
	}
}

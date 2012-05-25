package com.tdil.struts.forms;

import java.io.Serializable;

public class UploadDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436118842744032161L;

	private int blobId;
	private String description;
	private String content;
	private UploadData uploadData;
	
	public UploadDataBean() {
		super();
	}
	
	public boolean getHasUploadData() {
		return this.getUploadData() != null;
	}
	
	public UploadData getUploadData() {
		return uploadData;
	}

	public void setUploadData(UploadData uploadData) {
		this.uploadData = uploadData;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBlobId() {
		return blobId;
	}

	public void setBlobId(int blobId) {
		this.blobId = blobId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

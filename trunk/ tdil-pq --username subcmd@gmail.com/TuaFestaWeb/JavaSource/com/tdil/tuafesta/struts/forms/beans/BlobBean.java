package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;

import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.model.BlobData;
public class BlobBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436118842744032161L;

	private int blobId;
	private UploadData uploadData;
	
	public BlobBean() {
		super();
	}

	public BlobBean(UploadData uploadData) {
		super();
		this.uploadData = uploadData;
	}
	
	public BlobBean(BlobData blob) {
		super();
		this.blobId = blob.getId();
		this.uploadData = new UploadData(blob.getFilename(), blob.getContent(), false);
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

	public int getBlobId() {
		return blobId;
	}

	public void setBlobId(int blobId) {
		this.blobId = blobId;
	}

}

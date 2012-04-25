package com.tdil.struts.forms;

public class UploadData {

	private boolean modified = false;
	private String fileName;
	private byte[] data;
	
	public UploadData(String fileName, byte data[], boolean modified) {
		this.setFileName(fileName);
		this.setData(data);
		this.setModified(modified);
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

}

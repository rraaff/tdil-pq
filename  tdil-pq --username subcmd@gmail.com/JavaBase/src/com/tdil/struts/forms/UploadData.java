package com.tdil.struts.forms;

public class UploadData {

	private String fileName;
	private byte[] data;
	
	public UploadData(String fileName, byte data[]) {
		this.setFileName(fileName);
		this.setData(data);
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
}

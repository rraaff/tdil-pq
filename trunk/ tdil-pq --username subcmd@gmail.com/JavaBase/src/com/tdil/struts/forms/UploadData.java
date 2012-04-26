package com.tdil.struts.forms;

import org.apache.commons.io.FilenameUtils;

public class UploadData {

	private boolean modified = false;
	private String fileName;
	private byte[] data;
	
	private static final int MAX_FILE_NAME_LENGTH = 100;
	
	public UploadData(String fileName, byte data[], boolean modified) {
		this.setFileName(fileName);
		this.setData(data);
		this.setModified(modified);
	}
	
	public String getExtension() {
		return FilenameUtils.getExtension(this.getFileName());
	}
	
	public String getFileName() {
		if (fileName != null) {
			int length = fileName.length();
			if (length > MAX_FILE_NAME_LENGTH) {
				int start = length - MAX_FILE_NAME_LENGTH - 1;
				fileName = fileName.substring(start, length - 1);
			}
		}
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

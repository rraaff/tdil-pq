package com.tdil.cache.blob;

import java.io.InputStream;

public class BlobLocalData {

	private String fileName;
	public BlobLocalData(String fileName, String localFileName, InputStream inputStream) {
		super();
		this.fileName = fileName;
		this.localFileName = localFileName;
		this.inputStream = inputStream;
	}
	private String localFileName;
	private InputStream inputStream;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLocalFileName() {
		return localFileName;
	}
	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}

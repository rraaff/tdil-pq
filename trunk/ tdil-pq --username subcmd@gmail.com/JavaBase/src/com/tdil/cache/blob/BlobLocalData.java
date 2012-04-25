package com.tdil.cache.blob;

import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;

public class BlobLocalData {

	private String fileName;
	private String localFileName;
	private InputStream inputStream;

	public BlobLocalData(String fileName, String localFileName, InputStream inputStream) {
		super();
		this.fileName = fileName;
		this.localFileName = localFileName;
		this.inputStream = inputStream;
	}
	
	public String getMimeType() {
		String contentType = new MimetypesFileTypeMap().getContentType(localFileName);
		return contentType;
	}
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
	
	@Override
	protected void finalize() throws Throwable {
		try {
			this.getInputStream().close();
		} catch (Exception e) {
			// only for safety
		}
	}
}

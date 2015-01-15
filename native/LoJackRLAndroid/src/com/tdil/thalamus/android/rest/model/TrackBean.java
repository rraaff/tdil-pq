package com.tdil.thalamus.android.rest.model;

public class TrackBean {

	private String title;
	private String url;
	
	public TrackBean(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

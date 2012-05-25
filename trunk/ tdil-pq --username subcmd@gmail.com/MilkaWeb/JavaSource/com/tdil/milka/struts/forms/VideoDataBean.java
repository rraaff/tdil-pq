package com.tdil.milka.struts.forms;

import com.tdil.struts.forms.UploadDataBean;

public class VideoDataBean extends UploadDataBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840871470988000118L;
	
	private String title;
	private String url;

	private int videoId;

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

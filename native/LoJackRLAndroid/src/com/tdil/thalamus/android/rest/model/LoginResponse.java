package com.tdil.thalamus.android.rest.model;

public class LoginResponse {

	private boolean logged;
	
	private String name;
	private String lojackUserId;
	
	private boolean homeUser;
	private String homeUserId;
	private boolean preventUser;
	private String preventUserId;
	private boolean petUser;
	private String petUserId;
	
	private long cameraRefreshTime;
	private String petVideo;
	private String petUrl;
	private String preventVideo;
	private String preventUrl;
	
	public LoginResponse() {
	}
	
	public static LoginResponse failed() {
		LoginResponse failed = new LoginResponse();
		failed.logged = false;
		return failed;
	}
	
	public boolean getLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLojackUserId() {
		return lojackUserId;
	}
	public void setLojackUserId(String lojackUserId) {
		this.lojackUserId = lojackUserId;
	}
	public boolean getHomeUser() {
		return homeUser;
	}
	public void setHomeUser(boolean isHomeUser) {
		this.homeUser = isHomeUser;
	}
	public String getHomeUserId() {
		return homeUserId;
	}
	public void setHomeUserId(String homeUserId) {
		this.homeUserId = homeUserId;
	}
	public boolean getPreventUser() {
		return preventUser;
	}
	public void setPreventUser(boolean isPreventUser) {
		this.preventUser = isPreventUser;
	}
	public String getPreventUserId() {
		return preventUserId;
	}
	public void setPreventUserId(String preventUserId) {
		this.preventUserId = preventUserId;
	}
	public boolean getPetUser() {
		return petUser;
	}
	public void setPetUser(boolean isPetUser) {
		this.petUser = isPetUser;
	}
	public String getPetUserId() {
		return petUserId;
	}
	public void setPetUserId(String petUserId) {
		this.petUserId = petUserId;
	}

	public long getCameraRefreshTime() {
		return cameraRefreshTime;
	}

	public void setCameraRefreshTime(long cameraRefreshTime) {
		this.cameraRefreshTime = cameraRefreshTime;
	}

	public String getPetVideo() {
		return petVideo;
	}

	public void setPetVideo(String petVideo) {
		this.petVideo = petVideo;
	}

	public String getPetUrl() {
		return petUrl;
	}

	public void setPetUrl(String petUrl) {
		this.petUrl = petUrl;
	}

	public String getPreventVideo() {
		return preventVideo;
	}

	public void setPreventVideo(String preventVideo) {
		this.preventVideo = preventVideo;
	}

	public String getPreventUrl() {
		return preventUrl;
	}

	public void setPreventUrl(String preventUrl) {
		this.preventUrl = preventUrl;
	}
}

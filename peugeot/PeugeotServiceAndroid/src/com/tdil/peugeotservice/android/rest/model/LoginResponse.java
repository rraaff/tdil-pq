package com.tdil.peugeotservice.android.rest.model;

public class LoginResponse {

	private boolean logged;
	
	private String name;
	private String lojackUserId;
	
	private String apkToken;
	
	private boolean servicesAdvices;
	
	private String avatar;
	
	
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

	public String getApkToken() {
		return apkToken;
	}

	public void setApkToken(String apkToken) {
		this.apkToken = apkToken;
	}

	public boolean getServicesAdvices() {
		return servicesAdvices;
	}

	public void setServicesAdvices(boolean servicesAdvices) {
		this.servicesAdvices = servicesAdvices;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	

}

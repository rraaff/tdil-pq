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
	
	private String apkToken;
	
	private long cameraRefreshTime;
	
	private String homeVideo;
	private String petVideo;
	private String petUrl;
	private String preventVideo;
	private String preventUrl;
	
	private boolean vluClient;
	private int vluMessages;
	
	private boolean clubLoJackUser;
	private int clubLoJackLevel;
	private String dni;
	private String clubLoJackUrlDestination;
	
	private String genericLinkDestination;
	
	public String getGenericLinkDestination() {
		return genericLinkDestination;
	}

	public void setGenericLinkDestination(String genericLinkDestination) {
		this.genericLinkDestination = genericLinkDestination;
	}
	
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

	public String getHomeVideo() {
		return homeVideo;
	}

	public void setHomeVideo(String homeVideo) {
		this.homeVideo = homeVideo;
	}

	public String getApkToken() {
		return apkToken;
	}

	public void setApkToken(String apkToken) {
		this.apkToken = apkToken;
	}
	
	public boolean getVluClient() {
		return vluClient;
	}

	public void setVluClient(boolean vluClient) {
		this.vluClient = vluClient;
	}

	public int getVluMessages() {
		return vluMessages;
	}

	public void setVluMessages(int vluMessages) {
		this.vluMessages = vluMessages;
	}

	public boolean setClubLoJackUser() {
		return clubLoJackUser;
	}

	public void setClubLoJackUser(boolean clubLoJackUser) {
		this.clubLoJackUser = clubLoJackUser;
	}

	public int getClubLoJackLevel() {
		return clubLoJackLevel;
	}

	public void setClubLoJackLevel(int clubLoJackLevel) {
		this.clubLoJackLevel = clubLoJackLevel;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getClubLoJackUrlDestination() {
		return clubLoJackUrlDestination;
	}

	public void setClubLoJackUrlDestination(String urlDestination) {
		this.clubLoJackUrlDestination = urlDestination;
	}

}

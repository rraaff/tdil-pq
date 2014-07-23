package com.tdil.thalamus.android.rest.model;

public class OptIn {

	private int brandFamilyId;
	private String brandFamilyName;
	
	private int channelId;
	private String channelName;
	
	private boolean accepted;
	
	public OptIn() {
		super();
	}
	
	public OptIn(int brandFamilyId, String brandFamilyName, int channelId,
			String channelName) {
		super();
		this.brandFamilyId = brandFamilyId;
		this.brandFamilyName = brandFamilyName;
		this.channelId = channelId;
		this.channelName = channelName;
	}

	public int getBrandFamilyId() {
		return brandFamilyId;
	}
	public void setBrandFamilyId(int brandFamilyId) {
		this.brandFamilyId = brandFamilyId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channel) {
		this.channelId = channel;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getBrandFamilyName() {
		return brandFamilyName;
	}

	public void setBrandFamilyName(String brandFamilyName) {
		this.brandFamilyName = brandFamilyName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}

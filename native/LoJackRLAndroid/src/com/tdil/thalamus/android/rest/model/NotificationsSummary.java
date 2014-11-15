package com.tdil.thalamus.android.rest.model;

public class NotificationsSummary {

	private int count;
	private int maxLevel;
	private boolean unread;
	
	public NotificationsSummary() {
	}
	
	public NotificationsSummary(int count, int maxLevel, boolean unread) {
		super();
		this.count = count;
		this.maxLevel = maxLevel;
		this.unread = unread;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}
}

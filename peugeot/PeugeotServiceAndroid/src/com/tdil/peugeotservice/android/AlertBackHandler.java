package com.tdil.peugeotservice.android;

import android.view.View;

public class AlertBackHandler {

	private boolean opened = false;
	private View openSendAlertView;
	private View sendAlertView;
	
	public AlertBackHandler(View openSendAlertView, View sendAlertView) {
		super();
		this.openSendAlertView = openSendAlertView;
		this.sendAlertView = sendAlertView;
	}


	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	public void close() {
		setOpened(false);
		openSendAlertView.setVisibility(View.VISIBLE);
		sendAlertView.setVisibility(View.GONE);
	}
}

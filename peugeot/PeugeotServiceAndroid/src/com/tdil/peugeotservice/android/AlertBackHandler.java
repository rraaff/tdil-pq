package com.tdil.peugeotservice.android;

import android.view.View;

public class AlertBackHandler {

	private boolean openedSendAlert = false;
	private boolean openedGuardPhoneCall = false;
	private View openSendAlertView;
	private View sendAlertView;
	
	private View openGuardPhoneCallView;
	private View sendGuardPhoneCallView;
	
	public AlertBackHandler(View openSendAlertView, View sendAlertView, View openGuardPhoneCallView, View sendGuardPhoneCallView) {
		super();
		this.openSendAlertView = openSendAlertView;
		this.sendAlertView = sendAlertView;
		
		this.openGuardPhoneCallView = openGuardPhoneCallView;
		this.sendGuardPhoneCallView = sendGuardPhoneCallView;
	}

	public boolean isOpened() {
		return isOpenedSendAlert() || isOpenedGuardPhoneCall();
	}

	public boolean isOpenedSendAlert() {
		return openedSendAlert;
	}

	public void setOpenedSendAlert(boolean opened) {
		this.openedSendAlert = opened;
	}
	
	public void close() {
		if (isOpenedSendAlert()) {
			setOpenedSendAlert(false);
			openSendAlertView.setVisibility(View.VISIBLE);
			sendAlertView.setVisibility(View.GONE);
		}
		if (isOpenedGuardPhoneCall()) {
			setOpenedGuardPhoneCall(false);
			openGuardPhoneCallView.setVisibility(View.VISIBLE);
			sendGuardPhoneCallView.setVisibility(View.GONE);
		}
	}


	public boolean isOpenedGuardPhoneCall() {
		return openedGuardPhoneCall;
	}


	public void setOpenedGuardPhoneCall(boolean openedGuardPhoneCall) {
		this.openedGuardPhoneCall = openedGuardPhoneCall;
	}
}

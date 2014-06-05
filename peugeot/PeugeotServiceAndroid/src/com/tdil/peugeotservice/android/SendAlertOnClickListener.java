package com.tdil.peugeotservice.android;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.view.View;

public class SendAlertOnClickListener implements View.OnClickListener {
	
	private Activity activity;
	private View openSendAlertView;
	private View sendAlertView;
private AlertBackHandler alertBackHandler;
	
	public SendAlertOnClickListener(Activity activity, View openSendAlertView,
			View sendAlertView, AlertBackHandler alertBackHandler) {
		super();
		this.activity = activity;
		this.openSendAlertView = openSendAlertView;
		this.sendAlertView = sendAlertView;
		this.alertBackHandler = alertBackHandler;
	}
	
	public void alertSent() {
		openSendAlertView.setVisibility(View.VISIBLE);
		sendAlertView.setVisibility(View.GONE);
		alertBackHandler.setOpened(false);
	}

	@Override
	public void onClick(View view) {
		double longitude = 0;
		double latitude = 0;
		String mPhoneNumber = "-";
		TelephonyManager tMgr = (TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tMgr != null) {
			mPhoneNumber = tMgr.getLine1Number();
		}
		if (mPhoneNumber == null || mPhoneNumber.equals("")) {
			mPhoneNumber = "-";
		}
		final LocationManager manager = (LocationManager) activity.getSystemService( Context.LOCATION_SERVICE );
		Location lastKnownLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (lastKnownLocation != null) {
			ServicesDashboardActivity.sendAlert(activity, lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude(), mPhoneNumber, this);
		} else {
			Location lastKnownLocation1 = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    	if (lastKnownLocation1 != null) {
	    		ServicesDashboardActivity.sendAlert(activity, lastKnownLocation1.getLongitude(), lastKnownLocation1.getLatitude(), mPhoneNumber, this);
	    	} else {
	    		ServicesDashboardActivity.sendAlert(activity, 0, 0, mPhoneNumber, this);
	    	}
		}
	}
}
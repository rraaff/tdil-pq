package com.tdil.peugeotservice.android;

import android.app.Activity;
import android.view.View;

import com.tdil.peugeotservice.R;

public class AlertLogic {

	public static void installLogic(final Activity activity) {
		
//		View home = activity.findViewById(R.id.btnFooterHome);
//		if (home!= null) {
//			home.setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						handleHomeAccess(activity, finishOnExit);
//					}
//				});
//		}
	
		final View openSendAlertView = activity.findViewById(R.id.openSendAlertView);
		final View sendAlertView = activity.findViewById(R.id.sendAlertView);
		
		final View sendAlertButton = activity.findViewById(R.id.sendAlertButton);
		final View closeSendAlertButton = activity.findViewById(R.id.closeSendAlertButton);
		if(openSendAlertView != null) {
			openSendAlertView.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						openSendAlertView.setVisibility(View.GONE);
						sendAlertView.setVisibility(View.VISIBLE);
					}
				});
		}
		if (sendAlertButton != null) {
			sendAlertButton.setOnClickListener(new SendAlertOnClickListener(activity));
		}
		if(closeSendAlertButton != null) {
			closeSendAlertButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						openSendAlertView.setVisibility(View.VISIBLE);
						sendAlertView.setVisibility(View.GONE);
					}
				});
		}
		
		
	}
}

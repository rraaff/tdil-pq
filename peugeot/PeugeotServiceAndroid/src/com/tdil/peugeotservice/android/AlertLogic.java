package com.tdil.peugeotservice.android;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.utils.Login;

public class AlertLogic {

	public static void installLogic(final PeugeotActivity activity) {
		
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
	
		
		final View guardPhoneCallButton = activity.findViewById(R.id.guardPhoneCallButton);
		if (guardPhoneCallButton != null) {
			guardPhoneCallButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
						 String uri = "tel:"+ Login.getLoggedUser(activity).getGuardPhone();
	                     Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
	                     activity.startActivity(callIntent);
						}catch(Exception e) {
		                    Toast.makeText(activity,"Ha ocurrido un error realizando la llamada...",
		                        Toast.LENGTH_LONG).show();
		                }

					}
				});
		}
		
		final View openSendAlertView = activity.findViewById(R.id.openSendAlertView);
		final View sendAlertView = activity.findViewById(R.id.sendAlertView);
		final View sendAlertButtonContainer = activity.findViewById(R.id.sendAlertButtonContainer);
		
		final View sendAlertButton = activity.findViewById(R.id.sendAlertButton);
		final View closeSendAlertButton = activity.findViewById(R.id.closeSendAlertButton);
		final AlertBackHandler alertBackHandler = new AlertBackHandler(openSendAlertView, sendAlertView);
		activity.setBackHandler(alertBackHandler);
		if(openSendAlertView != null) {
			openSendAlertView.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (Login.getLoggedUser(activity).getMustCompleteEmergencyData()) {
							activity.startActivity(new Intent(activity, UpdateEmergencyConfigActivity.class));
						} else {
							 alertBackHandler.setOpened(true);
							openSendAlertView.setVisibility(View.GONE);
	//						sendAlertView.setVisibility(View.VISIBLE);
							Animation rightToLeft = AnimationUtils.loadAnimation(activity, R.anim.opensendalert);
							sendAlertView.setVisibility(View.VISIBLE);
							sendAlertButtonContainer.startAnimation(rightToLeft);
						}
					}
				});
		}
		if (sendAlertButton != null) {
			sendAlertButton.setOnClickListener(new SendAlertOnClickListener(activity, openSendAlertView, sendAlertView, alertBackHandler));
		}
		if (sendAlertView != null) {
			sendAlertView.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						alertBackHandler.setOpened(false);
						openSendAlertView.setVisibility(View.VISIBLE);
						sendAlertView.setVisibility(View.GONE);
					}
				});
		}
		if(closeSendAlertButton != null) {
			closeSendAlertButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						alertBackHandler.setOpened(false);
						openSendAlertView.setVisibility(View.VISIBLE);
						sendAlertView.setVisibility(View.GONE);
					}
				});
		}
		
		
	}
}

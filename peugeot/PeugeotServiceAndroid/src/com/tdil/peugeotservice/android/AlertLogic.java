package com.tdil.peugeotservice.android;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.utils.Login;

public class AlertLogic {
	
	public static class EmergencySlideInAnimationListener implements AnimationListener {
		
		private int leftMove;
		private View sendAlertViewContainer;
		private View openSendAlertView;
		private Button mButton;
	    private LinearLayout.LayoutParams params;
	    
        public EmergencySlideInAnimationListener(int leftMove, View sendAlertViewContainer, View openSendAlertView,Button mButton,
				LayoutParams params) {
			super();
			this.leftMove = leftMove;
			this.sendAlertViewContainer = sendAlertViewContainer;
			this.openSendAlertView = openSendAlertView;
			this.mButton = mButton;
			this.params = params;
		}

		@Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        	LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 50;
            params.topMargin = sendAlertViewContainer.getTop();
            sendAlertViewContainer.setLayoutParams(params);
            
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = -leftMove;
            params.topMargin = 0;
            params.width = openSendAlertView.getWidth();
            params.height = openSendAlertView.getHeight();
            openSendAlertView.setLayoutParams(params);
//        	
//        	params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            params.leftMargin = 50+50;
//            params.topMargin = 0;
//            mButton.setLayoutParams(params);
        }

		public Button getmButton() {
			return mButton;
		}

		public void setmButton(Button mButton) {
			this.mButton = mButton;
		}

		public LinearLayout.LayoutParams getParams() {
			return params;
		}

		public void setParams(LinearLayout.LayoutParams params) {
			this.params = params;
		}
    };

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
	
		final View openGuardPhoneCallButton = activity.findViewById(R.id.openGuardPhoneCallButton);
		final View guardPhoneCallView = activity.findViewById(R.id.guardPhoneCallView);
		final View sendGuardPhoneCallButtonContainer = activity.findViewById(R.id.guardPhoneCallButtonContainer);
		
		final View guardPhoneCallButton = activity.findViewById(R.id.guardPhoneCallButton);
		final View closeGuardPhoneCallButton = activity.findViewById(R.id.closeGuardPhoneCallButton);
		
		final View openSendAlertView = activity.findViewById(R.id.openSendAlertView);

		final View sendAlertView = activity.findViewById(R.id.sendAlertView);
		final View sendAlertButtonContainer = activity.findViewById(R.id.sendAlertButtonContainer);
		
		final View sendAlertButton = activity.findViewById(R.id.sendAlertButton);
		final View closeSendAlertButton = activity.findViewById(R.id.closeSendAlertButton);
		final AlertBackHandler alertBackHandler = new AlertBackHandler(openSendAlertView, sendAlertView, openGuardPhoneCallButton, guardPhoneCallView);
		
		// new field
		final View openSendAlertView1 = activity.findViewById(R.id.sendAlertButton);
		
		activity.setBackHandler(alertBackHandler);
		
		if(openGuardPhoneCallButton != null) {
			openGuardPhoneCallButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						 alertBackHandler.setOpenedGuardPhoneCall(true);
						 openGuardPhoneCallButton.setVisibility(View.GONE);
//						sendAlertView.setVisibility(View.VISIBLE);
						Animation rightToLeft = AnimationUtils.loadAnimation(activity, R.anim.opensendalert);
						guardPhoneCallView.setVisibility(View.VISIBLE);
						sendGuardPhoneCallButtonContainer.startAnimation(rightToLeft);
					}
				});
		}
		if (guardPhoneCallButton != null) {
			guardPhoneCallButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							alertBackHandler.setOpenedSendAlert(false);
							openGuardPhoneCallButton.setVisibility(View.VISIBLE);
							guardPhoneCallView.setVisibility(View.GONE);
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
		if (guardPhoneCallView != null) {
			guardPhoneCallView.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						alertBackHandler.setOpenedGuardPhoneCall(false);
						openGuardPhoneCallButton.setVisibility(View.VISIBLE);
						guardPhoneCallView.setVisibility(View.GONE);
					}
				});
		}
		if(closeGuardPhoneCallButton != null) {
			closeGuardPhoneCallButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						alertBackHandler.setOpenedSendAlert(false);
						openGuardPhoneCallButton.setVisibility(View.VISIBLE);
						guardPhoneCallView.setVisibility(View.GONE);
					}
				});
		}
		
		if(openSendAlertView != null) {
			openSendAlertView.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (Login.getLoggedUser(activity).getMustCompleteEmergencyData()) {
							activity.startActivity(new Intent(activity, UpdateEmergencyConfigActivity.class));
						} else {
							alertBackHandler.setOpenedSendAlert(true);
							 openSendAlertView.setVisibility(View.GONE);
	//						sendAlertView.setVisibility(View.VISIBLE);
//							Animation rightToLeft = AnimationUtils.loadAnimation(activity, R.anim.opensendalert);
							sendAlertView.setVisibility(View.VISIBLE);
//							sendAlertButtonContainer.startAnimation(rightToLeft);
							
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
						alertBackHandler.setOpenedSendAlert(false);
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
						alertBackHandler.setOpenedSendAlert(false);
						openSendAlertView.setVisibility(View.VISIBLE);
						sendAlertView.setVisibility(View.GONE);
					}
				});
		}
		
		
		
	}
}

package com.tdil.thalamus.android;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.utils.Login;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class FooterLogic {

	
	public static void installFooterLogic(final Activity activity) {
		installFooterLogic(activity, true);
	}
	
	public static void installFooterLogic(final Activity activity, final boolean finishOnExit) {
		
		View sendPanic = activity.findViewById(R.id.goToSendPanicActivity);
		if (sendPanic != null) {
			sendPanic.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(activity, HomeAlarmsSendPanicActivity.class);
						activity.startActivity(intent);
			        	//finish();
					}
				});
		}
		
		activity.findViewById(R.id.btnFooterHome).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (Login.loggedUser.getHomeUser()) {
						Intent intent = new Intent(activity, HomeActivity.class); 
						activity.startActivity(intent); 
						if (finishOnExit) {
							activity.finish();
						}
					} else {
						String videoId = Login.loggedUser.getHomeVideo();
						playVideo(activity, videoId);
					}
				}
			});
		
		activity.findViewById(R.id.btnFooterPets).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (Login.loggedUser.getPetUser()) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.loggedUser.getPetUrl())); 
						activity.startActivity(intent); 
					} else {
						String videoId = Login.loggedUser.getPetVideo();
						playVideo(activity, videoId);
					}
				}

			});
		
		activity.findViewById(R.id.btnFooterPrevent).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (Login.loggedUser.getPreventUser()) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.loggedUser.getPreventUrl())); 
						activity.startActivity(intent); 
					} else {
						String videoId = Login.loggedUser.getPreventVideo();
						playVideo(activity, videoId);
					}
				}
			});
		
		View parkings = activity.findViewById(R.id.btnFooterParkings);
		if (parkings != null) {
			parkings.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lojack-app.com.ar/")); 
						activity.startActivity(intent); 
					}
				});
		}
	}
	
	public static void playVideo(final Activity activity, String videoId) {
		try{
			 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
			 activity.startActivity(intent);      
		}catch (ActivityNotFoundException ex){
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.youtube.com/watch?v="+videoId)); 
			//intent.putExtra("VIDEO_ID", videoId); 
			activity.startActivity(intent); 
		}
	}
}

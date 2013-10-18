package com.tdil.thalamus.android;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.utils.Login;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
//import android.widget.TextView;
import android.widget.Toast;

public class FooterLogic  {

	
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
		
		View home = activity.findViewById(R.id.btnFooterHome);
		if (home!= null) {
			home.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handleHomeAccess(activity, finishOnExit);
					}
				});
		}
		View pets = activity.findViewById(R.id.btnFooterPets);
		if (pets != null) {
			pets.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handlePetsAccess(activity);
					}
	
				});
		}
		
		View prevent = activity.findViewById(R.id.btnFooterPrevent);
		if(prevent != null) {
			prevent.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handlePreventAccess(activity);
					}
				});
		}
		
		View parkings = activity.findViewById(R.id.btnFooterParkings);
		if (parkings != null) {
			parkings.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handleParkingsAccess(activity); 
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

	public static void handlePreventAccess(final Activity activity) {
		if (Login.loggedUser.getPreventUser()) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.loggedUser.getPreventUrl())); 
			activity.startActivity(intent); 
		} else {
			String videoId = Login.loggedUser.getPreventVideo();
			playVideo(activity, videoId);
		}
	}

	public static void handleParkingsAccess(final Activity activity) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lojack-app.com.ar/")); 
		activity.startActivity(intent);
	}

	public static void handlePetsAccess(final Activity activity) {
		if (Login.loggedUser.getPetUser()) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.loggedUser.getPetUrl())); 
			activity.startActivity(intent); 
		} else {
			String videoId = Login.loggedUser.getPetVideo();
			playVideo(activity, videoId);
		}
	}

	public static void handleHomeAccess(final Activity activity,
			final boolean finishOnExit) {
		if (Login.loggedUser.getHomeUser()) {
			Intent intent = new Intent(activity, HomeAlarmsActivity.class); 
			activity.startActivity(intent); 
			if (finishOnExit) {
				activity.finish();
			}
		} else {
			String videoId = Login.loggedUser.getHomeVideo();
			playVideo(activity, videoId);
		}
	}
}





package com.tdil.peugeotservice.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.URLResponse;
import com.tdil.peugeotservice.android.utils.Login;
import com.tdil.peugeotservice.android.utils.Messages;

public class FooterLogic  {

	
	public static void installFooterLogic(final Activity activity) {
		installFooterLogic(activity, true);
	}
	
	public static void installFooterLogic(final Activity activity, final boolean finishOnExit) {
		
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
		if (Login.getLoggedUser(activity).getPreventUser()) {
			activity.startActivity(new Intent(activity, PreventActivity.class));
		} else {
			String videoId = Login.getLoggedUser(activity).getPreventVideo();
			playVideo(activity, videoId);
		}
	}
	
	public static void handlePeugeotAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ServicesDashboardActivity.class));
	}
	
	public static void handleParkingsAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ParkingsActivity.class));
	}

//
//	public static void handleHomeAccess(final Activity activity,
//			final boolean finishOnExit) {
//		if (Login.getLoggedUser(activity).getHomeUser()) {
//			Intent intent = new Intent(activity, HomeAlarmsActivity.class); 
//			activity.startActivity(intent); 
//			if (finishOnExit) {
//				activity.finish();
//			}
//		} else {
//			String videoId = Login.getLoggedUser(activity).getHomeVideo();
//			playVideo(activity, videoId);
//		}
//	}

	public static void handleTvAccess(final Activity activity) {
        Intent ljtvintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lojack.tv"));
        activity.startActivity(ljtvintent); 
	}
}





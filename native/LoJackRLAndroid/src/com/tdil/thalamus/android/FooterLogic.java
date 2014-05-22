package com.tdil.thalamus.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.URLResponse;
import com.tdil.thalamus.android.utils.Login;
import com.tdil.thalamus.android.utils.Messages;
//import android.view.Menu;
//import android.widget.TextView;

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
		View tv = activity.findViewById(R.id.btnFooterTV);
		if (tv != null) {
			tv.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handleTvAccess(activity); 
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

	public static void handleParkingsAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ParkingsActivity.class));
	}

	public static void handlePetsAccess(final Activity activity) {
		if (Login.getLoggedUser(activity).getPetUser()) {
			// Pido la url de login
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();

					URLResponse response = gson.fromJson(task.getResult(),
							URLResponse.class);
					if (response.getUrl() == null || response.getUrl().length() == 0) {
						Messages.connectionErrorMessage(activity);
					} else {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.getUrl())); 
						activity.startActivity(intent); 
					}
				}
				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.LOGIN_PETS, new RestParams(), null).execute((Void) null);
			
		} else {
			String videoId = Login.getLoggedUser(activity).getPetVideo();
			playVideo(activity, videoId);
		}
	}

	public static void handleHomeAccess(final Activity activity,
			final boolean finishOnExit) {
		if (Login.getLoggedUser(activity).getHomeUser()) {
			Intent intent = new Intent(activity, HomeAlarmsActivity.class); 
			activity.startActivity(intent); 
			if (finishOnExit) {
				activity.finish();
			}
		} else {
			String videoId = Login.getLoggedUser(activity).getHomeVideo();
			playVideo(activity, videoId);
		}
	}

	public static void handleTvAccess(final Activity activity) {
        Intent ljtvintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lojack.tv"));
        activity.startActivity(ljtvintent); 
	}

	public static void handleClubLoJackAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ClubLJActivity.class));
	}
}





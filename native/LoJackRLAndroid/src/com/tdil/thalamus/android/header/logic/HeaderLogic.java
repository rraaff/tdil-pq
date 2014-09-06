package com.tdil.thalamus.android.header.logic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ActivityCarsNotClient;
import com.tdil.thalamus.android.ActivityPetsNotClient;
import com.tdil.thalamus.android.ClubLJActivity;
import com.tdil.thalamus.android.ParkingsActivity;
import com.tdil.thalamus.android.PreventActivity;
import com.tdil.thalamus.android.home.ActivityHomeIndex;
import com.tdil.thalamus.android.home.ActivityHomeNotClient;
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

public class HeaderLogic  {

	
	public static void installTabLogic(final Activity activity) {
		installFooterLogic(activity, true);
	}
	
	public static void installFooterLogic(final Activity activity, final boolean finishOnExit) {
		
		/*
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
		*/
//		View homeTab = activity.findViewById(R.id.tabHomeUnSelected);
//		if (home!= null) {
//			home.setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						handleHomeAccess(activity, finishOnExit);
//					}
//				});
//		}
		
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
		
		installTopMenuLogic(activity);
		HomeHeaderLogic.installHomeMenuLogic(activity);
		
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
		
//		View tv = activity.findViewById(R.id.btnFooterTV);
//		if (tv != null) {
//			tv.setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						handleTvAccess(activity); 
//					}
//				});
//		}
	}

	private static void installTopMenuLogic(final Activity activity) {
		/* logica del menu superior */
		View outer = (View)activity.findViewById(R.id.application_fullmenu);
		if (outer != null) {
			View tabPets = outer.findViewById(R.id.tabPetsUnselected);
			if (tabPets != null) {
				tabPets.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							handlePetsAccess(activity);
						}
		
					});
			}
			View tabPrevent = activity.findViewById(R.id.tabCarsUnselected);
			if(tabPrevent != null) {
				tabPrevent.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							handlePreventAccess(activity);
						}
					});
			}
			View tabApps = activity.findViewById(R.id.tabAppsUnselected);
			if (tabApps != null) {
				tabApps.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							handleParkingsAccess(activity); 
						}
					});
			}
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
			Intent intent = new Intent(activity, ActivityCarsNotClient.class); 
			activity.startActivity(intent); 
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
			Intent intent = new Intent(activity, ActivityPetsNotClient.class); 
			activity.startActivity(intent); 
		}
	}

	public static void handleHomeAccess(final Activity activity,
			final boolean finishOnExit) {
		if (Login.getLoggedUser(activity).getHomeUser()) {
			Intent intent = new Intent(activity, ActivityHomeIndex.class); 
			activity.startActivity(intent); 
		} else {
			Intent intent = new Intent(activity, ActivityHomeNotClient.class); 
			activity.startActivity(intent); 
		}
		if (finishOnExit) {
			activity.finish();
		}
	}

	public static void handleTvAccess(final Activity activity) {
        Intent ljtvintent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.getLoggedUser(activity).getGenericLinkDestination()));
        activity.startActivity(ljtvintent); 
	}

	public static void handleClubLoJackAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ClubLJActivity.class));
	}
}





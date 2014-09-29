package com.tdil.thalamus.android.header.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ActivityRestClientObserver;
import com.tdil.thalamus.android.ClubLJActivity;
import com.tdil.thalamus.android.HomeAlarmsSendPanicActivity;
import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.car.ActivityCars;
import com.tdil.thalamus.android.car.ActivityCarsNotClient;
import com.tdil.thalamus.android.car.VLUMessagesActivity;
import com.tdil.thalamus.android.car.parkedmode.ParkedModeRestFacade;
import com.tdil.thalamus.android.home.ActivityHomeIndex;
import com.tdil.thalamus.android.home.ActivityHomeNotClient;
import com.tdil.thalamus.android.pets.ActivityPetsNotClient;
import com.tdil.thalamus.android.places.ActivityPlaces;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.RESTResponse;
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
		
		View openAlertCenter = activity.findViewById(R.id.openSendAlertView);
		if (openAlertCenter != null) {
			openAlertCenter.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						new RESTClientTaskOpt<AlarmCollection>(activity, HttpMethod.GET, getOpenSendAlertDialog(activity), 
								RESTConstants.ALARMS, null,null,AlarmCollection.class).execute((Void) null);
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

	public static IRestClientObserver getOpenSendAlertDialog(final Activity activity) {
		return new ActivityRestClientObserver(activity) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				AlarmCollection pos = ((RESTClientTaskOpt<AlarmCollection>)restClientTask).getCastedResult();
				openSendAlertDialog(activity, pos);
			}
		};
	}
	
	public static IRestClientObserver getPostSendAlertDialog(final Activity activity, final Dialog dialog) {
		return new ActivityRestClientObserver(activity) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					new AlertDialog.Builder(activity)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Alarms")
		               .setMessage("Se ha enviado la señal de panico")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   dialog.dismiss();
		                       }
		               }).show();
				} else {
					error(restClientTask);
				}
			}
		};
	}
	
	private static void openSendAlertDialog(final Activity activity, AlarmCollection pos) {
		final Dialog dialog = new Dialog(activity);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.alert_center_dialog);
//		dialog.setTitle("Seleccione un vehiculo");
		LinearLayout vehiclesLayout= (LinearLayout)dialog.findViewById(R.id.alertCenterButtonsContainer);
		for (Alarm alarm : pos.getAlarms()) {
			final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.vehicle_button, null);
			Button vehicleButton = (Button)layout.findViewById(R.id.vehicleButton);
			vehicleButton.setText(alarm.getDescription());
		    vehicleButton.setOnClickListener(new SendPanicSignalListener(alarm, activity, dialog));
		    vehiclesLayout.addView(layout);       
		}
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.alertCenterCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	/********* Called when Item click in ListView ************/
	private static class SendPanicSignalListener implements OnClickListener {
		private Alarm alarm;
		private Activity activity;
		private Dialog dialog;
		
		SendPanicSignalListener(Alarm alarm, Activity activity, Dialog dialog) {
			this.alarm = alarm;
			this.activity = activity;
			this.dialog = dialog;
		}

		@Override
		public void onClick(View arg0) {
			new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.GET, getPostSendAlertDialog(activity, dialog), 
					RESTConstants.SEND_PANIC_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())),null,RESTResponse.class).execute((Void) null);
		}
	}
	
	private static void installTopMenuLogic(final Activity activity) {
		/* logica del menu superior */
		View outer = (View)activity.findViewById(R.id.application_fullmenu);
		if (outer != null) {
			View tabHome = outer.findViewById(R.id.tabHomeUnselected);
			if (tabHome != null) {
				tabHome.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							handleHomeAccess(activity, true);
						}
					});
			}
			
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
			activity.startActivity(new Intent(activity, ActivityCars.class));
		} else {
			if (Login.getLoggedUser(activity).getVluClient()) {
				Intent intent = new Intent(activity.getBaseContext(), VLUMessagesActivity.class);
				intent.putExtra(VLUMessagesActivity.VLU_MESSAGES_COUNT, Login.getLoggedUser(activity).getVluMessages());
				activity.startActivity(intent);
			} else {
				Intent intent = new Intent(activity, ActivityCarsNotClient.class); 
				activity.startActivity(intent); 
			}
		}
	}

	public static void handleParkingsAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ActivityPlaces.class));
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
	
	public static void handleParkedModeAccess(final LoJackActivity activity) {
//		if (Login.getLoggedUser(activity).getPmUser()) {
			ParkedModeRestFacade.startParkedModeStatusActivity(activity);
//		} else {
//			Intent intent = new Intent(activity, ActivityParkedModeNotClient.class); 
//			activity.startActivity(intent); 
//		}
	}

	public static void handleTvAccess(final Activity activity) {
        Intent ljtvintent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.getLoggedUser(activity).getGenericLinkDestination()));
        activity.startActivity(ljtvintent); 
	}

	public static void handleClubLoJackAccess(final Activity activity) {
		activity.startActivity(new Intent(activity, ClubLJActivity.class));
	}
	
	
}





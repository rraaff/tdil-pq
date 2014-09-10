package com.tdil.thalamus.android.header.logic;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.deprecated.HomeLightDashboard;
import com.tdil.thalamus.android.home.ActivityHomeAlarmDashboard;
import com.tdil.thalamus.android.home.ActivityHomeAlarms;
import com.tdil.thalamus.android.home.ActivityHomeCamera;
import com.tdil.thalamus.android.home.ActivityHomeCameras;
import com.tdil.thalamus.android.home.ActivityHomeLights;
import com.tdil.thalamus.android.home.HomeActivity;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.rest.model.CameraCollection;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightCollection;
import com.tdil.thalamus.android.utils.Messages;

public class HomeHeaderLogic {

	public static void installHomeMenuLogic(final HomeActivity activity) {
		View application_home_submenu = (View)activity.findViewById(R.id.application_home_submenu);
		if (application_home_submenu != null) {
			TextView tabAlarmsUnselected = (TextView)activity.findViewById(R.id.tabAlarmsUnselected);
			if (tabAlarmsUnselected != null) {
				if (!activity.isAlarmsTab()) {
					tabAlarmsUnselected.setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								loadAlarms(activity);
							}
						});
				} else {
					// color de texto seleccionado
					tabAlarmsUnselected.setTextColor(activity.getResources().getColor(R.color.white));
				}
			}
			TextView tabLightsUnselected = (TextView)activity.findViewById(R.id.tabLightsUnselected);
			if (tabLightsUnselected != null) {
				if (!activity.isLightsTab()) {
					tabLightsUnselected.setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								loadLights(activity);
							}
						});
				} else {
					// color de texto seleccionado
					tabLightsUnselected.setTextColor(activity.getResources().getColor(R.color.white));
				}
			}
			TextView tabCamerasUnselected = (TextView)activity.findViewById(R.id.tabCamerasUnselected);
			if (tabCamerasUnselected != null) {
				if (!activity.isCamerasTab()) {
					tabCamerasUnselected.setOnClickListener(
							new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								loadCameras(activity);
							}
						});
				} else {
					// color de texto seleccionado
					tabCamerasUnselected.setTextColor(activity.getResources().getColor(R.color.white));
				}
			}
		}
	}
	
	public static void loadAlarms(final Activity activity) {
		new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AlarmCollection col = gson.fromJson(task.getResult(),
						AlarmCollection.class);
				ArrayList<Alarm> alarms = new ArrayList<Alarm>(col.getAlarms());
				if (col.getAlarms().size() == 1) {
					Intent intent = new Intent(activity.getBaseContext(), ActivityHomeAlarmDashboard.class);
					intent.putExtra(ActivityHomeAlarmDashboard.ALARM, col.getAlarms().iterator().next());
					intent.putExtra(ActivityHomeAlarmDashboard.HAS_MORE, "FALSE");
					activity.startActivity(intent);
					activity.finish();
				}  else {
					Intent intent = new Intent(activity.getBaseContext(), ActivityHomeAlarms.class);
					intent.putExtra(ActivityHomeAlarms.ALARMS, alarms);
					activity.startActivity(intent);
					activity.finish();
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(activity);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}

	public static void loadLights(final Activity activity) {
		new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				LightCollection col = gson.fromJson(task.getResult(),
						LightCollection.class);
				ArrayList<Light> ligths = new ArrayList<Light>(col.getLights());
				if (col.getLights().size() == 1) {
					Intent intent = new Intent(activity.getBaseContext(), HomeLightDashboard.class);
					intent.putExtra(HomeLightDashboard.LIGHT, col.getLights().iterator().next());
					activity.startActivity(intent);
					activity.finish();
				}  else {
					Intent intent = new Intent(activity.getBaseContext(), ActivityHomeLights.class);
					intent.putExtra(ActivityHomeLights.LIGHTS, ligths);
					activity.startActivity(intent);
					activity.finish();
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(activity);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
	
	public static void loadCameras(final Activity activity) {
		new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				CameraCollection col = gson.fromJson(task.getResult(),
						CameraCollection.class);
				ArrayList<Camera> cameras = new ArrayList<Camera>(col.getCameras());
				if (col.getCameras().size() == 1) {
					Intent intent = new Intent(activity.getBaseContext(), ActivityHomeCamera.class);
					intent.putExtra(ActivityHomeCamera.CAMERA, col.getCameras().iterator().next());
					intent.putExtra(ActivityHomeCamera.CAMERAS_COUNT, col.getCameras().size());
					activity.startActivity(intent);
					activity.finish();
				}  else {
					Intent intent = new Intent(activity.getBaseContext(), ActivityHomeCameras.class);
					intent.putExtra(ActivityHomeCameras.CAMERAS, cameras);
					activity.startActivity(intent);
					activity.finish();
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(activity);
			}
		}, RESTConstants.CAMERAS, null, null).execute((Void) null);
	}
}

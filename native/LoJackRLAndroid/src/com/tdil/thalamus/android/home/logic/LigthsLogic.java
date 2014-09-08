package com.tdil.thalamus.android.home.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.home.ActivityHomeLightLog;
import com.tdil.thalamus.android.home.ILightsActivity;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.AsyncJobResponse;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

public class LigthsLogic {

	public static void toggleLightActivation(final Activity activity, int mPosition) {
		final ILightsActivity lightsActivity = (ILightsActivity)activity;
		Light light = (Light) lightsActivity.getLight(mPosition);
		if (light.isOn()) {
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de apagado")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   lightsActivity.startLightsBackgroundJob();
			                       }
	
			               }).show();
					} else {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de apagado")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   lightsActivity.loadLights();
			                       }
	
			               }).show();
					}
				}
				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.DEACTIVATE_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		} else {
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de encendido")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   lightsActivity.startLightsBackgroundJob();
			                       }
			               }).show();
					} else {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de encendido")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
			               }).show();
					}
				}
				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.ACTIVATE_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		}
	}
	
	public static void toggleLightRandom(final Activity activity, int mPosition) {
		final ILightsActivity lightsActivity = (ILightsActivity)activity;
		Light light = (Light) lightsActivity.getLight(mPosition);
		if (light.isInRandomMode()) {
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de desactivacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   lightsActivity.startLightsBackgroundJob();
			                       }
	
			               }).show();
					} else {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de desactivacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
	
			               }).show();
					}
				}
				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.DEACTIVATE_RANDOM_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		} else {
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de activacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   lightsActivity.startLightsBackgroundJob();
			                       }
			               }).show();
					} else {
						new AlertDialog.Builder(activity)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de activacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
			               }).show();
					}
				}
				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.ACTIVATE_RANDOM_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		}
	}
	
	public static void startLightsBackgroundJob(final Activity activity) {
		final ILightsActivity lightsActivity = (ILightsActivity)activity;
		new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				LightJobStatusCollection col = gson.fromJson(task.getResult(),
						LightJobStatusCollection.class);
				if (!col.getStatus().isEmpty()) {
					lightsActivity.startLightsBackgroundJob();
				} else {
					lightsActivity.loadLights();
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(activity);
			}
		}, RESTConstants.LIGHT_STATUS, new RestParams(), null).execute();
	}
	
	public static void viewLightLog(Activity activity, int mPosition) {
		final ILightsActivity lightsActivity = (ILightsActivity)activity;
		Intent intent = new Intent(activity.getBaseContext(), ActivityHomeLightLog.class);
		Light alarm = lightsActivity.getLight(mPosition);
		intent.putExtra(ActivityHomeLightLog.IDENTIDAD, alarm.getIdEntidad());
		intent.putExtra(ActivityHomeLightLog.IDLUZ, alarm.getIdLuz());
		activity.startActivity(intent);
	}
}
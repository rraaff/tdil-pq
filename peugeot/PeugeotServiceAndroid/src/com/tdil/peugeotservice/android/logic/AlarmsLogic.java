package com.tdil.peugeotservice.android.logic;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.tdil.peugeotservice.android.IAlarmsActivity;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.Alarm;
import com.tdil.peugeotservice.android.rest.model.AsyncJobResponse;
import com.tdil.peugeotservice.android.utils.Messages;

public class AlarmsLogic {

	public static void toggleAlarmActivation(final Activity activity,int mPosition) {
		final IAlarmsActivity alarmsActivity = (IAlarmsActivity)activity;
		Alarm alarm = (Alarm) alarmsActivity.getAlarm(mPosition);
		if (alarm.isActive()) {
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(
							task.getResult(), AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(activity)
								.setIcon(R.drawable.ic_launcher)
								.setTitle("Alarms")
								.setMessage(
										"Se ha enviado el comando de desactivacion")
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												alarmsActivity.startAlarmsBackgroundJob();
											}

										}).show();
					} else {
						new AlertDialog.Builder(activity)
								.setIcon(R.drawable.ic_launcher)
								.setTitle("Alarms")
								.setMessage(
										"No ha podido enviarse el comando de desactivacion")
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												alarmsActivity.loadAlarms();
											}

										}).show();
					}
				}

				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.DEACTIVATE_ALARM, new RestParams(
					RESTConstants.ID_ENTIDAD, String.valueOf(alarm
							.getIdEntidad())), null).execute((Void) null);
		} else {
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(
							task.getResult(), AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(activity)
								.setIcon(R.drawable.ic_launcher)
								.setTitle("Alarms")
								.setMessage(
										"Se ha enviado el comando de activacion")
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												alarmsActivity.startAlarmsBackgroundJob();
											}
										}).show();
					} else {
						new AlertDialog.Builder(activity)
								.setIcon(R.drawable.ic_launcher)
								.setTitle("Alarms")
								.setMessage(
										"No ha podido enviarse el comando de activacion")
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
											}
										}).show();
					}
				}

				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(activity);
				}
			}, RESTConstants.ACTIVATE_ALARM, new RestParams(
					RESTConstants.ID_ENTIDAD, String.valueOf(alarm
							.getIdEntidad())), null).execute((Void) null);
		}
	}
}

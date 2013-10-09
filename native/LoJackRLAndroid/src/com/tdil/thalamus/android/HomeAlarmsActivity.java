package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.AlarmJobStatusCollection;
import com.tdil.thalamus.android.rest.model.AsyncJobResponse;
import com.tdil.thalamus.android.rest.model.LightJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeAlarmsActivity extends Activity {
	/**
	 * The default email to populate the email field with.
	 */

	private RESTClientTask mAuthTask = null;
	ListView list;
	AlarmListAdapter adapter;
	public HomeAlarmsActivity CustomListView = null;
	public ArrayList<Alarm> CustomListViewValuesArr = new ArrayList<Alarm>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_alarms);

		list = (ListView) findViewById(R.id.alarmsList);
		
		FooterLogic.installFooterLogic(this);
		
		loadAlarms();
	}

	public void loadAlarms() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();

				AlarmCollection col = gson.fromJson(task.getResult(),
						AlarmCollection.class);
				CustomListViewValuesArr = new ArrayList<Alarm>(col.getAlarms());
				Resources res = getResources();
				adapter = new AlarmListAdapter(HomeAlarmsActivity.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	public void onItemClick(int mPosition) {
		Alarm tempValues = (Alarm) CustomListViewValuesArr.get(mPosition);
		System.out.println(tempValues);
		/*
		 * Toast.makeText(CustomListView,
		 * ""+tempValues.getCompanyName()+" \nImage:"
		 * +tempValues.getImage()+" \nUrl:"+tempValues.getUrl(),
		 * Toast.LENGTH_LONG) .show();
		 */
	}
	
	public void toggleActivation(int mPosition) {
		Alarm alarm = (Alarm) CustomListViewValuesArr.get(mPosition);
		if (alarm.isActive()) {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(HomeAlarmsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Alarms")
			               .setMessage("Se ha enviado el comando de desactivacion")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   startBackgroundJob();
			                       }
	
			               }).show();
					} else {
						new AlertDialog.Builder(HomeAlarmsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Alarms")
			               .setMessage("No ha podido enviarse el comando de desactivacion")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
	
			               }).show();
					}
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeAlarmsActivity.this);
				}
			}, RESTConstants.DEACTIVATE_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
		} else {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(HomeAlarmsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Alarms")
			               .setMessage("Se ha enviado el comando de activacion")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   startBackgroundJob();
			                       }
			               }).show();
					} else {
						new AlertDialog.Builder(HomeAlarmsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Alarms")
			               .setMessage("No ha podido enviarse el comando de activacion")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
			               }).show();
					}
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeAlarmsActivity.this);
				}
			}, RESTConstants.ACTIVATE_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
		}
	}
	
	private void startBackgroundJob() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				AlarmJobStatusCollection col = gson.fromJson(task.getResult(),
						AlarmJobStatusCollection.class);
				if (!col.getStatus().isEmpty()) {
					HomeAlarmsActivity.this.startBackgroundJob();
				} else {
					HomeAlarmsActivity.this.loadAlarms();
				}
			}
			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.ALARM_STATUS, new RestParams(), null).execute();
	}
	
	public void viewAlarmLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogAlarmActivity.class);
		Alarm alarm = CustomListViewValuesArr.get(mPosition);
		intent.putExtra(HomeLogAlarmActivity.IDENTIDAD, alarm.getIdEntidad());
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.

	}

}

package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;

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

		list = (ListView) findViewById(R.id.alarmList);

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
				// TODO Auto-generated method stub

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
		System.out.println("toggleActivation" + alarm);
		if (alarm.isActive()) {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					new AlertDialog.Builder(HomeAlarmsActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Alarms")
		               .setMessage("Se ha enviado el comando de desactivacion")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   //this.
		                       }
		               }).show();
				}
				@Override
				public void error(RESTClientTask task) {
					// TODO Auto-generated method stub
				}
			}, RESTConstants.DEACTIVATE_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
		} else {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					new AlertDialog.Builder(HomeAlarmsActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Alarms")
		               .setMessage("Se ha enviado el comando de activacion")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   //HomeAlarmsActivity.this.finish();
		                       }
		               }).show();
				}
				@Override
				public void error(RESTClientTask task) {
					// TODO Auto-generated method stub
				}
			}, RESTConstants.ACTIVATE_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_go_home:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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

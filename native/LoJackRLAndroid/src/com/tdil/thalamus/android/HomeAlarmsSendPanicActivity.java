package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeAlarmsSendPanicActivity extends Activity {
	/**
	 * The default email to populate the email field with.
	 */

	private IRestClientTask mAuthTask = null;
	ListView list;
	AlarmSendPanicListAdapter adapter;
	public HomeAlarmsSendPanicActivity CustomListView = null;
	public ArrayList<Alarm> CustomListViewValuesArr = new ArrayList<Alarm>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_home_alarms_send_panic);

		list = (ListView) findViewById(R.id.alarmsList);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();

				AlarmCollection col = gson.fromJson(task.getResult(),
						AlarmCollection.class);
				CustomListViewValuesArr = new ArrayList<Alarm>(col.getAlarms());
				Resources res = getResources();
				adapter = new AlarmSendPanicListAdapter(HomeAlarmsSendPanicActivity.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsSendPanicActivity.this);
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

	public void sendPanic(int mPosition) {
		Alarm alarm = (Alarm) CustomListViewValuesArr.get(mPosition);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				// TODO analizar la respuesta para mostrar un mensaje u otro
				new AlertDialog.Builder(HomeAlarmsSendPanicActivity.this)
	               .setIcon(R.drawable.ic_launcher)
	               .setTitle("Alarms")
	               .setMessage("Se ha enviado la señal de panico")
	               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int whichButton) {
	                    	   HomeAlarmsSendPanicActivity.this.finish();
	                       }
	               }).show();
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsSendPanicActivity.this);
			}
		}, RESTConstants.SEND_PANIC_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
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

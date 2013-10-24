package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.logic.AlarmsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.AlarmJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeAlarmDashboard extends Activity implements IAlarmsActivity{

	private Alarm alarm;
	private HomeAlarmsActivity previous;
	private boolean ignore = true;
	
	public static final String ALARM = "ALARM";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_dashboard);
		Bundle extras = getIntent().getExtras();
		alarm = (Alarm)extras.getSerializable(ALARM);
		Switch activateDeactivate = (Switch)findViewById(R.id.btnToggleAlarm);
		activateDeactivate.setOnCheckedChangeListener(new ToggleActivateListener(this));
		View viewlog = findViewById(R.id.goToAlarmViewLog);
		viewlog.setOnClickListener(new ViewAlarmLogListener(this));
		init();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public void init() {
		Switch activateDeactivate = (Switch)findViewById(R.id.btnToggleAlarm);
		TextView description = (TextView) findViewById(R.id.alarmDescription);
		description.setText(alarm.getDescription());
		
		TextView status = (TextView) findViewById(R.id.alarmStatus);
		status.setText(alarm.getStatus());
		if (alarm.isActive()) {
			status.setTextColor(getResources().getColor(R.color.orangeDark));
		} else {
			status.setTextColor(getResources().getColor(R.color.gray));
		}
		TextView lastChange = (TextView) findViewById(R.id.lastChangeDate);
		lastChange.setText(alarm.getLastChangeDate());
		
		TextView lastChangeAction = (TextView) findViewById(R.id.lastChangeStatus);
		lastChangeAction.setText(alarm.getLastChangeAction());
		if (alarm.isActive()) {
			lastChangeAction.setTextColor(Color.GREEN);
		} else {
			lastChangeAction.setTextColor(Color.RED);
		}
		TextView lastChangeUser = (TextView) findViewById(R.id.lastChangeUser);
		lastChangeUser.setText(alarm.getLastChangeUser());

		activateDeactivate.setChecked(alarm.isActive());
		ignore = false;
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


	public void toggleAlarmActivation(int mPosition) {
		AlarmsLogic.toggleAlarmActivation(this, mPosition);
	}
	
	@Override
	public void loadAlarms() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				AlarmCollection col = gson.fromJson(task.getResult(),
						AlarmCollection.class);
				for (Alarm a : col.getAlarms()) {
					if (a.getIdEntidad() == HomeAlarmDashboard.this.alarm.getIdEntidad()) {
						HomeAlarmDashboard.this.alarm = a;
						HomeAlarmDashboard.this.init();
					}
				}
			}
			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmDashboard.this);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}
	
	@Override
	public Alarm getAlarm(int i) {
		return alarm;
	}

	public void startAlarmsBackgroundJob() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				AlarmJobStatusCollection col = gson.fromJson(task.getResult(),
						AlarmJobStatusCollection.class);
				if (!col.getStatus().isEmpty()) {
					HomeAlarmDashboard.this.startAlarmsBackgroundJob();
				} else {
					HomeAlarmDashboard.this.loadAlarms();
				}
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmDashboard.this);
			}
		}, RESTConstants.ALARM_STATUS, new RestParams(), null).execute();
	}

	public void viewAlarmLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogAlarmActivity.class);
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
	
	private class ToggleActivateListener implements OnCheckedChangeListener {
		private HomeAlarmDashboard activity;
		
		ToggleActivateListener(HomeAlarmDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!ignore) {
				activity.ignore = true;
				activity.toggleAlarmActivation(0);
			}
		}
	}
	
	private class ViewAlarmLogListener implements OnClickListener {
		private HomeAlarmDashboard activity;
		
		ViewAlarmLogListener(HomeAlarmDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			activity.viewAlarmLog(0);
		}
	}

}

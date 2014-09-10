package com.tdil.thalamus.android.deprecated;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.UnCaughtException;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.home.IAlarmsActivity;
import com.tdil.thalamus.android.home.logic.AlarmsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.AlarmJobStatus;
import com.tdil.thalamus.android.rest.model.AlarmJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@Deprecated
public class HomeAlarmDashboard extends LoJackActivity implements IAlarmsActivity{

	public static final String TAB_CAMARAS = "CAMARAS";
	public static final String TAB_LUCES = "LUCES";
	public static final String TAB_ALARMAS = "ALARMAS";
	
	private Alarm alarm;
	private boolean hasMore;
	private HomeAlarmsActivity previous;
	private boolean ignore = true;
	
	private int times = 0;
	private static int max_times = 10;
	private static long sleep = 2000;
	
	public static final String ALARM = "ALARM";
	public static final String HAS_MORE = "HAS_MORE";
	private ToggleButton activateDeactivate;
	
	private TabSpec tabCameras;
	private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_alarm_dashboard);
		customizeActionBar();
		Bundle extras = getIntent().getExtras();
		
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		
		TabSpec ts = tabHost.newTabSpec("tabAlarms");
		ts.setContent(R.id.tab1);
		ts.setIndicator(TAB_ALARMAS);
		tabHost.addTab(ts);

		TabSpec tabLights = tabHost.newTabSpec("tabLights");
		tabLights.setContent(R.id.tab2);
		tabLights.setIndicator(TAB_LUCES);
		tabHost.addTab(tabLights);

		tabCameras = tabHost.newTabSpec("tabCameras");
		tabCameras.setContent(R.id.tab3);
		tabCameras.setIndicator(TAB_CAMARAS);
		tabHost.addTab(tabCameras);
		
		alarm = (Alarm)extras.getSerializable(ALARM);
		
		hasMore = !"FALSE".equals((String)extras.getSerializable(HAS_MORE));
		
		activateDeactivate = (ToggleButton)findViewById(R.id.btnToggleAlarm);
		activateDeactivate.setOnCheckedChangeListener(new ToggleActivateListener(this));
		View viewlog = findViewById(R.id.goToAlarmViewLog);
		viewlog.setOnClickListener(new ViewAlarmLogListener(this));
		init();
		
		int numberOfTabs = tabHost.getTabWidget().getChildCount();
	    for(int t=0; t<numberOfTabs; t++){
	    	final int index = t;
	        tabHost.getTabWidget().getChildAt(t).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (index == 0) {
						if (hasMore) {
							Intent intent = new Intent(HomeAlarmDashboard.this, HomeAlarmsActivity.class);
							intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_ALARMAS);
							startActivity(intent);
							HomeAlarmDashboard.this.finish();
						}
					}
					if (index == 1) {
						Intent intent = new Intent(HomeAlarmDashboard.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_LUCES);
			        	startActivity(intent);
			        	HomeAlarmDashboard.this.finish();
					}
					if (index == 2) {
						Intent intent = new Intent(HomeAlarmDashboard.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_CAMARAS);
			        	startActivity(intent);
			        	HomeAlarmDashboard.this.finish();
					}
				}
			});
	    }
		
		HeaderLogic.installTabLogic(this);
	}

	public void init() {
		ToggleButton activateDeactivate = (ToggleButton)findViewById(R.id.btnToggleAlarm);
		TextView description = (TextView) findViewById(R.id.alarmDescription);
		description.setText(alarm.getDescription());
		
		TextView status = (TextView) findViewById(R.id.alarmStatus);
		status.setText(alarm.getStatus());
		if (alarm.isActive()) {
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
		} else {
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
		}
		TextView lastChange = (TextView) findViewById(R.id.lastChangeDate);
		lastChange.setText(alarm.getLastChangeDate());
		
		TextView lastChangeAction = (TextView) findViewById(R.id.lastChangeStatus);
		lastChangeAction.setText(alarm.getLastChangeAction());
		if (alarm.isActive()) {
			lastChangeAction.setTextColor(getResources().getColor(R.color.lst_itm_on));
		} else {
			lastChangeAction.setTextColor(getResources().getColor(R.color.lst_itm_off));
		}
		TextView lastChangeUser = (TextView) findViewById(R.id.lastChangeUser);
		lastChangeUser.setText(alarm.getLastChangeUser());

		activateDeactivate.setChecked(alarm.isActive());
		activateDeactivate.setEnabled(true);
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
			public void sucess(IRestClientTask task) {
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
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmDashboard.this);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}
	
	@Override
	public Alarm getAlarm(int i) {
		return alarm;
	}

	public void startAlarmsBackgroundJob() {
		activateDeactivate.setEnabled(false);
		times = 0;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AlarmJobStatusCollection col = gson.fromJson(task.getResult(),
						AlarmJobStatusCollection.class);
				boolean found = false;
				for (AlarmJobStatus jobStatus : col.getStatus()) {
					if (jobStatus.getIdEntidad() == HomeAlarmDashboard.this.alarm.getIdEntidad()) {
						found = true;
						if (jobStatus.isArmada()) {
							HomeAlarmDashboard.this.alarm.setStatus(Alarm.ACTIVE);
						} else {
							HomeAlarmDashboard.this.alarm.setStatus(Alarm.INACTIVE);
						}
					}
				}
				if (!found) {
					if (times < max_times) {
						times++;
						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {}
						HomeAlarmDashboard.this.startAlarmsBackgroundJob();
					} else {
						HomeAlarmDashboard.this.loadAlarms();
						HomeAlarmDashboard.this.ignore = false;
					}
				} else {
					HomeAlarmDashboard.this.init();
					HomeAlarmDashboard.this.ignore = false;
				}
			}

			@Override
			public void error(IRestClientTask task) {
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

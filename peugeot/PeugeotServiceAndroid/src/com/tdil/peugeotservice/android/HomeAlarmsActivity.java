package com.tdil.peugeotservice.android;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.logic.AlarmsLogic;
import com.tdil.peugeotservice.android.logic.LigthsLogic;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.Alarm;
import com.tdil.peugeotservice.android.rest.model.AlarmCollection;
import com.tdil.peugeotservice.android.rest.model.AlarmJobStatusCollection;
import com.tdil.peugeotservice.android.rest.model.Light;
import com.tdil.peugeotservice.android.rest.model.LightCollection;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeAlarmsActivity extends ActionBarActivity implements ILightsActivity, IAlarmsActivity {
	public static final String TAB_LUCES = "LUCES";
	public static final String TAB_ALARMAS = "ALARMAS";
	
	public static final String TAB_HOME = "HOME";
	
	private ListView alarmsList;
	private AlarmListAdapter alarmListAdapter;
	public ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	
	private ListView lightsList;
	private LightListAdapter lightsListAdapter;
	public ArrayList<Light> ligths = new ArrayList<Light>();
	
	public boolean alarmsLoaded = false;
	public boolean lightsLoaded = false;
	public boolean camerasLoaded = false;
	
	public static final String SELECTED_TAB = "SELECTED_TAB";
	private TabSpec tabCameras;
	private TabHost tabHost;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_home_alarms);
		
		String tab = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			tab = extras.getString(SELECTED_TAB);
		}

		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		
		TabSpec ts = tabHost.newTabSpec("tabAlarms");
		ts.setContent(R.id.alarmsList);
		ts.setIndicator(TAB_ALARMAS);
		tabHost.addTab(ts);

		TabSpec tabLights = tabHost.newTabSpec("tabLights");
		tabLights.setContent(R.id.lightsList);
		tabLights.setIndicator(TAB_LUCES);
		tabHost.addTab(tabLights);

		ts = tabHost.newTabSpec("tabHome");
		ts.setContent(R.id.homeContent);
		ts.setIndicator(TAB_HOME);
		tabHost.addTab(ts);
		tabHost.getTabWidget().getChildAt(3).setVisibility(View.GONE);

		alarmsList = (ListView) findViewById(R.id.alarmsList);
		lightsList = (ListView) findViewById(R.id.lightsList);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("tabAlarms")) {
					loadAlarms();
				}
				if (tabId.equals("tabLights")) {
					loadLights();
				}
			}
		});
		if (TAB_LUCES.equals(tab)) {
			tabHost.setCurrentTab(1);
			loadLights();
		} else {
			if (TAB_ALARMAS.equals(tab)) {
				tabHost.setCurrentTab(1);
				tabHost.setCurrentTab(0);
				loadAlarms();
			} else {
				tabHost.setCurrentTab(3);
			}
		}
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		FooterLogic.installFooterLogic(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		TabHost th = (TabHost) findViewById(R.id.tabhost);
		if (th.getCurrentTab() == 0) {
			loadAlarms();
		}
		if (th.getCurrentTab() == 1) {
			loadLights();
		}
	}

	public void loadAlarms() {
		alarmsLoaded = true;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AlarmCollection col = gson.fromJson(task.getResult(),
						AlarmCollection.class);
				alarms = new ArrayList<Alarm>(col.getAlarms());
				Resources res = getResources();
				alarmListAdapter = new AlarmListAdapter(HomeAlarmsActivity.this,
						alarms, res);
				alarmsList.setAdapter(alarmListAdapter);
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}
	
	public void loadLights() {
		lightsLoaded = true;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				LightCollection col = gson.fromJson(task.getResult(),
						LightCollection.class);
				ligths = new ArrayList<Light>(col.getLights());
				Resources res = getResources();
				lightsListAdapter = new LightListAdapter(HomeAlarmsActivity.this,
						ligths, res);
				lightsList.setAdapter(lightsListAdapter);
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
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
		Alarm tempValues = (Alarm) alarms.get(mPosition);
	}
	
	
	public void toggleLightActivation(int mPosition) {
		LigthsLogic.toggleLightActivation(this, mPosition);
	}
	@Override
	public void toggleLightRandom(int mPosition) {
		LigthsLogic.toggleLightRandom(this, mPosition);
	}
	@Override
	public void startLightsBackgroundJob() {
		LigthsLogic.startLightsBackgroundJob(this);
	}
	@Override
	public void viewLightLog(int mPosition) {
		LigthsLogic.viewLightLog(this, mPosition);
	}

	@Override
	public void toggleAlarmActivation(int mPosition) {
		AlarmsLogic.toggleAlarmActivation(this, mPosition);
	}

	public void startAlarmsBackgroundJob() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AlarmJobStatusCollection col = gson.fromJson(task.getResult(),
						AlarmJobStatusCollection.class);
				if (!col.getStatus().isEmpty()) {
					HomeAlarmsActivity.this.startAlarmsBackgroundJob();
				} else {
					HomeAlarmsActivity.this.loadAlarms();
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.ALARM_STATUS, new RestParams(), null).execute();
	}

	public void viewAlarmLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogAlarmActivity.class);
		Alarm alarm = alarms.get(mPosition);
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

	public ListView getAlarmsList() {
		return alarmsList;
	}

	public void setAlarmsList(ListView list) {
		this.alarmsList = list;
	}

	public AlarmListAdapter getAlarmListAdapter() {
		return alarmListAdapter;
	}

	public void setAlarmListAdapter(AlarmListAdapter adapter) {
		this.alarmListAdapter = adapter;
	}

	public List<Light> getLights() {
		return ligths;
	}
	
	@Override
	public Light getLight(int i) {
		return getLights().get(i);
	}

	public void setLigths(ArrayList<Light> ligths) {
		this.ligths = ligths;
	}

	public ArrayList<Alarm> getAlarms() {
		return alarms;
	}
	
	@Override
	public Alarm getAlarm(int i) {
		return getAlarms().get(i);
	}

	public void setAlarms(ArrayList<Alarm> alarms) {
		this.alarms = alarms;
	}

}

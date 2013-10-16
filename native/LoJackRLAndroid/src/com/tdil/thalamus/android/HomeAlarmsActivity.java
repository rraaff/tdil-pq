package com.tdil.thalamus.android;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.logic.AlarmsLogic;
import com.tdil.thalamus.android.logic.LigthsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.AlarmJobStatusCollection;
import com.tdil.thalamus.android.rest.model.AsyncJobResponse;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.rest.model.CameraCollection;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeAlarmsActivity extends Activity implements ILightsActivity, IAlarmsActivity {
	public static final String TAB_CAMARAS = "CAMARAS";
	public static final String TAB_LUCES = "LUCES";
	public static final String TAB_ALARMAS = "ALARMAS";
	
	private ListView alarmsList;
	private AlarmListAdapter alarmListAdapter;
	public ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	
	private ListView lightsList;
	private LightListAdapter lightsListAdapter;
	public ArrayList<Light> ligths = new ArrayList<Light>();
	
	private ListView cameraList;
	private CameraListAdapter cameraListAdapter;
	public ArrayList<Camera> cameras = new ArrayList<Camera>();
	
	public boolean alarmsLoaded = false;
	public boolean lightsLoaded = false;
	public boolean camerasLoaded = false;
	
	public static final String SELECTED_TAB = "SELECTED_TAB";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_alarms);
		
		String tab = TAB_ALARMAS;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			tab = extras.getString(SELECTED_TAB);
		}

		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		
		TabSpec ts = th.newTabSpec("tabAlarms");
		ts.setContent(R.id.alarmsList);
		ts.setIndicator(TAB_ALARMAS);
		th.addTab(ts);

		ts = th.newTabSpec("tabLights");
		ts.setContent(R.id.lightsList);
		ts.setIndicator(TAB_LUCES);
		th.addTab(ts);

		ts = th.newTabSpec("tabCameras");
		ts.setContent(R.id.camerasList);
		ts.setIndicator(TAB_CAMARAS);
		th.addTab(ts);

		th.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				System.out.println(tabId);
				if (tabId.equals("tabAlarms")) {
					if (!alarmsLoaded) {
						loadAlarms();
					}
				}
				if (tabId.equals("tabLights")) {
					if (!lightsLoaded) {
						loadLights();
					}
				}
				if (tabId.equals("tabCameras")) {
					if (!camerasLoaded) {
						loadCameras();
					}
				}
			}
		});
		if (TAB_CAMARAS.equals(tab)) {
			th.setCurrentTab(2);
			loadCameras();
		} else {
			if (TAB_LUCES.equals(tab)) {
				th.setCurrentTab(1);
				loadLights();
			} else {
				loadAlarms();
			}
		}
		alarmsList = (ListView) findViewById(R.id.alarmsList);
		lightsList = (ListView) findViewById(R.id.lightsList);
		cameraList = (ListView) findViewById(R.id.camerasList);
		
		FooterLogic.installFooterLogic(this);
	}

	public void loadAlarms() {
		alarmsLoaded = true;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
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
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}
	
	public void loadLights() {
		lightsLoaded = true;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
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
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
	
	public void loadCameras() {
		camerasLoaded = true;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();

				CameraCollection col = gson.fromJson(task.getResult(),
						CameraCollection.class);
				cameras = new ArrayList<Camera>(col.getCameras());
				Resources res = getResources();
				cameraListAdapter = new CameraListAdapter(HomeAlarmsActivity.this,
						cameras, res);
				cameraList.setAdapter(cameraListAdapter);
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeAlarmsActivity.this);
			}
		}, RESTConstants.CAMERAS, null, null).execute((Void) null);
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
		System.out.println(tempValues);
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
			public void sucess(RESTClientTask task) {
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
			public void error(RESTClientTask task) {
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

	@Override
	public List<Light> getLights() {
		return ligths;
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

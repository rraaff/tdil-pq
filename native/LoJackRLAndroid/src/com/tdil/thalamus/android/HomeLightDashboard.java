package com.tdil.thalamus.android;

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
import com.tdil.thalamus.android.logic.LigthsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightCollection;
import com.tdil.thalamus.android.rest.model.LightJobStatus;
import com.tdil.thalamus.android.rest.model.LightJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeLightDashboard extends LoJackActivity implements ILightsActivity{

	public static final String TAB_CAMARAS = "CAMARAS";
	public static final String TAB_LUCES = "LUCES";
	public static final String TAB_ALARMAS = "ALARMAS";
	
	private Light light;
	private boolean hasMore;
	private boolean ignore = true;
	private HomeLightsActivity previous;
	
	public static final String LIGHT = "LIGHT";
	public static final String HAS_MORE = "HAS_MORE";
	
	private int times = 0;
	private static int max_times = 10;
	private static long sleep = 2000;
	private ToggleButton activateDeactivateSwitch;
	private ToggleButton randomSwitch;
	
	private TabSpec tabCameras;
	private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light_dashboard);
		customizeActionBar();
		Bundle extras = getIntent().getExtras();
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
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
		
		tabHost.setCurrentTab(1);
		
		ToggleButton activateDeactivate = (ToggleButton)findViewById(R.id.switchActivate);
		activateDeactivate.setOnCheckedChangeListener(new ToggleActivateListener(this));
		ToggleButton random = (ToggleButton)findViewById(R.id.switchRandom);
		random.setOnCheckedChangeListener(new ToggleRandomListener(this));
		View viewlog = findViewById(R.id.goToViewLightLog);
		viewlog.setOnClickListener(new ViewLightLogListener(this));
		light = (Light)extras.getSerializable(LIGHT);
		hasMore = !"FALSE".equals((String)extras.getSerializable(HAS_MORE));
		init();
		
		int numberOfTabs = tabHost.getTabWidget().getChildCount();
	    for(int t=0; t<numberOfTabs; t++){
	    	final int index = t;
	        tabHost.getTabWidget().getChildAt(t).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (index == 0) {
						Intent intent = new Intent(HomeLightDashboard.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_ALARMAS);
			        	startActivity(intent);
			        	HomeLightDashboard.this.finish();
					}
					if (index == 1) {
						if (hasMore) {
							Intent intent = new Intent(HomeLightDashboard.this, HomeAlarmsActivity.class);
							intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_LUCES);
				        	startActivity(intent);
				        	HomeLightDashboard.this.finish();
						}
					}
					if (index == 2) {
						Intent intent = new Intent(HomeLightDashboard.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_CAMARAS);
			        	startActivity(intent);
			        	HomeLightDashboard.this.finish();
					}
				}
			});
	    }
		
		FooterLogic.installFooterLogic(this);
	}


	public void init() {
		ignore = true;
		activateDeactivateSwitch = (ToggleButton)findViewById(R.id.switchActivate);
		randomSwitch = (ToggleButton)findViewById(R.id.switchRandom);
		
		TextView description = (TextView) findViewById(R.id.lightDescription);
		description.setText(light.getDescription());
		
		TextView status = (TextView) findViewById(R.id.lightStatus);
		status.setText(light.getStatusDescription());
		if (light.isOn()) {
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
		} else {
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
		}
		TextView lastChange = (TextView) findViewById(R.id.lastChangeDate);
		lastChange.setText(light.getLastChangeDate());
		
		TextView lastChangeAction = (TextView) findViewById(R.id.lastChangeStatus);
		lastChangeAction.setText(light.getLastChangeAction());
		if (light.isOn()) {
			lastChangeAction.setTextColor(getResources().getColor(R.color.lst_itm_on));
		} else {
			lastChangeAction.setTextColor(getResources().getColor(R.color.lst_itm_off));
		}
		TextView lastChangeUser = (TextView) findViewById(R.id.lastChangeUser);
		lastChangeUser.setText(light.getLastChangeUser());

		activateDeactivateSwitch.setChecked(light.isOn());
		randomSwitch.setChecked(light.isInRandomMode());
		
		if (light.isInRandomMode()) {
			activateDeactivateSwitch.setEnabled(false);
		} else {
			activateDeactivateSwitch.setEnabled(true);
		}
		if (light.isOn()) {
			randomSwitch.setEnabled(false);
		} else {
			randomSwitch.setEnabled(true);
		}
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


	public void toggleLightActivation(int mPosition) {
		LigthsLogic.toggleLightActivation(this, mPosition);
	}
	
	public void toggleLightRandom(int mPosition) {
		LigthsLogic.toggleLightRandom(this, mPosition);
	}
	
	@Override
	public void loadLights() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				LightCollection col = gson.fromJson(task.getResult(),
						LightCollection.class);
				for (Light light : col.getLights()) {
					if (light.getIdEntidad() == HomeLightDashboard.this.light.getIdEntidad()) {
						if (light.getIdLuz() == HomeLightDashboard.this.light.getIdLuz()) {
							HomeLightDashboard.this.light = light;
							init();
						}
					}
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeLightDashboard.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
	
	public Light getLight(int i) {
		return light;
	}

	public void startLightsBackgroundJob() {
		activateDeactivateSwitch.setEnabled(false);
		randomSwitch.setEnabled(false);
		times = 0;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				LightJobStatusCollection col = gson.fromJson(task.getResult(),
						LightJobStatusCollection.class);
				boolean found = false;
				for (LightJobStatus jobStatus : col.getStatus()) {
					if (jobStatus.getIdEntidad() == HomeLightDashboard.this.light.getIdEntidad()) {
						if (jobStatus.getIdLuz() == HomeLightDashboard.this.light.getIdLuz()) {
							found = true;
							if (jobStatus.isRan()) {
								HomeLightDashboard.this.light.setStatus(Light.RANDOM);
							} else {
								if (jobStatus.isOn()) {
									HomeLightDashboard.this.light.setStatus(Light.ON);
								} else {
									if (jobStatus.isUnknown()) {
										HomeLightDashboard.this.light.setStatus(Light.UNKNOWN);
									} else {
										HomeLightDashboard.this.light.setStatus(Light.OFF);
									}
								}
							}
						}
					}
				}
				if (!found) {
					if (times < max_times) {
						times++;
						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {}
						HomeLightDashboard.this.startLightsBackgroundJob();
					} else {
						HomeLightDashboard.this.loadLights();
						HomeLightDashboard.this.ignore = false;
					}
				} else {
					HomeLightDashboard.this.init();
					HomeLightDashboard.this.ignore = false;
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeLightDashboard.this);
			}
		}, RESTConstants.LIGHT_STATUS, new RestParams(), null).execute();
	}

	public void viewLightLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogLightActivity.class);
		intent.putExtra(HomeLogLightActivity.IDENTIDAD, light.getIdEntidad());
		intent.putExtra(HomeLogLightActivity.IDLUZ, light.getIdLuz());
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
		private HomeLightDashboard activity;
		
		ToggleActivateListener(HomeLightDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!activity.ignore) {
				activity.ignore = true;
				activity.toggleLightActivation(0);
			}
		}
	}
	
	private class ToggleRandomListener implements OnCheckedChangeListener {
		private HomeLightDashboard activity;
		
		ToggleRandomListener(HomeLightDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!activity.ignore) {
				activity.ignore = true;
				activity.toggleLightRandom(0);
			}
		}
	}
	
	private class ViewLightLogListener implements OnClickListener {
		private HomeLightDashboard activity;
		
		ViewLightLogListener(HomeLightDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			activity.viewLightLog(0);
		}
	}

}

package com.tdil.thalamus.android.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.home.logic.LigthsLogic;
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
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeLightDashboard extends HomeActivity implements ILightsActivity {

	private Light light;
	private boolean hasMore;
	private boolean ignore = true;
	
	public static final String LIGHT = "LIGHT";
	public static final String HAS_MORE = "HAS_MORE";
	
	private int times = 0;
	private static int max_times = 10;
	private static long sleep = 2000;
	private ToggleButton activateDeactivateSwitch;
	private ToggleButton randomSwitch;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_lights_dashboard);
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Bundle extras = getIntent().getExtras();
		
		ToggleButton activateDeactivate = (ToggleButton)findViewById(R.id.switchActivate);
		activateDeactivate.setOnCheckedChangeListener(new ToggleActivateListener(this));
		ToggleButton random = (ToggleButton)findViewById(R.id.switchRandom);
		random.setOnCheckedChangeListener(new ToggleRandomListener(this));
		View viewlog = findViewById(R.id.goToViewLightLog);
		viewlog.setOnClickListener(new ViewLightLogListener(this));
		light = (Light)extras.getSerializable(LIGHT);
		hasMore = !"FALSE".equals((String)extras.getSerializable(HAS_MORE));
		View goToAgenda = findViewById(R.id.goToAgenda);
		goToAgenda.setOnClickListener(new GoToAgendaList(this));
		init();
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
	
	private static class GoToAgendaList implements OnClickListener {
		private ActivityHomeLightDashboard activity;
		
		GoToAgendaList(ActivityHomeLightDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			AgendasFacade.startLightAgendasListActivity(activity);
		}
	}
    
	public void toggleLightRandom(int mPosition) {
		LigthsLogic.toggleLightRandom(this, mPosition);
	}
	
	public void toggleLightActivation(int mPosition) {
		LigthsLogic.toggleLightActivation(this, mPosition);
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
					if (light.getIdEntidad() == ActivityHomeLightDashboard.this.light.getIdEntidad()) {
						if (light.getIdLuz() == ActivityHomeLightDashboard.this.light.getIdLuz()) {
							ActivityHomeLightDashboard.this.light = light;
							init();
						}
					}
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ActivityHomeLightDashboard.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
    
	public Light getLight(int i) {
		return light;
	}
    
	public void viewLightLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), ActivityHomeLightLog.class);
		intent.putExtra(ActivityHomeLightLog.IDENTIDAD, light.getIdEntidad());
		intent.putExtra(ActivityHomeLightLog.IDLUZ, light.getIdLuz());
		startActivity(intent);
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
					if (jobStatus.getIdEntidad() == ActivityHomeLightDashboard.this.light.getIdEntidad()) {
						if (jobStatus.getIdLuz() == ActivityHomeLightDashboard.this.light.getIdLuz()) {
							found = true;
							if (jobStatus.isRan()) {
								ActivityHomeLightDashboard.this.light.setStatus(Light.RANDOM);
							} else {
								if (jobStatus.isOn()) {
									ActivityHomeLightDashboard.this.light.setStatus(Light.ON);
								} else {
									if (jobStatus.isUnknown()) {
										ActivityHomeLightDashboard.this.light.setStatus(Light.UNKNOWN);
									} else {
										ActivityHomeLightDashboard.this.light.setStatus(Light.OFF);
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
						ActivityHomeLightDashboard.this.startLightsBackgroundJob();
					} else {
						ActivityHomeLightDashboard.this.loadLights();
						ActivityHomeLightDashboard.this.ignore = false;
					}
				} else {
					ActivityHomeLightDashboard.this.init();
					ActivityHomeLightDashboard.this.ignore = false;
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ActivityHomeLightDashboard.this);
			}
		}, RESTConstants.LIGHT_STATUS, new RestParams(), null).execute();
	}
    
    private class ToggleActivateListener implements OnCheckedChangeListener {
		private ActivityHomeLightDashboard activity;
		
		ToggleActivateListener(ActivityHomeLightDashboard activity) {
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
		private ActivityHomeLightDashboard activity;
		
		ToggleRandomListener(ActivityHomeLightDashboard activity) {
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
		private ActivityHomeLightDashboard activity;
		
		ViewLightLogListener(ActivityHomeLightDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			activity.viewLightLog(0);
		}
	}
	@Override
	public boolean isAlarmsTab() {
		return false;
	}
	@Override
	public boolean isCamerasTab() {
		return false;
	}
	@Override
	public boolean isLightsTab() {
		return true;
	}

	public Light getLight() {
		return light;
	}

	public void setLight(Light light) {
		this.light = light;
	}
}
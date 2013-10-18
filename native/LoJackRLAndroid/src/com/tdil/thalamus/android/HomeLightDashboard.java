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

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.logic.LigthsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightCollection;
import com.tdil.thalamus.android.rest.model.LightJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeLightDashboard extends Activity implements ILightsActivity{

	private Light light;
	private boolean ignore = true;
	private HomeLightsActivity previous;
	
	public static final String LIGHT = "LIGHT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light_dashboard);
		Bundle extras = getIntent().getExtras();
		Switch activateDeactivate = (Switch)findViewById(R.id.switchActivate);
		activateDeactivate.setOnCheckedChangeListener(new ToggleActivateListener(this));
		Switch random = (Switch)findViewById(R.id.switchRandom);
		random.setOnCheckedChangeListener(new ToggleRandomListener(this));
		View viewlog = findViewById(R.id.goToViewLightLog);
		viewlog.setOnClickListener(new ViewLightLogListener(this));
		light = (Light)extras.getSerializable(LIGHT);
		init();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}


	public void init() {
		Switch activateDeactivate = (Switch)findViewById(R.id.switchActivate);
		Switch random = (Switch)findViewById(R.id.switchRandom);
		
		TextView description = (TextView) findViewById(R.id.lightDescription);
		description.setText(light.getDescription());
		
		TextView status = (TextView) findViewById(R.id.lightStatus);
		status.setText(light.getStatusDescription());
		if (light.isOn()) {
			status.setTextColor(Color.GREEN);
		} else {
			status.setTextColor(Color.RED);
		}
		TextView lastChange = (TextView) findViewById(R.id.lastChangeDate);
		lastChange.setText(light.getLastChangeDate());
		
		TextView lastChangeAction = (TextView) findViewById(R.id.lastChangeStatus);
		lastChangeAction.setText(light.getLastChangeAction());
		if (light.isOn()) {
			lastChangeAction.setTextColor(Color.GREEN);
		} else {
			lastChangeAction.setTextColor(Color.RED);
		}
		TextView lastChangeUser = (TextView) findViewById(R.id.lastChangeUser);
		lastChangeUser.setText(light.getLastChangeUser());

		activateDeactivate.setChecked(light.isOn());
		random.setChecked(light.isInRandomMode());
		
		if (light.isInRandomMode()) {
			activateDeactivate.setEnabled(false);
		} else {
			activateDeactivate.setEnabled(true);
		}
		if (light.isOn()) {
			random.setEnabled(false);
		} else {
			random.setEnabled(true);
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
			public void sucess(RESTClientTask task) {
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
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeLightDashboard.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
	
	public Light getLight(int i) {
		return light;
	}

	public void startLightsBackgroundJob() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				LightJobStatusCollection col = gson.fromJson(task.getResult(),
						LightJobStatusCollection.class);
				if (!col.getStatus().isEmpty()) {
					HomeLightDashboard.this.startLightsBackgroundJob();
				} else {
					HomeLightDashboard.this.loadLights();
				}
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeLightDashboard.this);
			}
		}, RESTConstants.LIGHT_STATUS, new RestParams(), null).execute();
	}

	public void viewLightLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogLightActivity.class);
		intent.putExtra(HomeLogLightActivity.IDENTIDAD, light.getIdEntidad());
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

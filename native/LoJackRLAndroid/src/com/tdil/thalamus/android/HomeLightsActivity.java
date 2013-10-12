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

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.logic.LigthsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.AsyncJobResponse;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightCollection;
import com.tdil.thalamus.android.rest.model.LightJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeLightsActivity extends Activity implements ILightsActivity {
	/**
	 * The default email to populate the email field with.
	 */

	private ListView lightsList;
	private LightListAdapter lightsListAdapter;
	public ArrayList<Light> ligths = new ArrayList<Light>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_lights);
		lightsList = (ListView) findViewById(R.id.lightsList);
		loadLights();
	}

	public void loadLights() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();

				LightCollection col = gson.fromJson(task.getResult(),
						LightCollection.class);
				ligths = new ArrayList<Light>(col.getLights());
				Resources res = getResources();
				lightsListAdapter = new LightListAdapter(HomeLightsActivity.this,
						ligths, res);
				lightsList.setAdapter(lightsListAdapter);
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeLightsActivity.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
	
	@Override
	public void startLightsBackgroundJob() {
		LigthsLogic.startLightsBackgroundJob(this);
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
		Light tempValues = (Light) ligths.get(mPosition);
	}
	
	@Override
	public void toggleLightActivation(int mPosition) {
		LigthsLogic.toggleLightActivation(this, mPosition);
	}
	
	@Override
	public void toggleLightRandom(int mPosition) {
		LigthsLogic.toggleLightRandom(this, mPosition);
	}
	
	@Override
	public void viewLightLog(int mPosition) {
		LigthsLogic.viewLightLog(this, mPosition);
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

	@Override
	public List<Light> getLights() {
		return ligths;
	}

	public void setLigths(ArrayList<Light> ligths) {
		this.ligths = ligths;
	}

}

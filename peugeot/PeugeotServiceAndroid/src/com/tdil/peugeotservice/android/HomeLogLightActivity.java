package com.tdil.peugeotservice.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.ChangeLog;
import com.tdil.peugeotservice.android.rest.model.LogCollection;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeLogLightActivity extends Activity {
	/**
	 * The default email to populate the email field with.
	 */
	private int identidad;
	private int idluz;
	private IRestClientTask mAuthTask = null;
	ListView list;
	LightLogListAdapter adapter;
	public HomeLogLightActivity CustomListView = null;
	public ArrayList<ChangeLog> CustomListViewValuesArr = new ArrayList<ChangeLog>();
	public static final String IDENTIDAD = "IDENTIDAD";
	public static final String IDLUZ = "IDLUZ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		Bundle extras = getIntent().getExtras();
		identidad = extras.getInt(IDENTIDAD);
		idluz = extras.getInt(IDLUZ);

		setContentView(R.layout.activity_home_light_log);

		list = (ListView) findViewById(R.id.lightLogList);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();

				LogCollection col = gson.fromJson(task.getResult(),
						LogCollection.class);
				CustomListViewValuesArr = new ArrayList<ChangeLog>(col.getLogs());
				Resources res = getResources();
				adapter = new LightLogListAdapter(HomeLogLightActivity.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeLogLightActivity.this);
			}
		}, RESTConstants.LOG_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(identidad)).put(RESTConstants.ID_LUZ, String.valueOf(idluz)), null).execute((Void) null);

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

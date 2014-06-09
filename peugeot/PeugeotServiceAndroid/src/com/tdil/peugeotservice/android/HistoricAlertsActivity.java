package com.tdil.peugeotservice.android;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.AlertBean;
import com.tdil.peugeotservice.android.rest.model.AlertBeanCollection;
import com.tdil.peugeotservice.android.utils.Messages;

@SuppressLint("ResourceAsColor")
public class HistoricAlertsActivity extends PeugeotActivity {

	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.historic_alerts_activity);
		setTypeface(this, R.id.TextView01);
		setTypeface(this, R.id.closeHistoricAlertasButton);
		setTypeface(this, R.id.sendAlertButton);

		customizeActionBar();

		final ListView list = (ListView) findViewById(R.id.alerts);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();

				AlertBeanCollection col = gson.fromJson(task.getResult(),
						AlertBeanCollection.class);
				ArrayList<AlertBean> CustomListViewValuesArr = new ArrayList<AlertBean>(col.getList());
				Resources res = getResources();
				HistoricAlertsListAdapter adapter = new HistoricAlertsListAdapter(HistoricAlertsActivity.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HistoricAlertsActivity.this);
			}
		}, RESTConstants.GET_CURRENT_MONTH_ALERTS, new RestParams(), null).executeSerial((Void) null);
		
		View snoozeAdvicesButton = findViewById(R.id.closeHistoricAlertasButton);
		snoozeAdvicesButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					HistoricAlertsActivity.this.finish();
				}
			});
	}
	

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	public Toast textView(View findViewById) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_full, menu);
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
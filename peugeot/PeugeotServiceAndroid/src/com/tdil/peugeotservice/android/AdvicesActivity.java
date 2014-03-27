package com.tdil.peugeotservice.android;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.AdviceBean;
import com.tdil.peugeotservice.android.rest.model.AdviceBeanCollection;
import com.tdil.peugeotservice.android.rest.model.RESTResponse;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBean;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBeanCollection;
import com.tdil.peugeotservice.android.utils.Login;
import com.tdil.peugeotservice.android.utils.Messages;

@SuppressLint("ResourceAsColor")
public class AdvicesActivity extends ActionBarActivity {

	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.advices_activity);

	
		this.getSupportActionBar().setTitle(Login.getLoggedUser(this).getName());

		final ListView list = (ListView) findViewById(R.id.advices);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();

				AdviceBeanCollection col = gson.fromJson(task.getResult(),
						AdviceBeanCollection.class);
				ArrayList<AdviceBean> CustomListViewValuesArr = new ArrayList<AdviceBean>(col.getList());
				Resources res = getResources();
				AdvicesListAdapter adapter = new AdvicesListAdapter(AdvicesActivity.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(AdvicesActivity.this);
			}
		}, RESTConstants.GET_ADVICES, new RestParams(), null).executeSerial((Void) null);
		
		View dismissAdvicesButton = findViewById(R.id.dismissAdvicesButton);
		dismissAdvicesButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new RESTClientTask(AdvicesActivity.this, HttpMethod.GET, new IRestClientObserver() {
						@Override
						public void sucess(IRestClientTask task) {
							Gson gson = new Gson();
							RESTResponse resp = gson.fromJson(task.getResult(), RESTResponse.class);
							if (resp.getOk()) {
								Intent intent = new Intent(AdvicesActivity.this,
										IndexActivity.class);
								startActivity(intent);
								AdvicesActivity.this.finish();
							} else {
								new AlertDialog.Builder(AdvicesActivity.this)
					               .setIcon(R.drawable.ic_launcher)
					               .setTitle("Avisos")
					               .setMessage("Ha ocurrido un error borrando los avisos")
					               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					                       public void onClick(DialogInterface dialog, int whichButton) {
					                       }
					               }).show();
							}
						}

						@Override
						public void error(IRestClientTask task) {
							Messages.connectionErrorMessage(AdvicesActivity.this);
						}
					}, RESTConstants.DISMISS_ADVICES, new RestParams(), null)
							.executeSerial((Void) null);
				}
			});
		
		View snoozeAdvicesButton = findViewById(R.id.snoozeAdvicesButton);
		snoozeAdvicesButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					AdvicesActivity.this.startActivity(new Intent(AdvicesActivity.this, IndexActivity.class));
					AdvicesActivity.this.finish();
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

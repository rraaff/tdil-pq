package com.tdil.peugeotservice.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.gui.BeanMappingFunction;
import com.tdil.peugeotservice.android.gui.BeanMappingListAdapter;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.DocumentTypeBean;
import com.tdil.peugeotservice.android.rest.model.DocumentTypeCollection;
import com.tdil.peugeotservice.android.rest.prevent.model.CityBean;
import com.tdil.peugeotservice.android.rest.prevent.model.CityBeanCollection;
import com.tdil.peugeotservice.android.rest.prevent.model.StateBean;
import com.tdil.peugeotservice.android.rest.prevent.model.StateBeanCollection;
import com.tdil.peugeotservice.android.utils.Login;
import com.tdil.peugeotservice.android.utils.Messages;

@SuppressLint("ResourceAsColor")
public class SearchDealersActivity extends ActionBarActivity {

	
	private Spinner citiesSpinner;

	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.search_dealers_activity);

		this.getSupportActionBar().setTitle(Login.getLoggedUser(this).getName());

		final Spinner statesSpinner = (Spinner) findViewById(R.id.dealersStatesSpinner);
		citiesSpinner = (Spinner) findViewById(R.id.dealersCitiesSpinner);
		
		statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final StateBean item = (StateBean) arg0
						.getItemAtPosition(arg2);
				loadCities(item);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				StateBeanCollection col = gson.fromJson(task.getResult(),
						StateBeanCollection.class);
				BeanMappingListAdapter<StateBean> adapter = new BeanMappingListAdapter<StateBean>(
						SearchDealersActivity.this,
						android.R.layout.simple_spinner_item, col.getList(),
						new BeanMappingFunction<StateBean>() {
							public String key(StateBean t) {
								return String.valueOf(t.getId());
							};

							@Override
							public String value(StateBean t) {
								return t.getName();
							}
						});
				statesSpinner.setEnabled(true);
				statesSpinner.setClickable(true);
				statesSpinner.setAdapter(adapter);
				
				citiesSpinner.setEnabled(false);
				citiesSpinner.setClickable(false);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(SearchDealersActivity.this);
			}
		}, RESTConstants.DEALERS_STATES, null, null).executeSerial((Void) null);
	}
	
	
//	@Override
//	protected void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//		int []location = new int[2];
//		Button button = (Button)findViewById(R.id.btnFooterPrevent);
//		button.getLocationInWindow(location);
//		System.out.println(location);
//		
//		Button msg = (Button)findViewById(R.id.vluCount);
//		msg.setX(location[0]);
//		msg.setY(location[1]);
//		
//		AbsoluteLayout.LayoutParams OBJ = new AbsoluteLayout.LayoutParams(35,35,location[0],location[1]);
//		msg.setLayoutParams(OBJ);
//	}



	protected void loadCities(StateBean item) {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				CityBeanCollection col = gson.fromJson(task.getResult(),
						CityBeanCollection.class);
				BeanMappingListAdapter<CityBean> adapter = new BeanMappingListAdapter<CityBean>(
						SearchDealersActivity.this,
						android.R.layout.simple_spinner_item, col.getList(),
						new BeanMappingFunction<CityBean>() {
							public String key(CityBean t) {
								return String.valueOf(t.getId());
							};

							@Override
							public String value(CityBean t) {
								return t.getName();
							}
						});
				citiesSpinner.setEnabled(true);
				citiesSpinner.setClickable(true);
				citiesSpinner.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(SearchDealersActivity.this);
			}
		}, RESTConstants.DEALERS_CITIES, new RestParams(
				RESTConstants.P_DEALER_STATE, String.valueOf(item.getId())), null).executeSerial((Void) null);
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
		getMenuInflater().inflate(R.menu.menu_without_apps, menu);
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

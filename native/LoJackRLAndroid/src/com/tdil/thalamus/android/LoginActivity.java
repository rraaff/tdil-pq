package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.DocumentTypeCollection;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity implements IRestClientObserver {

	/*
	 * public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 * public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 * "android_version.txt";
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	private RESTClientTask mAuthTask = null;
	// Values for email and password at the time of the login attempt.
	private String mDocType;
	private String mDocNumber;
	private String mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
		final Spinner spinner = (Spinner) findViewById(R.id.documentType);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				 DocumentTypeBean item = (DocumentTypeBean)arg0.getItemAtPosition(arg2);
				 LoginActivity.this.mDocType = String.valueOf(item.getId());
				
			}@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				 Gson gson = new Gson();
				 DocumentTypeCollection col = gson.fromJson(task.getResult(), DocumentTypeCollection.class);
				 BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(LoginActivity.this,
					android.R.layout.simple_spinner_item, col.getList(), new BeanMappingFunction<DocumentTypeBean>() {
			 			public String key(DocumentTypeBean t) {return String.valueOf(t.getId());};
			 			@Override
			 			public String value(DocumentTypeBean t) {
			 				return t.getName();
			 			}
					});
					spinner.setAdapter(adapter);
			}
			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(LoginActivity.this);
			}
		}, RESTConstants.DOCUMENT_TYPES, null, null).execute((Void) null);
		
		/*List<String> list = new ArrayList<String>();
		list.add("DNI");
		list.add("LE");
		SpinAdapter adapter = new SpinAdapter(this,
				android.R.layout.simple_spinner_item, list);
		spinner.setAdapter(adapter);*/

		findViewById(R.id.lights).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Store values at the time of the login attempt.
		mDocNumber = ((EditText) findViewById(R.id.documentNumber)).getText().toString();
		mPassword = ((EditText) findViewById(R.id.password)).getText().toString();

		boolean cancel = false;
		View focusView = null;
		showProgress(true);
		mAuthTask = new RESTClientTask(this, HttpMethod.GET, this, RESTConstants.LOGIN, new RestParams(RESTConstants.P_DOCUMENT_TYPE, mDocType).
				put(RESTConstants.P_DOCUMENT_NUMBER, mDocNumber).put(RESTConstants.P_PASSWORD, mPassword), null);
		mAuthTask.execute((Void) null);
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
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_go_home:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void error(RESTClientTask task) {
		Messages.connectionErrorMessage(LoginActivity.this);
		this.mAuthTask = null;
	}
	@Override
	public void sucess(RESTClientTask task) {
		Gson gson = new Gson();
		LoginResponse resp = gson.fromJson(task.getResult(), LoginResponse.class);
		if (resp.getLogged()) {
			Intent intent = new Intent(this, HomeActivity.class);
        	startActivity(intent);
        	finish();
		} else {
			System.out.println("not logged");
		}
		this.mAuthTask = null;
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

package com.tdil.thalamus.android;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTask;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity implements IRestClientObserver {

	public static final String URL_WEBSITE = "http://192.168.0.107:8180/LoJackWeb/";
	public static final String URL_ANDROID_VERSION = URL_WEBSITE
			+ "android_version.txt";

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
	private String mEmail;
	private String mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		Spinner spinner = (Spinner) findViewById(R.id.documentType);
		List<String> list = new ArrayList<String>();
		list.add("DNI");
		list.add("LE");
		SpinAdapter adapter = new SpinAdapter(this,
				android.R.layout.simple_spinner_item, list);
		spinner.setAdapter(adapter);

		findViewById(R.id.button1).setOnClickListener(
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
		mEmail = "1:25270160";
		mPassword = "123456";

		boolean cancel = false;
		View focusView = null;
		showProgress(true);
		mAuthTask = new RESTClientTask(this, HttpMethod.GET, this);
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
	public void error() {
		this.mAuthTask = null;
	}
	@Override
	public void sucess() {
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

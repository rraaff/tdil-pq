package com.tdil.thalamus.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tdil.lojack.rl.R;

@SuppressLint("ResourceAsColor")
public class FirstAccessActivity extends LoJackActivity {


	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_first_access);
		setTypeface(this, R.id.alreadyClientButton);
		setTypeface(this, R.id.notClientButton);

		customizeActionBar(false);

		//View sendEmergencyButton = findViewById(R.id.sendEmergencyButton);
		//sendEmergencyButton.setOnClickListener(new SendAlertOnClickListener(this));

		/*View configEmergencyButton = findViewById(R.id.configEmergencyButton);
		configEmergencyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ServicesDashboardActivity.this.startActivity(new Intent(
						ServicesDashboardActivity.this,
						UpdateEmergencyConfigActivity.class));
			}
		});*/

		View myServices = findViewById(R.id.alreadyClientButton);
		myServices.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final String PREFS_NAME = "RLPrefsFile";
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				// Update version in preferences
				settings.edit()
				        .putBoolean(CheckForUpdateActivity.IS_CLIENT ,true).commit();
                FirstAccessActivity.this.startActivity(new Intent(
						FirstAccessActivity.this,
						LoginActivity.class));
				FirstAccessActivity.this.finish();
			}
		});

		View officialServices = findViewById(R.id.notClientButton);
		officialServices.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FirstAccessActivity.this.startActivity(new Intent(
						FirstAccessActivity.this,
						NotClientActivity.class));
				FirstAccessActivity.this.finish();
			}
		});

	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected boolean mustUpdateMessages() {
		return false;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public Toast textView(View findViewById) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//getMenuInflater().inflate(R.menu.menu_full, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//return MenuLogic.handleOnOptionsItemSelected(this, item);
		return false;
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

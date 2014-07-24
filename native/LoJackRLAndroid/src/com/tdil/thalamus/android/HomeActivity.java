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

import com.tdil.lojack.rl.R;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeActivity extends LoJackActivity {

	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_home);
		customizeActionBar();
		findViewById(R.id.alarmas).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(HomeActivity.this, HomeAlarmsActivity.class);
					intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_ALARMAS);
		        	startActivity(intent);
		        	//finish();
				}
			});
	
		findViewById(R.id.lights).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(HomeActivity.this, HomeAlarmsActivity.class);
					intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_LUCES);
		        	startActivity(intent);
				}
			});
		
		findViewById(R.id.cameras).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(HomeActivity.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_CAMARAS);
			        	startActivity(intent);
					}
				});
		
		findViewById(R.id.updateButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
			        	startActivity(intent);
					}
				});
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

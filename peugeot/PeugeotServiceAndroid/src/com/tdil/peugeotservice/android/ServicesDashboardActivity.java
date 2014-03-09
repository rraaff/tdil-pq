package com.tdil.peugeotservice.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.utils.Login;

@SuppressLint("ResourceAsColor")
public class ServicesDashboardActivity extends ActionBarActivity {

	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_services_dashboard);

	
		this.getSupportActionBar().setTitle(Login.getLoggedUser(this).getName());

		View sendEmergencyButton = findViewById(R.id.sendEmergencyButton);
		sendEmergencyButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new AlertDialog.Builder(ServicesDashboardActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("TODO")
					.setMessage("Funcionalidad pendiente de definicion")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					}).show();
				}
			});
		
		View configEmergencyButton = findViewById(R.id.configEmergencyButton);
		configEmergencyButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ServicesDashboardActivity.this.startActivity(new Intent(ServicesDashboardActivity.this, UpdateEmergencyConfigActivity.class));
				}
			});
		
		View myServices = findViewById(R.id.myServicesButton);
		myServices.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ServicesDashboardActivity.this.startActivity(new Intent(ServicesDashboardActivity.this, MyServicesActivity.class));
				}
			});
		
		View officialServices = findViewById(R.id.officialServicesButton);
		officialServices.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ServicesDashboardActivity.this.startActivity(new Intent(ServicesDashboardActivity.this, OfficialServicesActivity.class));
				}
			});
		
		View myVehicles = findViewById(R.id.myVehiclesButton);
		myVehicles.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ServicesDashboardActivity.this.startActivity(new Intent(ServicesDashboardActivity.this, MyVehiclesActivity.class));
				}
			});
		
		View search = findViewById(R.id.searchDealersButton);
		search.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ServicesDashboardActivity.this.startActivity(new Intent(ServicesDashboardActivity.this, SearchDealersActivity.class));
				}
			});
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

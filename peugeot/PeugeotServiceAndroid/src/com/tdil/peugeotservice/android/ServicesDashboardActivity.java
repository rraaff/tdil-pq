package com.tdil.peugeotservice.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.AlertResponseBean;
import com.tdil.peugeotservice.android.utils.Login;
import com.tdil.peugeotservice.android.utils.Messages;

@SuppressLint("ResourceAsColor")
public class ServicesDashboardActivity extends ActionBarActivity {

	private final class UpdateLocationListener implements LocationListener {
		private final AlertResponseBean resp;
		private final LocationManager lm;
		private final ServicesDashboardActivity activity;
		private boolean alreadySent = false;

		private UpdateLocationListener(AlertResponseBean resp,
				LocationManager lm, ServicesDashboardActivity activity) {
			this.resp = resp;
			this.lm = lm;
			this.activity = activity;
		}

		public void onLocationChanged(Location location) {
			if (!alreadySent) {
				alreadySent = true;
				double updatedLongitude = location.getLongitude();
				double updatedLatitude = location.getLatitude();
				lm.removeUpdates(this);
				activity.updateAlertLocation(resp, updatedLatitude, updatedLongitude);
			}
		}

		public void onProviderDisabled(String provider) {}

		public void onProviderEnabled(String provider) {}

		public void onStatusChanged(String provider, int status,
		        Bundle extras) {}
	}

	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_services_dashboard);

		this.getSupportActionBar()
				.setTitle(Login.getLoggedUser(this).getName());

		View sendEmergencyButton = findViewById(R.id.sendEmergencyButton);
		sendEmergencyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				double longitude = 0;
				double latitude = 0;
				String mPhoneNumber = "-";
				TelephonyManager tMgr = (TelephonyManager) ServicesDashboardActivity.this
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (tMgr != null) {
					mPhoneNumber = tMgr.getLine1Number();
				}
				if (mPhoneNumber == null || mPhoneNumber.equals("")) {
					mPhoneNumber = "-";
				}
				sendAlert(longitude, latitude, mPhoneNumber);
			}
		});

		View configEmergencyButton = findViewById(R.id.configEmergencyButton);
		configEmergencyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ServicesDashboardActivity.this.startActivity(new Intent(
						ServicesDashboardActivity.this,
						UpdateEmergencyConfigActivity.class));
			}
		});

		View myServices = findViewById(R.id.myServicesButton);
		myServices.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ServicesDashboardActivity.this.startActivity(new Intent(
						ServicesDashboardActivity.this,
						MyServicesActivity.class));
			}
		});

		View officialServices = findViewById(R.id.officialServicesButton);
		officialServices.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ServicesDashboardActivity.this.startActivity(new Intent(
						ServicesDashboardActivity.this,
						OfficialServicesActivity.class));
			}
		});

		View myVehicles = findViewById(R.id.myVehiclesButton);
		myVehicles.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ServicesDashboardActivity.this.startActivity(new Intent(
						ServicesDashboardActivity.this,
						MyVehiclesActivity.class));
			}
		});

		View search = findViewById(R.id.searchDealersButton);
		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ServicesDashboardActivity.this.startActivity(new Intent(
						ServicesDashboardActivity.this,
						SearchDealersActivity.class));
			}
		});
	}

	// @Override
	// protected void onStart() {
	// // TODO Auto-generated method stub
	// super.onStart();
	// int []location = new int[2];
	// Button button = (Button)findViewById(R.id.btnFooterPrevent);
	// button.getLocationInWindow(location);
	// System.out.println(location);
	//
	// Button msg = (Button)findViewById(R.id.vluCount);
	// msg.setX(location[0]);
	// msg.setY(location[1]);
	//
	// AbsoluteLayout.LayoutParams OBJ = new
	// AbsoluteLayout.LayoutParams(35,35,location[0],location[1]);
	// msg.setLayoutParams(OBJ);
	// }

	public void updateAlertLocation(AlertResponseBean resp,
			double updatedLatitude, double updatedLongitude) {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				if (task.getStatusCode() == 201) {
					new AlertDialog.Builder(ServicesDashboardActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("Envio de alerta")
					.setMessage(
							"Se ha enviado el mensaje de alerta")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialog,
										int whichButton) {
								}
							}).show();
					
				} else {
					new AlertDialog.Builder(ServicesDashboardActivity.this)
							.setIcon(R.drawable.ic_launcher)
							.setTitle("Envio de alerta")
							.setMessage(
									"Ha occurrido un error, por favor intentelo nuevamente")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
										}
									}).show();
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ServicesDashboardActivity.this);
			}
		}, RESTConstants.UPDATE_ALERT, new RestParams(RESTConstants.P_LAT, String
				.valueOf(updatedLatitude)).put(RESTConstants.P_LON,
				String.valueOf(updatedLongitude)).put(RESTConstants.P_ALERTID,
						resp.getAlertId()), null).executeSerial((Void) null);
		
	}

	protected void sendAlert(final double longitude, final double latitude,
			final String mPhoneNumber) {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				if (task.getStatusCode() == 201) {
					final AlertResponseBean resp = gson.fromJson(task.getResult(),
							AlertResponseBean.class);
					final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
					Criteria criteria = new Criteria();
					String bestProvider = lm.getBestProvider(criteria, false);
					lm.requestLocationUpdates(bestProvider, 1, 0,
				            new UpdateLocationListener(resp, lm, ServicesDashboardActivity.this));
					
				} else {
					new AlertDialog.Builder(ServicesDashboardActivity.this)
							.setIcon(R.drawable.ic_launcher)
							.setTitle("Envio de alerta")
							.setMessage(
									"Ha occurrido un error, por favor intentelo nuevamente")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
										}
									}).show();
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ServicesDashboardActivity.this);
			}
		}, RESTConstants.ADD_ALERT, new RestParams(RESTConstants.P_LAT, String
				.valueOf(latitude)).put(RESTConstants.P_LON,
				String.valueOf(longitude)).put(RESTConstants.P_PHONE,
				mPhoneNumber), null).executeSerial((Void) null);
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

package com.tdil.peugeotservice.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import com.tdil.peugeotservice.android.utils.Messages;

@SuppressLint("ResourceAsColor")
public class ServicesDashboardActivity extends ActionBarActivity {

	private UpdateLocationListener locListener = null;


	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_services_dashboard);

		this.getSupportActionBar().setTitle(ApplicationConfig.APP_NAME);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		/** START ALERTA */
		AlertLogic.installLogic(this);
		/** END ALERTA */

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

	public static void updateAlertLocation(final Activity activity, AlertResponseBean resp,
			double updatedLatitude, double updatedLongitude, final boolean silent, final SendAlertOnClickListener listener) {
		new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				if (!silent) {
					Gson gson = new Gson();
					if (task.getStatusCode() == 201) {
						new AlertDialog.Builder(activity)
						.setIcon(R.drawable.ic_launcher)
						.setTitle("Envio de alerta")
						.setMessage(
								"Se ha enviado el mensaje de alerta")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int whichButton) {
										listener.alertSent();
									}
								}).show();
						
					} else {
						new AlertDialog.Builder(activity)
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
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(activity);
			}
		}, RESTConstants.UPDATE_ALERT, new RestParams(RESTConstants.P_LAT, String
				.valueOf(updatedLatitude)).put(RESTConstants.P_LON,
				String.valueOf(updatedLongitude)).put(RESTConstants.P_ALERTID,
						resp.getAlertId()), null).executeSerial((Void) null);
		
	}

	public static void sendAlert(final Activity activity, final double longitude, final double latitude,
			final String mPhoneNumber, final SendAlertOnClickListener listener) {
		new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				if (task.getStatusCode() == 201) {
					if (longitude != 0) {
						new AlertDialog.Builder(activity)
						.setIcon(R.drawable.ic_launcher)
						.setTitle("Envio de alerta")
						.setMessage(
								"Se ha enviado el mensaje de alerta")
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int whichButton) {
										listener.alertSent();
									}
								}).show();
					} else {
						final AlertResponseBean resp = gson.fromJson(task.getResult(),
								AlertResponseBean.class);
						
						final LocationManager manager = (LocationManager) activity.getSystemService( Context.LOCATION_SERVICE );
	
					    if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
					    	if ( manager.isProviderEnabled( LocationManager.NETWORK_PROVIDER )) {
					    		// pido la ubicacion basada en wifi o antena
					    		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0,
					    				new UpdateLocationListener(resp, manager, activity, listener));
					    	} else {
					    		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
					    	    builder.setMessage("Se ha enviado el alerta pero su ubicacion no pudo ser determinada. " +
					    	    		"Desea encender el GPS para enviar una informacion mas precisa?")
					    	           .setCancelable(false)
					    	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					    	               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
					    	            	   activity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					    	                   manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0,
					    	                		   new UpdateLocationListener(resp, manager, activity, listener));
					    	               }
					    	           })
					    	           .setNegativeButton("No", new DialogInterface.OnClickListener() {
					    	               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
					    	                    dialog.cancel();
					    	                    listener.alertSent();
					    	               }
					    	           });
					    	    final AlertDialog alert = builder.create();
					    	    alert.show();
					    	}
					    } else {
					    	manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0,
					    			new UpdateLocationListener(resp, manager, activity, listener));
					    }
					} 
				}else {
					new AlertDialog.Builder(activity)
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
				Messages.connectionErrorMessage(activity);
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (locListener != null) {
			locListener.destroy();
		}
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

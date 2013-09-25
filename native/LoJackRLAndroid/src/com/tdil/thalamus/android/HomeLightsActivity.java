package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmJobStatusCollection;
import com.tdil.thalamus.android.rest.model.AsyncJobResponse;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.LightCollection;
import com.tdil.thalamus.android.rest.model.LightJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeLightsActivity extends Activity {
	/**
	 * The default email to populate the email field with.
	 */

	ListView list;
	LightListAdapter adapter;
	public HomeLightsActivity CustomListView = null;
	public ArrayList<Light> CustomListViewValuesArr = new ArrayList<Light>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_lights);
		list = (ListView) findViewById(R.id.lightsList);
		loadLights();
	}

	public void loadLights() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();

				LightCollection col = gson.fromJson(task.getResult(),
						LightCollection.class);
				CustomListViewValuesArr = new ArrayList<Light>(col.getLights());
				Resources res = getResources();
				adapter = new LightListAdapter(HomeLightsActivity.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeLightsActivity.this);
			}
		}, RESTConstants.LIGHTS, null, null).execute((Void) null);
	}
	
	private void startBackgroundJob() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				LightJobStatusCollection col = gson.fromJson(task.getResult(),
						LightJobStatusCollection.class);
				if (!col.getStatus().isEmpty()) {
					HomeLightsActivity.this.startBackgroundJob();
				} else {
					HomeLightsActivity.this.loadLights();
				}
			}
			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(HomeLightsActivity.this);
			}
		}, RESTConstants.LIGHT_STATUS, new RestParams(), null).execute();
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

	public void onItemClick(int mPosition) {
		Light tempValues = (Light) CustomListViewValuesArr.get(mPosition);
		/*
		 * Toast.makeText(CustomListView,
		 * ""+tempValues.getCompanyName()+" \nImage:"
		 * +tempValues.getImage()+" \nUrl:"+tempValues.getUrl(),
		 * Toast.LENGTH_LONG) .show();
		 */
	}
	
	public void toggleActivation(int mPosition) {
		Light light = (Light) CustomListViewValuesArr.get(mPosition);
		if (light.isOn()) {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de apagado")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   startBackgroundJob();
			                       }
	
			               }).show();
					} else {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de apagado")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
	
			               }).show();
					}
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeLightsActivity.this);
				}
			}, RESTConstants.DEACTIVATE_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		} else {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de encendido")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   startBackgroundJob();
			                       }
			               }).show();
					} else {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de encendido")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
			               }).show();
					}
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeLightsActivity.this);
				}
			}, RESTConstants.ACTIVATE_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		}
	}
	
	public void toggleRandom(int mPosition) {
		Light light = (Light) CustomListViewValuesArr.get(mPosition);
		if (light.isInRandomMode()) {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de desactivacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   startBackgroundJob();
			                       }
	
			               }).show();
					} else {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de desactivacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
	
			               }).show();
					}
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeLightsActivity.this);
				}
			}, RESTConstants.DEACTIVATE_RANDOM_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		} else {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					AsyncJobResponse asyncJobResponse = gson.fromJson(task.getResult(),
							AsyncJobResponse.class);
					if (asyncJobResponse.isAccepted()) {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("Se ha enviado el comando de activacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                    	   startBackgroundJob();
			                       }
			               }).show();
					} else {
						new AlertDialog.Builder(HomeLightsActivity.this)
			               .setIcon(R.drawable.ic_launcher)
			               .setTitle("Luces")
			               .setMessage("No ha podido enviarse el comando de activacion de secuencia aleatoria")
			               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                       public void onClick(DialogInterface dialog, int whichButton) {
			                       }
			               }).show();
					}
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeLightsActivity.this);
				}
			}, RESTConstants.ACTIVATE_RANDOM_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(light.getIdEntidad())).put(RESTConstants.ID_LUZ, String.valueOf(light.getIdLuz())), null).execute((Void) null);
		}
	}
	
	public void viewLightLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogLightActivity.class);
		Light alarm = CustomListViewValuesArr.get(mPosition);
		intent.putExtra(HomeLogLightActivity.IDENTIDAD, alarm.getIdEntidad());
		intent.putExtra(HomeLogLightActivity.IDLUZ, alarm.getIdLuz());
		startActivity(intent);
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

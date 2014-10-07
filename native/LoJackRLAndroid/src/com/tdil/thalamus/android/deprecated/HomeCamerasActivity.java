package com.tdil.thalamus.android.deprecated;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackLoggedActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.UnCaughtException;
import com.tdil.thalamus.android.home.CameraListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.rest.model.CameraCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@Deprecated
public class HomeCamerasActivity extends LoJackLoggedActivity {
	/**
	 * The default email to populate the email field with.
	 */

	private ListView cameraList;
	private CameraListAdapter cameraListAdapter;
	public ArrayList<Camera> cameras = new ArrayList<Camera>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_home_cameras);
		customizeActionBar();

		cameraList = (ListView) findViewById(R.id.camerasList);

		loadCameras();
	}

	public void loadCameras() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();

				CameraCollection col = gson.fromJson(task.getResult(),
						CameraCollection.class);
				cameras = new ArrayList<Camera>(col.getCameras());
				Resources res = getResources();
				cameraListAdapter = new CameraListAdapter(HomeCamerasActivity.this,
						cameras, res);
				cameraList.setAdapter(cameraListAdapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(HomeCamerasActivity.this);
			}
		}, RESTConstants.CAMERAS, null, null).execute((Void) null);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	public void onItemClick(int mPosition) {
		Camera tempValues = (Camera) cameras.get(mPosition);
		/*
		 * Toast.makeText(CustomListView,
		 * ""+tempValues.getCompanyName()+" \nImage:"
		 * +tempValues.getImage()+" \nUrl:"+tempValues.getUrl(),
		 * Toast.LENGTH_LONG) .show();
		 */
	}
	
	public void toggleActivation(int mPosition) {
		Camera alarm = (Camera) cameras.get(mPosition);
		/*if (alarm.isActive()) {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					new AlertDialog.Builder(HomeLightsActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Alarms")
		               .setMessage("Se ha enviado el comando de desactivacion")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   //this.
		                       }
		               }).show();
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeLightsActivity.this);
				}
			}, RESTConstants.DEACTIVATE_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
		} else {
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
					Gson gson = new Gson();
					new AlertDialog.Builder(HomeLightsActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Alarms")
		               .setMessage("Se ha enviado el comando de activacion")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   //HomeAlarmsActivity.this.finish();
		                       }
		               }).show();
				}
				@Override
				public void error(RESTClientTask task) {
					Messages.connectionErrorMessage(HomeLightsActivity.this);
				}
			}, RESTConstants.ACTIVATE_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null).execute((Void) null);
		}*/
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

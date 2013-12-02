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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.camera.IPCamera;
import com.tdil.thalamus.android.camera.PanasonicBLC131;
import com.tdil.thalamus.android.camera.TPLinkSC4171G;
import com.tdil.thalamus.android.camera.TrendnetTVIP851;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.utils.DownloadCameraImageTask;
import com.tdil.thalamus.android.utils.MoveCameraDownTask;
import com.tdil.thalamus.android.utils.MoveCameraLeftTask;
import com.tdil.thalamus.android.utils.MoveCameraRightTask;
import com.tdil.thalamus.android.utils.MoveCameraUpTask;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeCameraActivity extends Activity {

	public static final String TAB_CAMARAS = "CAMARAS";
	public static final String TAB_LUCES = "LUCES";
	public static final String TAB_ALARMAS = "ALARMAS";
	
	public static final String CAMERA = "CAMERA";
	
	public static final String CAMERAS_COUNT = "CAMERAS_COUNT";

	private IPCamera camera;
	private Integer camerasCount;
	
	private TabSpec tabCameras;
	private TabHost tabHost;

	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_camera);
		Bundle extras = getIntent().getExtras();
		
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		
		TabSpec ts = tabHost.newTabSpec("tabAlarms");
		ts.setContent(R.id.tab1);
		ts.setIndicator(TAB_ALARMAS);
		tabHost.addTab(ts);

		TabSpec tabLights = tabHost.newTabSpec("tabLights");
		tabLights.setContent(R.id.tab2);
		tabLights.setIndicator(TAB_LUCES);
		tabHost.addTab(tabLights);

		tabCameras = tabHost.newTabSpec("tabCameras");
		tabCameras.setContent(R.id.tab3);
		tabCameras.setIndicator(TAB_CAMARAS);
		tabHost.addTab(tabCameras);
		
		tabHost.setCurrentTab(2);
		
		Camera tmpCamera = (Camera)extras.getSerializable(CAMERA);
		camerasCount = (Integer)extras.getSerializable(CAMERAS_COUNT);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (TPLinkSC4171G.TP_LINK_SC4171G.equals(tmpCamera.getModel())) {
			camera = new TPLinkSC4171G(tmpCamera.getUrl(), tmpCamera.getUsername(), tmpCamera.getPassword());
		}
		if (PanasonicBLC131.PANASONIC_BLC131.equals(tmpCamera.getModel())) {
			camera = new PanasonicBLC131(tmpCamera.getUrl(), tmpCamera.getUsername(), tmpCamera.getPassword());
		}
		
		if (TrendnetTVIP851.TrendnetTVIP851.equals(tmpCamera.getModel())) {
			camera = new TrendnetTVIP851(tmpCamera.getUrl(), tmpCamera.getUsername(), tmpCamera.getPassword());
		}

		new DownloadCameraImageTask(this, (ImageView)this.findViewById(R.id.cameraView), RESTConstants.CAMERA_URL + "").execute();
		
		
		findViewById(R.id.leftButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// left
						new MoveCameraLeftTask(camera).execute();
						//new MoveCameraTask(RESTConstants.CAMERA_MOVE_URL + frame, "left").execute();
					}
				});
		findViewById(R.id.upButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						new MoveCameraUpTask(camera).execute();
//						new MoveCameraTask(RESTConstants.CAMERA_MOVE_URL + urlCamera, "up").execute();
					}
				});
		findViewById(R.id.downButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						new MoveCameraDownTask(camera).execute();
//						new MoveCameraTask(RESTConstants.CAMERA_MOVE_URL + urlCamera, "down").execute();
					}
				});
		findViewById(R.id.rightButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						new MoveCameraRightTask(camera).execute();
//						new MoveCameraTask(RESTConstants.CAMERA_MOVE_URL + urlCamera, "right").execute();
					}
				});
		
		int numberOfTabs = tabHost.getTabWidget().getChildCount();
	    for(int t=0; t<numberOfTabs; t++){
	    	final int index = t;
	        tabHost.getTabWidget().getChildAt(t).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (index == 0) {
						Intent intent = new Intent(HomeCameraActivity.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_ALARMAS);
			        	startActivity(intent);
			        	HomeCameraActivity.this.finish();
					}
					if (index == 1) {
						Intent intent = new Intent(HomeCameraActivity.this, HomeAlarmsActivity.class);
						intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_LUCES);
			        	startActivity(intent);
			        	HomeCameraActivity.this.finish();
					}
					if (index == 2) {
						if (camerasCount > 1) {
							Intent intent = new Intent(HomeCameraActivity.this, HomeAlarmsActivity.class);
							intent.putExtra(HomeAlarmsActivity.SELECTED_TAB, HomeAlarmsActivity.TAB_CAMARAS);
				        	startActivity(intent);
				        	HomeCameraActivity.this.finish();
						}
					}
				}
			});
	    }
		
		FooterLogic.installFooterLogic(this);
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

	public IPCamera getCamera() {
		return camera;
	}

	public void setCamera(IPCamera camera) {
		this.camera = camera;
	}

}

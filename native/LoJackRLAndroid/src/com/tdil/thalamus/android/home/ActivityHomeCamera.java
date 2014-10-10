package com.tdil.thalamus.android.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.camera.IPCamera;
import com.tdil.thalamus.android.camera.PanasonicBLC131;
import com.tdil.thalamus.android.camera.TPLinkSC4171G;
import com.tdil.thalamus.android.camera.TrendnetTVIP851;
import com.tdil.thalamus.android.gui.OnSwipeTouchListener;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.utils.DownloadCameraImageTask;
import com.tdil.thalamus.android.utils.MoveCameraDownTask;
import com.tdil.thalamus.android.utils.MoveCameraLeftTask;
import com.tdil.thalamus.android.utils.MoveCameraRightTask;
import com.tdil.thalamus.android.utils.MoveCameraUpTask;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeCamera extends HomeActivity {

	public static final String CAMERAS = "CAMERAS";
	public static final String CAMERA = "CAMERA";
	
	public static final String CAMERAS_COUNT = "CAMERAS_COUNT";

	private IPCamera camera;
	private Integer camerasCount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_camera);
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        Camera tmpCamera = (Camera)extras.getSerializable(CAMERA);
		camerasCount = (Integer)extras.getSerializable(CAMERAS_COUNT);
		
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
		
		ImageView cameraImageView = (ImageView)this.findViewById(R.id.cameraView);
		cameraImageView.setOnTouchListener(new OnSwipeTouchListener(this) {
		    public void onSwipeTop() {
		    	new MoveCameraUpTask(camera).execute();
		    }
		    public void onSwipeRight() {
		    	new MoveCameraRightTask(camera).execute();
		    }
		    public void onSwipeLeft() {
		    	new MoveCameraLeftTask(camera).execute();
		    }
		    public void onSwipeBottom() {
		    	new MoveCameraDownTask(camera).execute();
		    }

		});
		
		/*findViewById(R.id.leftButton).setOnClickListener(
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
				*/
    }
    
 
    public IPCamera getCamera() {
		return camera;
	}

	public void setCamera(IPCamera camera) {
		this.camera = camera;
	}
    
	@Override
	public boolean isAlarmsTab() {
		return false;
	}
	@Override
	public boolean isCamerasTab() {
		return true;
	}
	@Override
	public boolean isLightsTab() {
		return false;
	}
}
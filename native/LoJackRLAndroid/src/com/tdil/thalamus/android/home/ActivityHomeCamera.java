package com.tdil.thalamus.android.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.camera.IPCamera;
import com.tdil.thalamus.android.camera.PanasonicBLC131;
import com.tdil.thalamus.android.camera.TPLinkSC4171G;
import com.tdil.thalamus.android.camera.TrendnetTVIP851;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
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
public class ActivityHomeCamera extends LoJackWithProductMenuActivity {

	public static final String CAMERAS = "CAMERAS";
	public static final String CAMERA = "CAMERA";
	
	public static final String CAMERAS_COUNT = "CAMERAS_COUNT";

	private IPCamera camera;
	private Integer camerasCount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_camera);
        HeaderLogic.installTabLogic(this);
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
    }
    
 
    public IPCamera getCamera() {
		return camera;
	}

	public void setCamera(IPCamera camera) {
		this.camera = camera;
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
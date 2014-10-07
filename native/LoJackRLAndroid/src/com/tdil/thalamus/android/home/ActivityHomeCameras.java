package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.rest.model.Light;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeCameras extends HomeActivity {

	public static final String CAMERAS = "CAMERAS";
	
	private CameraListAdapter cameraListAdapter;
	public ArrayList<Camera> cameras = new ArrayList<Camera>();
	private ListView cameraList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_cameras);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        cameras = (ArrayList<Camera>)extras.getSerializable(CAMERAS);
        cameraListAdapter = new CameraListAdapter(ActivityHomeCameras.this,
        		cameras, res);
        cameraList = (ListView) findViewById(R.id.camerasList);
        cameraList.setAdapter(cameraListAdapter);
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
package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.Camera;
import com.tdil.thalamus.android.rest.model.Light;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeCameras extends LoJackWithProductMenuActivity {

	public static final String CAMERAS = "CAMERAS";
	
	private CameraListAdapter cameraListAdapter;
	public ArrayList<Camera> cameras = new ArrayList<Camera>();
	private ListView cameraList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_cameras);
        HeaderLogic.installTabLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        cameras = (ArrayList<Camera>)extras.getSerializable(CAMERAS);
        cameraListAdapter = new CameraListAdapter(ActivityHomeCameras.this,
        		cameras, res);
        cameraList = (ListView) findViewById(R.id.camerasList);
        cameraList.setAdapter(cameraListAdapter);
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
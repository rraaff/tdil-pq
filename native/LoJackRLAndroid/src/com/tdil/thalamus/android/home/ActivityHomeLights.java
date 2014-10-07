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
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.Light;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeLights extends HomeActivity {

	public static final String LIGHTS = "LIGHTS";
	
	private LightListAdapter lightsListAdapter;
	public ArrayList<Light> lights = new ArrayList<Light>();
	private ListView lightsList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_lights);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        lights = (ArrayList<Light>)extras.getSerializable(LIGHTS);
        lightsListAdapter = new LightListAdapter(ActivityHomeLights.this,
        		lights, res);
        lightsList = (ListView) findViewById(R.id.lightsList);
        lightsList.setAdapter(lightsListAdapter);
    }
	
	@Override
	public boolean isAlarmsTab() {
		return false;
	}
	@Override
	public boolean isCamerasTab() {
		return false;
	}
	@Override
	public boolean isLightsTab() {
		return true;
	}
}
package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.Light;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeLights extends LoJackWithProductMenuActivity {

	public static final String LIGHTS = "LIGHTS";
	
	private LightListAdapter lightsListAdapter;
	public ArrayList<Light> lights = new ArrayList<Light>();
	private ListView lightsList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_lights);
        HeaderLogic.installTabLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        lights = (ArrayList<Light>)extras.getSerializable(LIGHTS);
        lightsListAdapter = new LightListAdapter(ActivityHomeLights.this,
        		lights, res);
        lightsList = (ListView) findViewById(R.id.lightsList);
        lightsList.setAdapter(lightsListAdapter);
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
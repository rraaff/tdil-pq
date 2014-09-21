package com.tdil.thalamus.android.car.parkedmode;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.car.ActivityCars;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatusCollection;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityParkedModeVehicles extends LoJackWithProductMenuActivity {

	public static final String VEHICLES_LIST = "VEHICLES_LIST";
	private ParkedModeStatusCollection parkedModeStatusCollection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_list);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		parkedModeStatusCollection = (ParkedModeStatusCollection)extras.getSerializable(VEHICLES_LIST);
		final ListView list = (ListView) findViewById(R.id.pmStatusList);
		
		ArrayList<ParkedModeStatus> CustomListViewValuesArr = new ArrayList<ParkedModeStatus>(parkedModeStatusCollection.getList());
		Resources res = getResources();
		ParkedModeStatusListAdapter adapter = new ParkedModeStatusListAdapter(this,
				CustomListViewValuesArr, res);
		list.setAdapter(adapter);
		
		((View)findViewById(R.id.pmVehiclesBack)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
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
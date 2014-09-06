package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.Alarm;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeAlarms extends LoJackWithProductMenuActivity {

	public static final String ALARMS = "ALARMS";
	
	private AlarmListAdapter alarmListAdapter;
	public ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	private ListView alarmsList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_alarms);
        HeaderLogic.installTabLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        alarms = (ArrayList<Alarm>)extras.getSerializable(ALARMS);
		alarmListAdapter = new AlarmListAdapter(ActivityHomeAlarms.this,
				alarms, res);
		alarmsList = (ListView) findViewById(R.id.alarmsList);
		alarmsList.setAdapter(alarmListAdapter);
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
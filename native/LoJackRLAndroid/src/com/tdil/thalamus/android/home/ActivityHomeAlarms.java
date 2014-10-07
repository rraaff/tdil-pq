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


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeAlarms extends HomeActivity {

	public static final String ALARMS = "ALARMS";
	
	private AlarmListAdapter alarmListAdapter;
	public ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	private ListView alarmsList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_alarms);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        alarms = (ArrayList<Alarm>)extras.getSerializable(ALARMS);
		alarmListAdapter = new AlarmListAdapter(ActivityHomeAlarms.this,
				alarms, res);
		alarmsList = (ListView) findViewById(R.id.alarmsList);
		alarmsList.setAdapter(alarmListAdapter);
    }
    
	@Override
	public boolean isAlarmsTab() {
		return true;
	}
	@Override
	public boolean isCamerasTab() {
		return false;
	}
	@Override
	public boolean isLightsTab() {
		return false;
	}
}
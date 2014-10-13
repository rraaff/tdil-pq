package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.rest.model.AlarmAgenda;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeAlarmsAgendas extends HomeActivity {

	public static final String AGENDAS = "AGENDAS";
	
	private AlarmAgendaListAdapter alarmListAdapter;
	public ArrayList<AlarmAgenda> alarms = new ArrayList<AlarmAgenda>();
	private ListView alarmsList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_alarm_agendas);
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        alarms = (ArrayList<AlarmAgenda>)extras.getSerializable(AGENDAS);
		alarmListAdapter = new AlarmAgendaListAdapter(ActivityHomeAlarmsAgendas.this,
				alarms, res);
		alarmsList = (ListView) findViewById(R.id.agendaList);
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
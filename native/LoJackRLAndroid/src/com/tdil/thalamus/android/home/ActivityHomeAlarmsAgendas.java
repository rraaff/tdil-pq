package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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

	public static final String ID_ENTIDAD = "ID_ENTIDAD";
	public static final String AGENDAS = "AGENDAS";
	
	private int idEntidad;
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
        idEntidad = extras.getInt(ID_ENTIDAD);
        alarms = (ArrayList<AlarmAgenda>)extras.getSerializable(AGENDAS);
		alarmListAdapter = new AlarmAgendaListAdapter(ActivityHomeAlarmsAgendas.this,
				alarms, res);
		alarmsList = (ListView) findViewById(R.id.agendaList);
		alarmsList.setAdapter(alarmListAdapter);
		
		View goToNewAgenda = findViewById(R.id.goToNewAgenda);
		goToNewAgenda.setOnClickListener(new GoToNewAgenda(this));
    }
    
    private static class GoToNewAgenda implements OnClickListener {
		private ActivityHomeAlarmsAgendas activity;
		
		public GoToNewAgenda(ActivityHomeAlarmsAgendas activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity, ActivityHomeAlarmAgendaEdit.class);
			intent.putExtra(ActivityHomeAlarmAgendaEdit.ID_ENTIDAD, activity.idEntidad);
			this.activity.startActivity(intent);
			this.activity.finish();
		}
	}
    
    //TODO reeler la lista cuando vuelve
    
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
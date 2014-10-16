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
import com.tdil.thalamus.android.rest.model.LightAgenda;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeLightsAgendas extends HomeActivity {

	public static final String ID_ENTIDAD = "ID_ENTIDAD";
	public static final String ID_LUZ = "ID_LUZ";
	public static final String AGENDAS = "AGENDAS";
	
	private int idEntidad;
	private String idLuz;
	private LightAgendaListAdapter alarmListAdapter;
	public ArrayList<LightAgenda> alarms = new ArrayList<LightAgenda>();
	private ListView alarmsList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_light_agendas);
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
        idEntidad = extras.getInt(ID_ENTIDAD);
        idLuz = extras.getString(ID_LUZ);
        alarms = (ArrayList<LightAgenda>)extras.getSerializable(AGENDAS);
		alarmListAdapter = new LightAgendaListAdapter(ActivityHomeLightsAgendas.this,
				alarms, res);
		alarmsList = (ListView) findViewById(R.id.agendaList);
		alarmsList.setAdapter(alarmListAdapter);
		View goToNewAgenda = findViewById(R.id.goToNewAgenda);
		goToNewAgenda.setOnClickListener(new GoToNewAgenda(this));
    }
    
    private static class GoToNewAgenda implements OnClickListener {
		private ActivityHomeLightsAgendas activity;
		
		public GoToNewAgenda(ActivityHomeLightsAgendas activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity, ActivityHomeLightAgendaEdit.class);
			intent.putExtra(ActivityHomeLightAgendaEdit.ID_ENTIDAD, activity.idEntidad);
			intent.putExtra(ActivityHomeLightAgendaEdit.ID_LUZ, activity.idLuz);
			this.activity.startActivity(intent);
			this.activity.finish();
		}
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

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getIdLuz() {
		return idLuz;
	}

	public void setIdLuz(String idLuz) {
		this.idLuz = idLuz;
	}
}
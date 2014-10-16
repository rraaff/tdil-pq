package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.Intent;

import com.tdil.thalamus.android.ActivityRestClientObserver;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.AlarmAgenda;
import com.tdil.thalamus.android.rest.model.AlarmAgendaCollection;
import com.tdil.thalamus.android.rest.model.LightAgenda;
import com.tdil.thalamus.android.rest.model.LightAgendaCollection;

public class AgendasFacade {

	public static void startAlarmAgendasListActivity(ActivityHomeAlarmDashboard activity) {
		new RESTClientTaskOpt<AlarmAgendaCollection>(activity, HttpMethod.GET, getAlarmAgendasListObserver(activity), RESTConstants.GET_ALARM_AGENDAS, 
				new RestParams(RESTConstants.ID_ENTIDAD, activity.getAlarm().getIdEntidad()),null, 
				AlarmAgendaCollection.class).execute((Void) null);
	}

	private static IRestClientObserver getAlarmAgendasListObserver(final ActivityHomeAlarmDashboard pActivity) {
		return new ActivityRestClientObserver(pActivity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				AlarmAgendaCollection pos = ((RESTClientTaskOpt<AlarmAgendaCollection>)restClientTask).getCastedResult();
				Intent intent = new Intent(activity.getBaseContext(), ActivityHomeAlarmsAgendas.class);
				intent.putExtra(ActivityHomeAlarmsAgendas.ID_ENTIDAD, pActivity.getAlarm().getIdEntidad());
				intent.putExtra(ActivityHomeAlarmsAgendas.AGENDAS, new ArrayList<AlarmAgenda>(pos.getList()));
				activity.startActivity(intent);
			}
		};
	}
	
	public static void startLightAgendasListActivity(ActivityHomeLightDashboard activity) {
		new RESTClientTaskOpt<LightAgendaCollection>(activity, HttpMethod.GET, getLightAgendasListObserver(activity), RESTConstants.GET_LIGHT_AGENDAS, 
				new RestParams(RESTConstants.ID_ENTIDAD, activity.getLight().getIdEntidad())
					.put(RESTConstants.ID_LUZ, activity.getLight().getIdLuz()),null, 
					LightAgendaCollection.class).execute((Void) null);
	}

	private static IRestClientObserver getLightAgendasListObserver(final ActivityHomeLightDashboard pActivity) {
		return new ActivityRestClientObserver(pActivity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				LightAgendaCollection pos = ((RESTClientTaskOpt<LightAgendaCollection>)restClientTask).getCastedResult();
				Intent intent = new Intent(activity.getBaseContext(), ActivityHomeLightsAgendas.class);
				intent.putExtra(ActivityHomeLightsAgendas.ID_ENTIDAD, pActivity.getLight().getIdEntidad());
				intent.putExtra(ActivityHomeLightsAgendas.ID_LUZ, String.valueOf(pActivity.getLight().getIdLuz()));
				intent.putExtra(ActivityHomeLightsAgendas.AGENDAS, new ArrayList<LightAgenda>(pos.getList()));
				activity.startActivity(intent);
			}
		};
	}
}

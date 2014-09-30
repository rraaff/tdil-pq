package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.ChangeLog;
import com.tdil.thalamus.android.rest.model.LogCollection;
import com.tdil.thalamus.android.utils.Messages;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeAlarmLog extends HomeActivity {

	private int identidad;
	public static final String IDENTIDAD = "IDENTIDAD";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_alarm_log);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
		Bundle extras = getIntent().getExtras();
		identidad = extras.getInt(IDENTIDAD);
		final ListView list = (ListView) findViewById(R.id.alarmLogList);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				LogCollection col = gson.fromJson(task.getResult(),
						LogCollection.class);
				ArrayList<ChangeLog> logs = new ArrayList<ChangeLog>(col.getLogs());
				Resources res = getResources();
				AlarmLogListAdapter adapter = new AlarmLogListAdapter(ActivityHomeAlarmLog.this,
						logs, res);
				list.setAdapter(adapter);
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ActivityHomeAlarmLog.this);
			}
		}, RESTConstants.LOG_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(identidad)), null).execute((Void) null);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
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
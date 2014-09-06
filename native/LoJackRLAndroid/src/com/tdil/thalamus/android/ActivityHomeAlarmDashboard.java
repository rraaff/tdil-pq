package com.tdil.thalamus.android;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.logic.AlarmsLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.AlarmJobStatus;
import com.tdil.thalamus.android.rest.model.AlarmJobStatusCollection;
import com.tdil.thalamus.android.utils.Messages;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeAlarmDashboard extends LoJackWithProductMenuActivity implements IAlarmsActivity {

	private Alarm alarm;
	private boolean hasMore;
	private boolean ignore = true;
	
	public static final String ALARM = "ALARM";
	public static final String HAS_MORE = "HAS_MORE";
	private ToggleButton activateDeactivate;
	
	private int times = 0;
	private static int max_times = 10;
	private static long sleep = 2000;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_alarms_dashboard);
        HeaderLogic.installTabLogic(this);
        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
       
        alarm = (Alarm)extras.getSerializable(ALARM);
		
		hasMore = !"FALSE".equals((String)extras.getSerializable(HAS_MORE));
		
		activateDeactivate = (ToggleButton)findViewById(R.id.btnToggleAlarm);
		activateDeactivate.setOnCheckedChangeListener(new ToggleActivateListener(this));
		View viewlog = findViewById(R.id.goToAlarmViewLog);
		viewlog.setOnClickListener(new ViewAlarmLogListener(this));
		init();
    }
    
    public void init() {
		ToggleButton activateDeactivate = (ToggleButton)findViewById(R.id.btnToggleAlarm);
		TextView description = (TextView) findViewById(R.id.alarmDescription);
		description.setText(alarm.getDescription());
		
		TextView status = (TextView) findViewById(R.id.alarmStatus);
		status.setText(alarm.getStatus());
		if (alarm.isActive()) {
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
		} else {
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
		}
		TextView lastChange = (TextView) findViewById(R.id.lastChangeDate);
		lastChange.setText(alarm.getLastChangeDate());
		
		TextView lastChangeAction = (TextView) findViewById(R.id.lastChangeStatus);
		lastChangeAction.setText(alarm.getLastChangeAction());
		if (alarm.isActive()) {
			lastChangeAction.setTextColor(getResources().getColor(R.color.lst_itm_on));
		} else {
			lastChangeAction.setTextColor(getResources().getColor(R.color.lst_itm_off));
		}
		TextView lastChangeUser = (TextView) findViewById(R.id.lastChangeUser);
		lastChangeUser.setText(alarm.getLastChangeUser());

		activateDeactivate.setChecked(alarm.isActive());
		activateDeactivate.setEnabled(true);
		ignore = false;
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

    private class ToggleActivateListener implements OnCheckedChangeListener {
		private ActivityHomeAlarmDashboard activity;
		
		ToggleActivateListener(ActivityHomeAlarmDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!ignore) {
				activity.ignore = true;
				activity.toggleAlarmActivation(0);
			}
		}
	}
    
    private static class ViewAlarmLogListener implements OnClickListener {
		private ActivityHomeAlarmDashboard activity;
		
		ViewAlarmLogListener(ActivityHomeAlarmDashboard activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			activity.viewAlarmLog(0);
		}
	}
    
    public void toggleAlarmActivation(int mPosition) {
		AlarmsLogic.toggleAlarmActivation(this, mPosition);
	}
    
    public void viewAlarmLog(int mPosition) {
		Intent intent = new Intent(getBaseContext(), HomeLogAlarmActivity.class);
		intent.putExtra(HomeLogAlarmActivity.IDENTIDAD, alarm.getIdEntidad());
		startActivity(intent);
	}
    
	@Override
	public Alarm getAlarm(int i) {
		return alarm;
	}
	
	@Override
	public void loadAlarms() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AlarmCollection col = gson.fromJson(task.getResult(),
						AlarmCollection.class);
				for (Alarm a : col.getAlarms()) {
					if (a.getIdEntidad() == ActivityHomeAlarmDashboard.this.alarm.getIdEntidad()) {
						ActivityHomeAlarmDashboard.this.alarm = a;
						ActivityHomeAlarmDashboard.this.init();
					}
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ActivityHomeAlarmDashboard.this);
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
	}

	public void startAlarmsBackgroundJob() {
		activateDeactivate.setEnabled(false);
		times = 0;
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AlarmJobStatusCollection col = gson.fromJson(task.getResult(),
						AlarmJobStatusCollection.class);
				boolean found = false;
				for (AlarmJobStatus jobStatus : col.getStatus()) {
					if (jobStatus.getIdEntidad() == ActivityHomeAlarmDashboard.this.alarm.getIdEntidad()) {
						found = true;
						if (jobStatus.isArmada()) {
							ActivityHomeAlarmDashboard.this.alarm.setStatus(Alarm.ACTIVE);
						} else {
							ActivityHomeAlarmDashboard.this.alarm.setStatus(Alarm.INACTIVE);
						}
					}
				}
				if (!found) {
					if (times < max_times) {
						times++;
						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {}
						ActivityHomeAlarmDashboard.this.startAlarmsBackgroundJob();
					} else {
						ActivityHomeAlarmDashboard.this.loadAlarms();
						ActivityHomeAlarmDashboard.this.ignore = false;
					}
				} else {
					ActivityHomeAlarmDashboard.this.init();
					ActivityHomeAlarmDashboard.this.ignore = false;
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ActivityHomeAlarmDashboard.this);
			}
		}, RESTConstants.ALARM_STATUS, new RestParams(), null).execute();
	}
}
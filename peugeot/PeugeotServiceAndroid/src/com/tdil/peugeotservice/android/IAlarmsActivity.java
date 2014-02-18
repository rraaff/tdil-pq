package com.tdil.peugeotservice.android;

import com.tdil.peugeotservice.android.rest.model.Alarm;

public interface IAlarmsActivity {

	public void toggleAlarmActivation(int mPosition);
	public void viewAlarmLog(int mPosition);
	
	public Alarm getAlarm(int i);
	
	public void startAlarmsBackgroundJob();
	public void loadAlarms();
}

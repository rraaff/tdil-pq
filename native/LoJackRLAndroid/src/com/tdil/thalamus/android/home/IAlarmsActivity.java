package com.tdil.thalamus.android.home;

import com.tdil.thalamus.android.rest.model.Alarm;

public interface IAlarmsActivity {

	public void toggleAlarmActivation(int mPosition);
	public void viewAlarmLog(int mPosition);
	
	public Alarm getAlarm(int i);
	
	public void startAlarmsBackgroundJob();
	public void loadAlarms();
}

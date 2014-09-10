package com.tdil.thalamus.android.home;

import com.tdil.thalamus.android.LoJackWithProductMenuActivity;

public abstract class HomeActivity extends LoJackWithProductMenuActivity{

	public abstract boolean  isAlarmsTab();
	public abstract boolean isLightsTab();
	public abstract boolean isCamerasTab();
	
}

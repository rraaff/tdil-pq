package com.tdil.peugeotservice.android;

import com.tdil.peugeotservice.android.rest.model.Light;

public interface ILightsActivity {

	public void toggleLightActivation(int mPosition);
	public void viewLightLog(int mPosition);
	public void toggleLightRandom(int mPosition);
	public Light getLight(int i);
	public void startLightsBackgroundJob();
	public void loadLights();
}

package com.tdil.thalamus.android;

import java.util.List;

import com.tdil.thalamus.android.rest.model.Light;

public interface ILightsActivity {

	public void toggleLightActivation(int mPosition);
	public void viewLightLog(int mPosition);
	public void toggleLightRandom(int mPosition);
	public List<Light> getLights();
	public void startLightsBackgroundJob();
	public void loadLights();
}

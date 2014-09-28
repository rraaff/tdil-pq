package com.tdil.thalamus.android.car;

import android.view.View;

import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.menu.cars.ToggleCarsMenuOnClick;

public final class CarsParkedModeOnClick implements View.OnClickListener {
	private ActivityCars activity;
	private ToggleCarsMenuOnClick menuListener;
	
	public CarsParkedModeOnClick(ActivityCars activity, ToggleCarsMenuOnClick menuListener) {
		super();
		this.activity = activity;
		this.menuListener = menuListener;
	}

	@Override
	public void onClick(View v) {
		menuListener.toggle();
		HeaderLogic.handleParkedModeAccess(activity);
	}
}
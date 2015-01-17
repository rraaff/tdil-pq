package com.tdil.peugeotservice.android.car;

import android.view.View;

import com.tdil.peugeotservice.android.car.menu.ToggleCarsMenuOnClick;

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
	}
}
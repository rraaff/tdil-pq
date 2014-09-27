package com.tdil.thalamus.android.car;

import android.view.View;

import com.tdil.thalamus.android.header.logic.HeaderLogic;

public final class CarsParkedModeOnClick implements View.OnClickListener {
	private ActivityCars activity;
	
	public CarsParkedModeOnClick(ActivityCars activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		HeaderLogic.handleParkedModeAccess(activity);
	}
}
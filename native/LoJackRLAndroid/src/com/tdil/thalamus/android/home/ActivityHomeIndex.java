package com.tdil.thalamus.android.home;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;

public class ActivityHomeIndex extends HomeActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_index);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);

        View v = findViewById(R.id.goAlarmsImageView);
        v.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadAlarms(ActivityHomeIndex.this);
							}
						});
						
		v = findViewById(R.id.goLightsImageView);
        v.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadLights(ActivityHomeIndex.this);
							}
						});
		v = findViewById(R.id.goCamerasImageView);
        v.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadCameras(ActivityHomeIndex.this);
							}
						});
    }
	
	@Override
	public boolean isAlarmsTab() {
		return false;
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
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
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);
        track("Seccion HOME", "/home/index");
        
        View v = findViewById(R.id.goAlarmsImageView);
        View.OnClickListener goAlarms = new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadAlarms(ActivityHomeIndex.this);
							}
						};
		v.setOnClickListener(goAlarms);
		v = findViewById(R.id.alarmsRow);
		if (v != null) {
			v.setOnClickListener(goAlarms);
		}
		v = findViewById(R.id.alarmsImageView);
		if (v != null) {
			v.setOnClickListener(goAlarms);
		}
		v = findViewById(R.id.alarmsTextRow);
		if (v != null) {
			v.setOnClickListener(goAlarms);
		}
		v = findViewById(R.id.alarmsTextRow1);
		if (v != null) {
			v.setOnClickListener(goAlarms);
		}
		v = findViewById(R.id.alarmsTitle);
		if (v != null) {
			v.setOnClickListener(goAlarms);
		}
		v = findViewById(R.id.alarmsSubtitle);
		if (v != null) {
			v.setOnClickListener(goAlarms);
		}
						
		v = findViewById(R.id.goLightsImageView);
        View.OnClickListener goLights = new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadLights(ActivityHomeIndex.this);
							}
						};
		v.setOnClickListener(goLights);
		v = findViewById(R.id.lightsRow);
		if (v != null) {
			v.setOnClickListener(goLights);
		}
		v = findViewById(R.id.lightsImageView);
		if (v != null) {
			v.setOnClickListener(goLights);
		}
		v = findViewById(R.id.lightsTextRow);
		if (v != null) {
			v.setOnClickListener(goLights);
		}
		v = findViewById(R.id.lightsTextRow1);
		if (v != null) {
			v.setOnClickListener(goLights);
		}
		v = findViewById(R.id.lightsTitle);
		if (v != null) {
			v.setOnClickListener(goLights);
		}
		v = findViewById(R.id.lightsSubtitle);
		if (v != null) {
			v.setOnClickListener(goLights);
		}
		
		v = findViewById(R.id.goCamerasImageView);
        View.OnClickListener goCameras = new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadCameras(ActivityHomeIndex.this);
							}
						};
		v.setOnClickListener(goCameras);
		v = findViewById(R.id.camerasRow);
		if (v != null) {
			v.setOnClickListener(goCameras);
		}
		v = findViewById(R.id.camerasImageView);
		if (v != null) {
			v.setOnClickListener(goCameras);
		}
		v = findViewById(R.id.camerasTextRow);
		if (v != null) {
			v.setOnClickListener(goCameras);
		}
		v = findViewById(R.id.camerasTextRow1);
		if (v != null) {
			v.setOnClickListener(goCameras);
		}
		v = findViewById(R.id.camerasTitle);
		if (v != null) {
			v.setOnClickListener(goCameras);
		}
		v = findViewById(R.id.camerasSubtitle);
		if (v != null) {
			v.setOnClickListener(goCameras);
		}
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
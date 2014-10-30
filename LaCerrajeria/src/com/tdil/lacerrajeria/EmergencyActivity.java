package com.tdil.lacerrajeria;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class EmergencyActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emergency_activity);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setTitle("Urgencias");

		View tel1 = findViewById(R.id.sebastianCallButton);
		tel1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Tracker t = getTracker();
			        t.send(new HitBuilders.EventBuilder("Sebastian", "call").build());
					String uri = "tel:0221156412772";
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
					EmergencyActivity.this.startActivity(callIntent);
				} catch (Exception e) {
					Toast.makeText(EmergencyActivity.this, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		View tel2 = findViewById(R.id.fernandoCallButton);
		tel2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Tracker t = getTracker();
			        t.send(new HitBuilders.EventBuilder("Fernando", "call").build());
					String uri = "tel:0221156412772";
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
					EmergencyActivity.this.startActivity(callIntent);
				} catch (Exception e) {
					Toast.makeText(EmergencyActivity.this, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		findViewById(R.id.backButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					EmergencyActivity.this.finish();
				}
			});
	}

}

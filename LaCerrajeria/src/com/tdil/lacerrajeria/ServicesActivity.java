package com.tdil.lacerrajeria;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ServicesActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setTitle("Servicios");

		findViewById(R.id.backButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ServicesActivity.this.finish();
				}
			});
	}

}

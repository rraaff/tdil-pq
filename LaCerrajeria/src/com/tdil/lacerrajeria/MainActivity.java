package com.tdil.lacerrajeria;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

public class MainActivity extends BaseActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setTitle("La Cerrajeria");
		
	}

	



}

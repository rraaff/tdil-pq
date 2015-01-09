package com.tdil.thalamus.android.car.parkedmode;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeHistoryLogBean;

public class ActivityParkedModeMapView extends LoJackWithProductMenuActivity {

	public static final String POSITION = "POSITION";
	private ParkedModeHistoryLogBean parkedModeHistoryLogBean;
//	private MapView mapView;
	private GoogleMap map;
	private SupportMapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locar_pm_position);
		customizeActionBar(true);
		HeaderLogic.installTabLogic(this);
		
		Bundle extras = getIntent().getExtras();
		parkedModeHistoryLogBean = (ParkedModeHistoryLogBean)extras.getSerializable(POSITION);

		// Gets to GoogleMap from the MapView and does initialization stuff
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview);
		mapFragment.onCreate(savedInstanceState);
		map = mapFragment.getMap();
		// map.getUiSettings().setMyLocationButtonEnabled(false);
//		map.setMyLocationEnabled(true);

		// Needs to call MapsInitializer before doing any CameraUpdateFactory
		// calls
		MapsInitializer.initialize(this);

		LatLng latLng = new LatLng(Double.parseDouble(parkedModeHistoryLogBean.getLatitud()),Double.parseDouble(parkedModeHistoryLogBean.getLongitud()));
		Marker m = map.addMarker(new MarkerOptions()
	        .position(latLng)
	        .title(parkedModeHistoryLogBean.getFecha()));
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
		map.animateCamera(cameraUpdate);

		((View)findViewById(R.id.pmPositionDetailBack)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
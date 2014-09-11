package com.tdil.thalamus.android.car;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.places.LocarRestClientObserver;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.prevent.VehicleBean;
import com.tdil.thalamus.android.rest.model.prevent.VehicleCollection;



public class ActivityCars extends ActionBarActivity {

	private MapView mapView;
	
	private List<Marker> vehiclesMarkers = new ArrayList<Marker>();
	
	private LocarRestClientObserver positionsObserver = new LocarRestClientObserver(this) {
		
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			// validar la respuesta
			VehicleCollection vehicles = gson.fromJson(restClientTask.getResult(), VehicleCollection.class);
			activity.updatePositionsLocations(vehicles);
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_main);
        
        mapView = (MapView) this.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        GoogleMap map = mapView.getMap();
        //map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
           MapsInitializer.initialize(this);

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-34.6093546,-58.51752859999999), 16);
        map.animateCamera(cameraUpdate);
       
        ((View)findViewById(R.id.carPositionsButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new RESTClientTask(ActivityCars.this, HttpMethod.GET, positionsObserver, RESTConstants.GET_VEHICLES, null,null).execute((Void) null);
			}
		});
//        ((View)findViewById(R.id.carPositionsButton)).setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//		        final Dialog dialog = new Dialog(ActivityCars.this);
//				dialog.setContentView(R.layout.selection_vehicle_dialog);
//				dialog.setTitle("Title...");
//		
//				// set the custom dialog components - text, image and button
//				TextView text = (TextView) dialog.findViewById(R.id.text);
//				text.setText("Android custom dialog example!");
//				ImageView image = (ImageView) dialog.findViewById(R.id.image);
//				image.setImageResource(R.drawable.ic_launcher);
//		
//				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//				// if button is clicked, close the custom dialog
//				dialogButton.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						dialog.dismiss();
//					}
//				});
//				dialog.show();
//			}
//        });

    }
    
    public void centerMap(List<LatLng> copiedPoints) {
        double minLat = Integer.MAX_VALUE;
        double maxLat = Integer.MIN_VALUE;
        double minLon = Integer.MAX_VALUE;
        double maxLon = Integer.MIN_VALUE;
        for (LatLng point : copiedPoints) {
            maxLat = Math.max(point.latitude, maxLat);
            minLat = Math.min(point.latitude, minLat);
            maxLon = Math.max(point.longitude, maxLon);
            minLon = Math.min(point.longitude, minLon);
        }
        final LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(maxLat, maxLon)).include(new LatLng(minLat, minLon)).build();
        mapView.getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
    }
    
    protected void updatePositionsLocations(VehicleCollection vehicles) {
    	for (Marker m : vehiclesMarkers) {
    		m.remove();
    		m.setVisible(false);
    	}
    	vehiclesMarkers.clear();
    	List<LatLng> points = new ArrayList<LatLng>();
		for (VehicleBean vehicleBean : vehicles.getList()) {
			LatLng latLng = new LatLng(Double.parseDouble(vehicleBean.getLatitude()),Double.parseDouble(vehicleBean.getLongitude()));
			points.add(latLng);
			Marker m = mapView.getMap().addMarker(new MarkerOptions()
		        .position(latLng)
		        .title(vehicleBean.getDescription()));
			 vehiclesMarkers.add(m);
		}
		if (vehicles.getList().size() == 1) {
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(points.get(0), 16);
			mapView.getMap().animateCamera(cameraUpdate);
		} else {
			centerMap(points);
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
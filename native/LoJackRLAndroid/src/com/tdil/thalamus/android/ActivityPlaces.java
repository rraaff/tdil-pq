package com.tdil.thalamus.android;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.DocumentTypeCollection;
import com.tdil.thalamus.android.rest.model.poi.PoiCollection;
import com.tdil.thalamus.android.rest.model.poi.PointOfInterestBean;
import com.tdil.thalamus.android.utils.Messages;



public class ActivityPlaces extends ActionBarActivity {

	private MapView mapView;
	private List<Marker> currentMarkers = new ArrayList<Marker>();
	
	private boolean placesParkings = false;
	private boolean placesPetrols = false;
	private boolean placesLojack = false;

	private UpdateMapObserver observer = new UpdateMapObserver(this);
	
	static final class UpdateMapObserver implements IRestClientObserver {
		private ActivityPlaces activity;
		public UpdateMapObserver(ActivityPlaces activity) {
			super();
			this.activity = activity;
		}
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			PoiCollection col = gson.fromJson(restClientTask.getResult(), PoiCollection.class);
			activity.updateMap(col);
		}
		@Override
		public void error(IRestClientTask task) {
			Messages.connectionErrorMessage(activity);
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loplaces_main);

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
        ((View)findViewById(R.id.placesLocateButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapView.getMap().addMarker(new MarkerOptions()
		        .position(new LatLng(-34.6093546,-58.51752859999999))
		        .title("Hello world"));
				
			}
		});
        
        ((View)findViewById(R.id.placesParkingsButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				placesParkings = !placesParkings;
				reloadPois();
				
			}
		}); 
        ((View)findViewById(R.id.placesPetrolButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				placesPetrols = !placesPetrols;
				reloadPois();
				
			}
		});
        ((View)findViewById(R.id.placesLojackButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				placesLojack = !placesLojack;
				reloadPois();
				
			}
		});
    }

    protected void reloadPois() {
    	for (Marker m : currentMarkers) {
    		m.remove();
    		m.setVisible(false);
    	}
    	currentMarkers = new ArrayList<Marker>();
    	if (!placesParkings && !placesPetrols && !placesLojack) {
    		return;
    	}
    	new RESTClientTask(this, HttpMethod.GET, observer, RESTConstants.POIS, new RestParams(RESTConstants.P_POI_TYPES, getPoisTypes()),null).execute((Void) null);
	}
    
    private void updateMap(PoiCollection col) {
    	System.out.println(col.getList().size());
		 System.out.println(col);
		 for (PointOfInterestBean poi : col.getList()) {
			 Marker m = mapView.getMap().addMarker(new MarkerOptions()
		        .position(new LatLng(Double.parseDouble(poi.getLat()),Double.parseDouble(poi.getLon())))
		        .title("Hello world"));
			 currentMarkers.add(m);
		 }
    }

	private String getPoisTypes() {
		StringBuilder sb = new StringBuilder();
		if (placesParkings) {
			sb.append("1,");
		}
		if (placesPetrols) {
			sb.append("2,");
		}
		if (placesLojack) {
			sb.append("3,");
		}
		return sb.toString();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mapView.onDestroy();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
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
package com.tdil.thalamus.android.places;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackLoggedActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.poi.PoiCollection;
import com.tdil.thalamus.android.rest.model.poi.PointOfInterestBean;
import com.tdil.thalamus.android.utils.Messages;



public class ActivityPlaces extends LoJackLoggedActivity {

	private MapView mapView;
	
	private PoiCollection parkings = null;
	private PoiCollection petrols = null;
	private PoiCollection lojack = null;
	
	private List<Marker> currentParkings = new ArrayList<Marker>();
	private List<Marker> currentPetrols = new ArrayList<Marker>();
	private List<Marker> currentLojack = new ArrayList<Marker>();
	
	private boolean placesParkings = false;
	private boolean placesPetrols = false;
	private boolean placesLojack = false;

	private UpdateParkingsMapObserver updateParkingsMapObserver = new UpdateParkingsMapObserver(this);
	private UpdatePetrolsMapObserver updatePetrolsMapObserver= new UpdatePetrolsMapObserver(this);
	private UpdateLojackMapObserver updateLojackMapObserver = new UpdateLojackMapObserver(this);
	
	static final class UpdateParkingsMapObserver implements IRestClientObserver {
		private ActivityPlaces activity;
		public UpdateParkingsMapObserver(ActivityPlaces activity) {
			super();
			this.activity = activity;
		}
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			this.activity.parkings = gson.fromJson(restClientTask.getResult(), PoiCollection.class);
			activity.updateParkingsMap(this.activity.parkings);
		}
		@Override
		public void error(IRestClientTask task) {
			Messages.connectionErrorMessage(activity);
		}
	}
	static final class UpdatePetrolsMapObserver implements IRestClientObserver {
		private ActivityPlaces activity;
		public UpdatePetrolsMapObserver(ActivityPlaces activity) {
			super();
			this.activity = activity;
		}
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			this.activity.petrols = gson.fromJson(restClientTask.getResult(), PoiCollection.class);
			activity.updatePetrolsMap(this.activity.petrols);
		}
		@Override
		public void error(IRestClientTask task) {
			Messages.connectionErrorMessage(activity);
		}
	}
	static final class UpdateLojackMapObserver implements IRestClientObserver {
		private ActivityPlaces activity;
		public UpdateLojackMapObserver(ActivityPlaces activity) {
			super();
			this.activity = activity;
		}
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			this.activity.lojack = gson.fromJson(restClientTask.getResult(), PoiCollection.class);
			activity.updateLojackMap(this.activity.lojack);
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
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
        
        mapView = (MapView) this.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        GoogleMap map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
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
		        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
		        .title("Mi ubicacion"));
				
			}
		});
        
        final ImageButton placesParkingsButton = (ImageButton)findViewById(R.id.placesParkingsButton);
		placesParkingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				placesParkings = !placesParkings;
				if (!placesParkings) {
					for (Marker m : currentParkings) {
			    		m.remove();
			    		m.setVisible(false);
			    	}
					currentParkings.clear();
					placesParkingsButton.setBackgroundResource(R.drawable.ic_places_parkings_off);
				} else {
					if (parkings == null) {
						reloadPois(updateParkingsMapObserver, "1");
					} else {
						updateParkingsMap(parkings);
					}
					placesParkingsButton.setBackgroundResource(R.drawable.ic_places_parkings_active);
				}
				
			}
		}); 
		final ImageButton placesPetrolButton = (ImageButton)findViewById(R.id.placesPetrolButton);
		placesPetrolButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				placesPetrols = !placesPetrols;
				if (!placesPetrols) {
					for (Marker m : currentPetrols) {
			    		m.remove();
			    		m.setVisible(false);
			    	}
					currentPetrols.clear();
					placesPetrolButton.setBackgroundResource(R.drawable.ic_places_gasstations_off);
				} else {
					if (petrols == null) {
						reloadPois(updatePetrolsMapObserver, "2");
					} else {
						updatePetrolsMap(petrols);
					}
					placesPetrolButton.setBackgroundResource(R.drawable.ic_places_gasstations_active);
				}
				
			}
		});
		final ImageButton placesLojackButton = (ImageButton)findViewById(R.id.placesLojackButton);
		placesLojackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				placesLojack = !placesLojack;
				if (!placesLojack) {
					for (Marker m : currentLojack) {
			    		m.remove();
			    		m.setVisible(false);
			    	}
					currentLojack.clear();
					placesLojackButton.setBackgroundResource(R.drawable.ic_places_lojack_off);
				} else {
					if (lojack == null) {
						reloadPois(updateLojackMapObserver, "3");
					} else {
						updateLojackMap(lojack);
					}
					placesLojackButton.setBackgroundResource(R.drawable.ic_places_lojack_active);
				}
				
			}
		});
    }

    protected void reloadPois(IRestClientObserver observer, String type) {
    	new RESTClientTask(this, HttpMethod.GET, observer, RESTConstants.POIS, new RestParams(RESTConstants.P_POI_TYPES, type),null).executeSerial((Void) null);
	}
    
    private void updateParkingsMap(PoiCollection col) {
		 for (PointOfInterestBean poi : col.getList()) {
			 Marker m = mapView.getMap().addMarker(new MarkerOptions()
		        .position(new LatLng(Double.parseDouble(poi.getLat()),Double.parseDouble(poi.getLon())))
		        .title(poi.getDesc())
		        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			 currentParkings.add(m);
		 }
    }
    private void updatePetrolsMap(PoiCollection col) {
		 for (PointOfInterestBean poi : col.getList()) {
			 Marker m = mapView.getMap().addMarker(new MarkerOptions()
		        .position(new LatLng(Double.parseDouble(poi.getLat()),Double.parseDouble(poi.getLon())))
		        .title(poi.getDesc())
		        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			 currentPetrols.add(m);
		 }
   }
   private void updateLojackMap(PoiCollection col) {
		 for (PointOfInterestBean poi : col.getList()) {
			 Marker m = mapView.getMap().addMarker(new MarkerOptions()
		        .position(new LatLng(Double.parseDouble(poi.getLat()),Double.parseDouble(poi.getLon())))
		        .title(poi.getDesc())
		        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			 currentLojack.add(m);
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

}
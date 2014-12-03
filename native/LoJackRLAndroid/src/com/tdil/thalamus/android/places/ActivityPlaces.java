package com.tdil.thalamus.android.places;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackLoggedActivity;
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

public class ActivityPlaces extends LoJackLoggedActivity implements OnInfoWindowClickListener {

	private MapView mapView;
	
	private Marker currentPos = null;

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
	private UpdatePetrolsMapObserver updatePetrolsMapObserver = new UpdatePetrolsMapObserver(this);
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
	public void onInfoWindowClick(final Marker marker) {
		String title = marker.getTitle();
		if (isNumeric(title) && title.length() >= 8) {
			// handle click here
			try {
				String uri = "tel:" + title;
				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
				this.startActivity(callIntent);
			} catch (Exception e) {
				Toast.makeText(this, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
			}
		}
	}

	private static final boolean isNumeric(final String s) {
		final char[] numbers = s.toCharArray();
		for (int x = 0; x < numbers.length; x++) {
			final char c = numbers[x];
			if ((c >= '0') && (c <= '9'))
				continue;
			return false; // invalid
		}
		return true; // valid
	}
	
	public void centerAtPosition(double updatedLatitude, double updatedLongitude) {
		if (currentPos != null) {
			currentPos.setVisible(false);
			currentPos.remove();
		}
		currentPos = mapView.getMap().addMarker(new MarkerOptions().position(new LatLng(updatedLatitude, updatedLongitude))
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).title("Mi ubicacion"));
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(updatedLatitude, updatedLongitude), 16);
		mapView.getMap().animateCamera(cameraUpdate);
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
		map.setOnInfoWindowClickListener(this);

		map.getUiSettings().setMyLocationButtonEnabled(false);
		map.setMyLocationEnabled(true);

		// Needs to call MapsInitializer before doing any CameraUpdateFactory
		// calls
		MapsInitializer.initialize(this);

		// Updates the location and zoom of the MapView
		final LocationManager manager = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
		Location lastKnownLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (lastKnownLocation != null) {
			centerAtPosition(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
		} else {
			Location lastKnownLocation1 = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    	if (lastKnownLocation1 != null) {
				centerAtPosition(lastKnownLocation1.getLatitude(), lastKnownLocation1.getLongitude());
	    	} 
		}
		((View) findViewById(R.id.placesLocateButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final LocationManager manager = (LocationManager) ActivityPlaces.this.getSystemService( Context.LOCATION_SERVICE );
			    if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
			    	final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPlaces.this);
		    	    builder.setMessage("Desea encender el GPS?")
		    	           .setCancelable(false)
		    	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		    	               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
		    	            	   ActivityPlaces.this.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		    	                   manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0,
		    	                		   new UpdateLocationListener(manager, ActivityPlaces.this));
		    	               }
		    	           })
		    	           .setNegativeButton("No", new DialogInterface.OnClickListener() {
		    	               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
		    	                    dialog.cancel();
		    	               }
		    	           });
		    	    final AlertDialog alert = builder.create();
		    	    alert.show();
			    } else {
			    	manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0,
	                		   new UpdateLocationListener(manager, ActivityPlaces.this));
			    }
			}
		});

		final ImageButton placesParkingsButton = (ImageButton) findViewById(R.id.placesParkingsButton);
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
		final ImageButton placesPetrolButton = (ImageButton) findViewById(R.id.placesPetrolButton);
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
		final ImageButton placesLojackButton = (ImageButton) findViewById(R.id.placesLojackButton);
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
		new RESTClientTask(this, HttpMethod.GET, observer, RESTConstants.POIS, new RestParams(RESTConstants.P_POI_TYPES, type), null)
				.executeSerial((Void) null);
	}

	private void updateParkingsMap(PoiCollection col) {
		for (PointOfInterestBean poi : col.getList()) {
			Marker m = mapView.getMap().addMarker(
					new MarkerOptions().position(new LatLng(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon())))
							.title(poi.getDesc()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			currentParkings.add(m);
		}
	}

	private void updatePetrolsMap(PoiCollection col) {
		for (PointOfInterestBean poi : col.getList()) {
			Marker m = mapView.getMap().addMarker(
					new MarkerOptions().position(new LatLng(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon())))
							.title(poi.getDesc()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			currentPetrols.add(m);
		}
	}

	private void updateLojackMap(PoiCollection col) {
		for (PointOfInterestBean poi : col.getList()) {
			Marker m = mapView.getMap().addMarker(
					new MarkerOptions().position(new LatLng(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon())))
							.title(poi.getDesc()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
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
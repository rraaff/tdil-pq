package com.tdil.thalamus.android.places;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackLoggedActivity;
import com.tdil.thalamus.android.cache.InternalCache;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.CachedRestClientTask;
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

	public static final String PARKINGS = "1";
	public static final String PETROL = "2";
	public static final String LJ = "3";
	public static final int PARKINGS_INT = 1;
	public static final int PETROL_INT = 2;
	public static final int LJ_INT = 3;


	private ClusterManager<PlacesItem> mClusterManager;
	private MapView mapView;
	
	private Marker currentPos = null;

	private PoiCollection parkings = null;
	private PoiCollection petrols = null;
	private PoiCollection lojack = null;

	private List<Marker> currentParkings = new ArrayList<Marker>();
	private List<PlacesItem> currentParkingsItems = new ArrayList<PlacesItem>();
	private Map<Marker, String> currentMarkerToPhones = new HashMap<Marker, String>();
	
	private List<Marker> currentPetrols = new ArrayList<Marker>();
	private List<PlacesItem> currentPetrolsItems = new ArrayList<PlacesItem>();
	
	private List<Marker> currentLojack = new ArrayList<Marker>();
	private List<PlacesItem> currentLojackItems = new ArrayList<PlacesItem>();

	private boolean placesParkings = false;
	private boolean placesPetrols = false;
	private boolean placesLojack = false;

	private UpdateParkingsMapObserver updateParkingsMapObserver = new UpdateParkingsMapObserver(this);
	private UpdatePetrolsMapObserver updatePetrolsMapObserver = new UpdatePetrolsMapObserver(this);
	private UpdateLojackMapObserver updateLojackMapObserver = new UpdateLojackMapObserver(this);
	
	
	private Cluster<PlacesItem> clickedCluster;
	private PlacesItem clickedClusterItem;

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
			if (!(restClientTask instanceof CachedRestClientTask)) {
				InternalCache.put(activity, "poi-" +PARKINGS, restClientTask.getResult());
			}
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
			if (!(restClientTask instanceof CachedRestClientTask)) {
				InternalCache.put(activity, "poi-" +PETROL, restClientTask.getResult());
			}
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
			if (!(restClientTask instanceof CachedRestClientTask)) {
				InternalCache.put(activity, "poi-" +LJ, restClientTask.getResult());
			}
		}

		@Override
		public void error(IRestClientTask task) {
			Messages.connectionErrorMessage(activity);
		}
	}
	
	@Override
	public void onInfoWindowClick(final Marker marker) {
		String title = getTel(marker);
		if (title != null) {
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

	private String getTel(Marker marker) {
		if (currentMarkerToPhones.containsKey(marker)) {
			return currentMarkerToPhones.get(marker);
		}
		return null;
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
	
	private static final String getNumeric(final String s) {
		StringBuilder result = new StringBuilder();
		final char[] numbers = s.toCharArray();
		for (int x = 0; x < numbers.length; x++) {
			final char c = numbers[x];
			if ((c >= '0') && (c <= '9'))
				result.append(c);
		}
		return result.toString(); // valid
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
	
//	private void addItems() {
//
//	    // Set some lat/lng coordinates to start with.
//	    double lat = 51.5145160;
//	    double lng = -0.1270060;
//
//	    // Add ten cluster items in close proximity, for purposes of this example.
//	    for (int i = 0; i < 10; i++) {
//	        double offset = i / 60d;
//	        lat = lat + offset;
//	        lng = lng + offset;
//	        PlacesItem offsetItem = new PlacesItem(lat, lng);
//	        mClusterManager.addItem(offsetItem);
//	    }
//	}

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
		
		

	    // Position the map.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

	    // Initialize the manager with the context and the map.
	    // (Activity extends context, so we can pass 'this' in the constructor.)
	    mClusterManager = new ClusterManager<PlacesItem>(this, map);
	    mClusterManager.setRenderer(new DefaultClusterRenderer<PlacesItem>(this, map, mClusterManager) {
	    	@Override
	    	protected void onBeforeClusterItemRendered(PlacesItem item, MarkerOptions markerOptions) {
	    		if (item.getType() == PARKINGS_INT) {
	    			markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
	    		}
	    		if (item.getType() == PETROL_INT) {
	    			markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
	    		}
	    		if (item.getType() == LJ_INT) {
	    			markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
	    		}
	    		super.onBeforeClusterItemRendered(item, markerOptions);
	    	}
	    });
	    map.setInfoWindowAdapter(mClusterManager.getMarkerManager());
	    mClusterManager.getClusterMarkerCollection().setOnInfoWindowAdapter(new MyCustomAdapterForClusters());
	    mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new MyCustomAdapterForItems());
	    map.setOnMarkerClickListener(mClusterManager);
	    mClusterManager.setOnClusterClickListener(new OnClusterClickListener<PlacesItem>() {
	        @Override
	        public boolean onClusterClick(Cluster<PlacesItem> cluster) {
	            clickedCluster = cluster; // remember for use later in the Adapter
	            return false;
	        }
	    });
	    mClusterManager.setOnClusterItemClickListener(new OnClusterItemClickListener<PlacesItem>() {
	        @Override
	        public boolean onClusterItemClick(PlacesItem item) {
	            clickedClusterItem = item;
	            return false;
	        }
	    });

	    // Point the map's listeners at the listeners implemented by the cluster
	    // manager.
	    map.setOnCameraChangeListener(mClusterManager);
	    map.setOnMarkerClickListener(mClusterManager);

	    // Add cluster items (markers) to the cluster manager.
//	    addItems();

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
					mClusterManager.clearItems();
					reAddItems();
					placesParkingsButton.setBackgroundResource(R.drawable.ic_places_parkings_off);
				} else {
					if (parkings == null) {
						reloadPois(updateParkingsMapObserver, PARKINGS);
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
					mClusterManager.clearItems();
					reAddItems();
					placesPetrolButton.setBackgroundResource(R.drawable.ic_places_gasstations_off);
				} else {
					if (petrols == null) {
						reloadPois(updatePetrolsMapObserver, PETROL);
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
					mClusterManager.clearItems();
					reAddItems();
					placesLojackButton.setBackgroundResource(R.drawable.ic_places_lojack_off);
				} else {
					if (lojack == null) {
						reloadPois(updateLojackMapObserver, LJ);
					} else {
						updateLojackMap(lojack);
					}
					placesLojackButton.setBackgroundResource(R.drawable.ic_places_lojack_active);
				}

			}
		});
	}
	
	protected void reAddItems() {
		if (placesParkings) {
			updateParkingsMap(parkings);
		}
		if (placesPetrols) {
			updatePetrolsMap(petrols);
		}
		if (placesLojack) {
			updateLojackMap(lojack);
		}
		mClusterManager.cluster();
	}

	private class MyCustomAdapterForClusters implements InfoWindowAdapter {
	    @Override
	    public View getInfoContents(Marker marker) {
	    	View view = getLayoutInflater().inflate(R.layout.places_info_view, null);
	        if (clickedCluster != null) {
	            for (PlacesItem item : clickedCluster.getItems()) {
	                // Extract data from each item in the cluster as needed
	            }
	        }
	        // build your custom view
	        // ...
	        return view;
	    }
	    @Override
	    public View getInfoWindow(Marker arg0) {
	    	 return null;
	    }
	}
	
	private class MyCustomAdapterForItems implements InfoWindowAdapter {
	    @Override
	    public View getInfoContents(Marker marker) {
	    	View view = getLayoutInflater().inflate(R.layout.places_info_view, null);
	        if (clickedClusterItem != null) {
	            TextView tv = (TextView)view.findViewById(R.id.placesTitle);
	            tv.setText(clickedClusterItem.getDescription());
	            clickedClusterItem.setTel("08009995959");
	            if (clickedClusterItem.getTel() != null) {
	            	currentMarkerToPhones.put(marker, clickedClusterItem.getTel());
//	            	OnClickListener onClick = new CallOnClick(ActivityPlaces.this, clickedClusterItem.getTel());
//	            	tv.setOnClickListener(onClick);
//	            	view.setOnClickListener(onClick);
	            }
	        }
	        // build your custom view
	        // ...
	        return view;
	    }
	    @Override
	    public View getInfoWindow(Marker arg0) {
	    	 return null;
	    }
	}
	
//	private class CallOnClick implements OnClickListener {
//		
//		private ActivityPlaces activity;
//		private String tel;
//
//		public CallOnClick(ActivityPlaces activity, String tel) {
//			super();
//			this.activity = activity;
//			this.tel = tel;
//		}
//		
//		@Override
//		public void onClick(View v) {
//			try {
//				String uri = "tel:" + this.tel;
//				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
//				this.activity.startActivity(callIntent);
//			} catch (Exception e) {
//				Toast.makeText(this.activity, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
//			}
//		}
//	}

	protected void reloadPois(IRestClientObserver observer, String type) {
		String resultFromCache = InternalCache.get(this, "poi-" + type, 30);
		if (resultFromCache != null) {
			observer.sucess(new CachedRestClientTask(resultFromCache, 200));
		} else {
			new RESTClientTask(this, HttpMethod.GET, observer, RESTConstants.POIS, new RestParams(RESTConstants.P_POI_TYPES, type), null)
				.executeSerial((Void) null);
		}
	}

	private void updateParkingsMap(PoiCollection col) {
		for (PointOfInterestBean poi : col.getList()) {
			String desc = poi.getDesc();
			String tel = null;
			int start = desc.indexOf("<tel>");
			int end = desc.indexOf("</tel>");
			if (start != -1 && end != -1 && start < end) {
				tel = getNumeric(desc.substring(start + 5, end));
				desc = desc.replace("<tel>", "");
				desc = desc.replace("</tel>", "");
			}
			
			PlacesItem offsetItem = new PlacesItem(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon()), PARKINGS_INT, desc, tel);
			currentParkingsItems.add(offsetItem);
	        mClusterManager.addItem(offsetItem);
			
//			Marker m = mapView.getMap().addMarker(
//					new MarkerOptions().position(new LatLng(Double.parseDouble(poi.getLat()), ))
//							.title(desc).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//			currentParkings.add(m);
//			if (tel != null) {
//				currentParkingsToPhones.put(m, tel);
//			}
		}
		mClusterManager.cluster();
	}

	private void updatePetrolsMap(PoiCollection col) {
		for (PointOfInterestBean poi : col.getList()) {
			String desc = poi.getDesc();
			String tel = null;
			int start = desc.indexOf("<tel>");
			int end = desc.indexOf("</tel>");
			if (start != -1 && end != -1 && start < end) {
				tel = getNumeric(desc.substring(start + 5, end));
				desc = desc.replace("<tel>", "");
				desc = desc.replace("</tel>", "");
			}
			PlacesItem offsetItem = new PlacesItem(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon()), PETROL_INT, desc, tel);
			currentPetrolsItems.add(offsetItem);
	        mClusterManager.addItem(offsetItem);
//			Marker m = mapView.getMap().addMarker(
//					new MarkerOptions().position(new LatLng(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon())))
//							.title(desc).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//			currentPetrols.add(m);
//			if (tel != null) {
//				currentPetrolsToPhones.put(m, tel);
//			}
		}
		mClusterManager.cluster();
	}

	private void updateLojackMap(PoiCollection col) {
		for (PointOfInterestBean poi : col.getList()) {
			String desc = poi.getDesc();
			String tel = null;
			int start = desc.indexOf("<tel>");
			int end = desc.indexOf("</tel>");
			if (start != -1 && end != -1 && start < end) {
				tel = getNumeric(desc.substring(start + 5, end));
				desc = desc.replace("<tel>", "");
				desc = desc.replace("</tel>", "");
			}
			PlacesItem offsetItem = new PlacesItem(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon()), LJ_INT, desc, tel);
			currentLojackItems.add(offsetItem);
	        mClusterManager.addItem(offsetItem);
//			Marker m = mapView.getMap().addMarker(
//					new MarkerOptions().position(new LatLng(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLon())))
//							.title(desc).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//			currentLojack.add(m);
//			if (tel != null) {
//				currentLojackToPhones.put(m, tel);
//			}
		}
		mClusterManager.cluster();
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
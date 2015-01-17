package com.tdil.peugeotservice.android.car;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.PeugeotActivity;
import com.tdil.peugeotservice.android.places.LocarRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTaskOpt;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.prevent.PhoneNumbersBean;
import com.tdil.peugeotservice.android.rest.model.prevent.PositionHistoryBean;
import com.tdil.peugeotservice.android.rest.model.prevent.PositionHistoryCollection;
import com.tdil.peugeotservice.android.rest.model.prevent.SecureZoneCollection;
import com.tdil.peugeotservice.android.rest.model.prevent.SpeedLimitCollection;
import com.tdil.peugeotservice.android.rest.model.prevent.VehicleBean;
import com.tdil.peugeotservice.android.rest.model.prevent.VehicleCollection;



public class ActivityCars extends PeugeotActivity {

//	private MapView mapView;
	private GoogleMap map;
	private SupportMapFragment mapFragment;
	
	private List<Marker> vehiclesMarkers = new ArrayList<Marker>();
	VehicleCollection vehicles = null;
	
	private VehicleBean selectedVehicle = null;
	VehicleOption option;
	
	public static final int REQUEST_PATH = 0;
	public static final String REQUEST_PATH_PARAM = "REQUEST_PATH_PARAM";
	
	LocarRestClientObserver positionsObserver = new LocarRestClientObserver(this) {
		@Override
		public void sucess(IRestClientTask restClientTask) {
			// validar la respuesta
			vehicles = ((RESTClientTaskOpt<VehicleCollection>)restClientTask).getCastedResult();
			activity.updatePositionsLocations(vehicles);
		}
	};
	
	private LocarRestClientObserver getSpeedsObserver = new LocarRestClientObserver(this) {
		@Override
		public void sucess(IRestClientTask restClientTask) {
			// validar la respuesta
			SpeedLimitCollection speed = ((RESTClientTaskOpt<SpeedLimitCollection>)restClientTask).getCastedResult();
			CarsDialogs.openChangeSpeedDialog(speed, ActivityCars.this);
		}
	};
	
	private LocarRestClientObserver getZoneObserver = new LocarRestClientObserver(this) {
		@Override
		public void sucess(IRestClientTask restClientTask) {
			// validar la respuesta
			SecureZoneCollection speed = ((RESTClientTaskOpt<SecureZoneCollection>)restClientTask).getCastedResult();
			CarsDialogs.openChangeZoneDialog(speed, ActivityCars.this);
		}
	};
	
	private LocarRestClientObserver getPhonesObserver = new LocarRestClientObserver(this) {
		@Override
		public void sucess(IRestClientTask restClientTask) {
			// validar la respuesta
			PhoneNumbersBean speed = ((RESTClientTaskOpt<PhoneNumbersBean>)restClientTask).getCastedResult();
			CarsDialogs.openChangePhoneNumbersDialog(speed, ActivityCars.this);
		}
	};
	
	private class SelectVehicleAndContinueObserver extends LocarRestClientObserver {
		public SelectVehicleAndContinueObserver(ActivityCars activity) {
			super(activity);
		}
		
		@Override
		public void sucess(IRestClientTask restClientTask) {
			// validar la respuesta
			vehicles = ((RESTClientTaskOpt<VehicleCollection>)restClientTask).getCastedResult();
			selectVehicleAndContinue();
		}
	};
	
	SelectVehicleAndContinueObserver selectVehicleSpeedObserver = new SelectVehicleAndContinueObserver(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_main);
//        customizeActionBar(true);
        
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview);
		mapFragment.onCreate(savedInstanceState);
		map = mapFragment.getMap();
		
        //map.getUiSettings().setMyLocationButtonEnabled(false);
//        map.setMyLocationEnabled(true);
        map.setInfoWindowAdapter(new MyCustomAdapter());

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this);
        
        new RESTClientTaskOpt<VehicleCollection>(this, HttpMethod.GET, this.positionsObserver, RESTConstants.GET_VEHICLES, null,null,VehicleCollection.class).executeSerial((Void) null);
        
	        // Updates the location and zoom of the MapView
	//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-34.6093546,-58.51752859999999), 16);
	//        map.animateCamera(cameraUpdate);
	 /*      
	        ((View)findViewById(R.id.carPositionsButton)).setOnClickListener(new CarsPositionsOnClick(this));
	        ((View)findViewById(R.id.carSpeedButton)).setOnClickListener(new CarsSpeedOnClick(this));
	        ((View)findViewById(R.id.carZoneButton)).setOnClickListener(new CarsZoneOnClick(this));
	        ((View)findViewById(R.id.carPhoneButton)).setOnClickListener(new CarsPhoneOnClick(this));
	        ((View)findViewById(R.id.carPathButton)).setOnClickListener(new CarsPathOnClick(this));
	        ((View)findViewById(R.id.carParkedModeButton)).setOnClickListener(new CarsParkedModeOnClick(this));
	        
	*/        
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	BitmapDescriptor n = BitmapDescriptorFactory.fromResource(R.drawable.icon_n);
    	BitmapDescriptor ne = BitmapDescriptorFactory.fromResource(R.drawable.icon_ne);
    	BitmapDescriptor e = BitmapDescriptorFactory.fromResource(R.drawable.icon_e);
    	BitmapDescriptor se = BitmapDescriptorFactory.fromResource(R.drawable.icon_se);
    	BitmapDescriptor s = BitmapDescriptorFactory.fromResource(R.drawable.icon_s);
    	BitmapDescriptor so = BitmapDescriptorFactory.fromResource(R.drawable.icon_so);
    	BitmapDescriptor o = BitmapDescriptorFactory.fromResource(R.drawable.icon_o);
    	BitmapDescriptor no = BitmapDescriptorFactory.fromResource(R.drawable.icon_no);
    	
    	if (requestCode == REQUEST_PATH && resultCode == RESULT_OK && data != null) {
    		PositionHistoryCollection pos = (PositionHistoryCollection)data.getSerializableExtra(REQUEST_PATH_PARAM);
    		for (PositionHistoryBean bean : pos.getList()) {
    			LatLng latLng = new LatLng(Double.parseDouble(bean.getLatitude()),Double.parseDouble(bean.getLongitude()));
    			MarkerOptions marker = new MarkerOptions().position(latLng)
    					.title(bean.getStreet() + " " + bean.getNumber() + "\n" + bean.getSpeed() + "km/h " + bean.getDirection() + "\n" + bean.getFecha());
    			if (bean.getDirection().equals("N")) {
    				marker.icon(n);
    			} 
    			if (bean.getDirection().equals("NE")) {
    				marker.icon(ne);
    			} 
    			if (bean.getDirection().equals("E")) {
    				marker.icon(e);
    			} 
    			if (bean.getDirection().equals("SE")) {
    				marker.icon(se);
    			} 
    			if (bean.getDirection().equals("S")) {
    				marker.icon(s);
    			} 
    			if (bean.getDirection().equals("SO")) {
    				marker.icon(so);
    			} 
    			if (bean.getDirection().equals("O")) {
    				marker.icon(o);
    			} 
    			if (bean.getDirection().equals("NO")) {
    				marker.icon(no);
    			} 
				// adding marker
    			marker.anchor(0.5f, 0.5f);
				map.addMarker(marker);
    		}
    		if (pos.getList().size() > 0) {
    			PositionHistoryBean bean = pos.getList().iterator().next();
    			LatLng latLng = new LatLng(Double.parseDouble(bean.getLatitude()),Double.parseDouble(bean.getLongitude()));
    			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
    			map.animateCamera(cameraUpdate);
    		}
    	}
    }

	protected void selectVehicleAndContinue() {
		if (vehicles.getList().size() == 1) {
			selectedVehicle = vehicles.getList().iterator().next();
			vehicleSelectedAndContinue();
		} else {
			openVehicleSelectionAndContinue();
		}
		
	}

	private void openVehicleSelectionAndContinue() {
		final Dialog dialog = new Dialog(ActivityCars.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.selection_vehicle_dialog);
		TextView selectVehicleForActionTextView = (TextView)dialog.findViewById(R.id.selectVehicleForActionTextView);
		if (option == VehicleOption.SPEED) {
			selectVehicleForActionTextView.setText(R.string.selectVehicleForSpeed);
		}
		if (option == VehicleOption.ZONE) {
			selectVehicleForActionTextView.setText(R.string.selectVehicleForZone);
		}
		if (option == VehicleOption.PHONE_NUMBERS) {
			selectVehicleForActionTextView.setText(R.string.selectVehicleForPhoneNumbers);
		}
		if (option == VehicleOption.PATH) {
			selectVehicleForActionTextView.setText(R.string.selectVehicleForPath);
		}
//		dialog.setTitle("Seleccione un vehiculo");
		LinearLayout vehiclesLayout= (LinearLayout)dialog.findViewById(R.id.selectVehicleButtonsContainer);
		for (VehicleBean vehicle : vehicles.getList()) {
			final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.vehicle_button, null);
			Button vehicleButton = (Button)layout.findViewById(R.id.vehicleButton);
			
//			Button vehicleButton = new Button(this);
//		    vehicleButton.setText(vehicle.getDescription().toUpperCase());
//		    vehicleButton.setLayoutParams(new ViewGroup.LayoutParams(
//		        ViewGroup.LayoutParams.MATCH_PARENT,
//		            ViewGroup.LayoutParams.WRAP_CONTENT));
//		    vehicleButton.setBackground(background)
		    //<item name="android:drawableRight">@drawable/icon_arrow_inlist</item>
		    //<item name="android:textSize">14sp</item>
//		    vehicleButton.setTextAlignment(textAlignment)
		    
		 // TODO: (Marcos)
		 // Este es el estilo vehicleButton.setStyle("@style/ButtonLoginList");
		 /*
		  * y estas son las propiedades
		 	    <item name="android:layout_width">match_parent</item>
		 	    <item name="android:layout_height">36dp</item>
		 	    <item name="android:gravity">left|center_vertical</item>
		         <item name="android:paddingLeft">10dp</item>
		 	    <item name="android:background">@drawable/bg_textfields_02</item>
		 	    <item name="android:drawableRight">@drawable/icon_arrow_inlist</item>
		 	    <item name="android:textSize">14sp</item>
		 	    <item name="android:textColor">@color/black</item>
		 	    <item name="android:textAllCaps">true</item>
		 	    <item name="android:textAlignment">textStart</item>
		  * 
		  * */		    
			vehicleButton.setText(vehicle.getDescription());
		    vehicleButton.setOnClickListener(new VehicleButtonOnClickListener(vehicle, this, dialog));
		    vehiclesLayout.addView(layout);       
		}
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.selectVehicleCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}


	private void vehicleSelectedAndContinue() {
		if (option == VehicleOption.SPEED) {
			new RESTClientTaskOpt<SpeedLimitCollection>(ActivityCars.this, HttpMethod.GET, getSpeedsObserver, RESTConstants.GET_VEHICLE_SPEED_LIMITS, new RestParams(
					RESTConstants.P_VEHICLE, selectedVehicle.getId()),null, SpeedLimitCollection.class).executeSerial((Void) null);
		}
		if (option == VehicleOption.ZONE) {
			new RESTClientTaskOpt<SecureZoneCollection>(ActivityCars.this, HttpMethod.GET, getZoneObserver, RESTConstants.GET_VEHICLE_SECURE_ZONES, new RestParams(
					RESTConstants.P_VEHICLE, selectedVehicle.getId()),null,SecureZoneCollection.class).executeSerial((Void) null);
		}
		if (option == VehicleOption.PHONE_NUMBERS) {
			new RESTClientTaskOpt<PhoneNumbersBean>(ActivityCars.this, HttpMethod.GET, getPhonesObserver, RESTConstants.GET_VEHICLE_PHONES, new RestParams(
					RESTConstants.P_VEHICLE, selectedVehicle.getId()),null, PhoneNumbersBean.class).executeSerial((Void) null);
		}
		
		if (option == VehicleOption.PATH) {
			CarsDialogs.openSelectHistoricPathDialog(this);
		}
		
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
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
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
			Marker m = map.addMarker(new MarkerOptions()
		        .position(latLng)
		        .title(vehicleBean.getDescription()));
			 vehiclesMarkers.add(m);
		}
		if (vehicles.getList().size() == 1) {
			LatLng latLng = new LatLng(points.get(0).latitude - 0.00089832, points.get(0).longitude);
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
			map.animateCamera(cameraUpdate);
		} else {
			centerMap(points);
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapFragment.onResume();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		mapFragment.onDestroy();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mapFragment.onPause();
	}

    static class VehicleButtonOnClickListener implements View.OnClickListener {
    	
    	private VehicleBean vehicle;
    	private ActivityCars activityCars;
    	private Dialog dialog;
    	
    	public VehicleButtonOnClickListener(VehicleBean vehicle,
				ActivityCars activityCars, Dialog dialog) {
			super();
			this.vehicle = vehicle;
			this.activityCars = activityCars;
			this.dialog = dialog;
		}

		@Override
    	public void onClick(View v) {
			this.dialog.dismiss();
    		this.activityCars.setSelectedVehicle(this.vehicle);
    		this.activityCars.vehicleSelectedAndContinue();
    	}
    }

	public VehicleBean getSelectedVehicle() {
		return selectedVehicle;
	}


	public void setSelectedVehicle(VehicleBean selectedVehicle) {
		this.selectedVehicle = selectedVehicle;
	}
	
	private class MyCustomAdapter implements InfoWindowAdapter {
	    @Override
	    public View getInfoContents(Marker marker) {
	    	View view = getLayoutInflater().inflate(R.layout.cars_info_view, null);
	    	TextView carsTitle = (TextView)view.findViewById(R.id.carsTitle);
	    	carsTitle.setText(marker.getTitle());
	        return view;
	    }
	    @Override
	    public View getInfoWindow(Marker arg0) {
	    	 return null;
	    }
	}
}
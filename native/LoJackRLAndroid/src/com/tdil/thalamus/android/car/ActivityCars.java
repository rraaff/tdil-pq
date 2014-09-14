package com.tdil.thalamus.android.car;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tdil.thalamus.android.UpdateActivity;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.places.LocarRestClientObserver;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.AddressTypeBean;
import com.tdil.thalamus.android.rest.model.AddressTypeBeanCollection;
import com.tdil.thalamus.android.rest.model.prevent.SpeedLimitBean;
import com.tdil.thalamus.android.rest.model.prevent.SpeedLimitCollection;
import com.tdil.thalamus.android.rest.model.prevent.VehicleBean;
import com.tdil.thalamus.android.rest.model.prevent.VehicleCollection;



public class ActivityCars extends ActionBarActivity {

	private MapView mapView;
	
	private List<Marker> vehiclesMarkers = new ArrayList<Marker>();
	private VehicleCollection vehicles = null;
	
	private VehicleBean selectedVehicle = null;
	private VehicleOption option;
	
	private LocarRestClientObserver positionsObserver = new LocarRestClientObserver(this) {
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			// validar la respuesta
			vehicles = gson.fromJson(restClientTask.getResult(), VehicleCollection.class);
			activity.updatePositionsLocations(vehicles);
		}
	};
	
	private LocarRestClientObserver getSpeedsObserver = new LocarRestClientObserver(this) {
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			// validar la respuesta
			SpeedLimitCollection speed = gson.fromJson(restClientTask.getResult(), SpeedLimitCollection.class);
			activity.openChangeSpeedDialog(speed);
		}
	};
	
	private class SelectVehicleAndContinueObserver extends LocarRestClientObserver {
		public SelectVehicleAndContinueObserver(ActivityCars activity) {
			super(activity);
		}
		
		@Override
		public void sucess(IRestClientTask restClientTask) {
			Gson gson = new Gson();
			// validar la respuesta
			vehicles = gson.fromJson(restClientTask.getResult(), VehicleCollection.class);
			selectVehicleAndContinue();
		}
	};
	
	private SelectVehicleAndContinueObserver selectVehicleSpeedObserver = new SelectVehicleAndContinueObserver(this);
	
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
        
        ((View)findViewById(R.id.carSpeedButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (vehicles == null) {
					new RESTClientTask(ActivityCars.this, HttpMethod.GET, selectVehicleSpeedObserver, RESTConstants.GET_VEHICLES, null,null).execute((Void) null);
				} else {
					option = VehicleOption.SPEED;
					selectVehicleAndContinue();
				}
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
		dialog.setContentView(R.layout.selection_vehicle_dialog);
		dialog.setTitle("Seleccione un vehiculo");
		LinearLayout vehiclesLayout= (LinearLayout)dialog.findViewById(R.id.selectVehicleButtonsContainer);
		for (VehicleBean vehicle : vehicles.getList()) {
			Button vehicleButton = new Button(this);
		    vehicleButton.setText(vehicle.getDescription());
		    vehicleButton.setLayoutParams(new ViewGroup.LayoutParams(
		        ViewGroup.LayoutParams.WRAP_CONTENT,
		            ViewGroup.LayoutParams.WRAP_CONTENT));
		    vehicleButton.setOnClickListener(new VehicleButtonOnClickListener(vehicle, this, dialog));
		    vehiclesLayout.addView(vehicleButton);       
		}
		dialog.show();
	}


	private void vehicleSelectedAndContinue() {
		if (option == VehicleOption.SPEED) {
			new RESTClientTask(ActivityCars.this, HttpMethod.GET, getSpeedsObserver, RESTConstants.GET_VEHICLE_SPEED_LIMITS, new RestParams(
					RESTConstants.P_VEHICLE, selectedVehicle.getId()),null).execute((Void) null);
		}
	}

	protected void openChangeSpeedDialog(SpeedLimitCollection speed) {
		final Dialog dialog = new Dialog(ActivityCars.this);
		dialog.setContentView(R.layout.vehicle_change_speed_dialog);
		dialog.setTitle("Cambio de velocidad");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.vehicleSpeedDomainTextView);
		text.setText(selectedVehicle.getDescription());

		final Spinner speedSpinner = (Spinner)dialog.findViewById(R.id.changeSpeedSpinner);
		
		BeanMappingListAdapter<SpeedLimitBean> adapter = new BeanMappingListAdapter<SpeedLimitBean>(
			this,
			android.R.layout.simple_spinner_item,
			new ArrayList<SpeedLimitBean>(speed.getList()),
			new BeanMappingFunction<SpeedLimitBean>() {
				public String key(SpeedLimitBean t) {
					return String.valueOf(t.getDescription());
				};

				@Override
				public String value(SpeedLimitBean t) {
					return t.getDescription();
				}
			});
		speedSpinner.setAdapter(adapter);
		int position = 0;
		for (SpeedLimitBean bean : speed.getList()) {
			if (bean.isActive()) {
				speedSpinner.setSelection(position);
			}
			position++;
		}
				
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.vehicleChangeSpeedButtonCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		Button dialogOkButton = (Button) dialog.findViewById(R.id.vehicleChangeSpeedButtonOK);
		dialogOkButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String selectSpeedId = ((SpeedLimitBean)speedSpinner.getSelectedItem()).getId();
				new RESTClientTask(ActivityCars.this, HttpMethod.POST, getPostSpeedObserver(dialog), RESTConstants.POST_VEHICLE_SPEED_LIMITS, new RestParams(
						RESTConstants.P_VEHICLE, selectedVehicle.getId())
						.put(RESTConstants.P_SPEED_LIMIT_ID, selectSpeedId),null).execute((Void) null);
			}
		});
		dialog.show();
	}
	
	public IRestClientObserver getPostSpeedObserver(final Dialog dialog) {
		return new LocarRestClientObserver(this) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				// TODO validar la respuesta
				dialog.dismiss();
				Context context = getApplicationContext();
				CharSequence text = "Se ha modificado la velocidad";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		};
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
}
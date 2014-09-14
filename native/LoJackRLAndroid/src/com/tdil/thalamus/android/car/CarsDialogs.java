package com.tdil.thalamus.android.car;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.places.LocarRestClientObserver;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.prevent.SecureZoneBean;
import com.tdil.thalamus.android.rest.model.prevent.SecureZoneCollection;
import com.tdil.thalamus.android.rest.model.prevent.SpeedLimitBean;
import com.tdil.thalamus.android.rest.model.prevent.SpeedLimitCollection;

public class CarsDialogs {

	protected static void openChangeSpeedDialog(SpeedLimitCollection speed, final ActivityCars activityCars) {
		final Dialog dialog = new Dialog(activityCars);
		dialog.setContentView(R.layout.vehicle_change_speed_dialog);
		dialog.setTitle("Cambio de velocidad");
	
		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.vehicleSpeedDomainTextView);
		text.setText(activityCars.getSelectedVehicle().getDescription());
	
		final Spinner speedSpinner = (Spinner)dialog.findViewById(R.id.changeSpeedSpinner);
		
		BeanMappingListAdapter<SpeedLimitBean> adapter = new BeanMappingListAdapter<SpeedLimitBean>(
				activityCars,
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
				new RESTClientTask(activityCars, HttpMethod.POST, getPostSpeedObserver(dialog, activityCars), RESTConstants.POST_VEHICLE_SPEED_LIMITS, new RestParams(
						RESTConstants.P_VEHICLE, activityCars.getSelectedVehicle().getId())
						.put(RESTConstants.P_SPEED_LIMIT_ID, selectSpeedId),null).execute((Void) null);
			}
		});
		dialog.show();
	}
	
	
	protected static void openChangeZoneDialog(SecureZoneCollection zones, final ActivityCars activityCars) {
		final Dialog dialog = new Dialog(activityCars);
		dialog.setContentView(R.layout.vehicle_change_zone_dialog);
		dialog.setTitle("Cambio de zona segura");
	
		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.vehicleZoneDomainTextView);
		text.setText(activityCars.getSelectedVehicle().getDescription());
	
		final Spinner speedSpinner = (Spinner)dialog.findViewById(R.id.changeZoneSpinner);
		
		BeanMappingListAdapter<SecureZoneBean> adapter = new BeanMappingListAdapter<SecureZoneBean>(
				activityCars,
			android.R.layout.simple_spinner_item,
			new ArrayList<SecureZoneBean>(zones.getList()),
			new BeanMappingFunction<SecureZoneBean>() {
				public String key(SecureZoneBean t) {
					return String.valueOf(t.getDescription());
				};
	
				@Override
				public String value(SecureZoneBean t) {
					return t.getDescription();
				}
			});
		speedSpinner.setAdapter(adapter);
		int position = 0;
		for (SecureZoneBean bean : zones.getList()) {
			if (bean.isActive()) {
				speedSpinner.setSelection(position);
			}
			position++;
		}
				
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.vehicleChangeZoneButtonCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		Button dialogOkButton = (Button) dialog.findViewById(R.id.vehicleChangeZoneButtonOK);
		dialogOkButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String selectSpeedId = ((SecureZoneBean)speedSpinner.getSelectedItem()).getId();
				new RESTClientTask(activityCars, HttpMethod.POST, getPostZoneObserver(dialog, activityCars), RESTConstants.POST_VEHICLE_SECURE_ZONE, new RestParams(
						RESTConstants.P_VEHICLE, activityCars.getSelectedVehicle().getId())
						.put(RESTConstants.P_SECURE_ZONE_ID, selectSpeedId),null).execute((Void) null);
			}
		});
		dialog.show();
	}


	public static IRestClientObserver getPostSpeedObserver(final Dialog dialog, final ActivityCars activityCars) {
		return new LocarRestClientObserver(activityCars) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				// TODO validar la respuesta
				dialog.dismiss();
				Context context = activityCars.getApplicationContext();
				CharSequence text = "Se ha modificado la velocidad";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		};
	}


	public static IRestClientObserver getPostZoneObserver(final Dialog dialog, final ActivityCars activityCars) {
		return new LocarRestClientObserver(activityCars) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				// TODO validar la respuesta
				dialog.dismiss();
				Context context = activityCars.getApplicationContext();
				CharSequence text = "Se ha modificado la zona segura";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		};
	}

}

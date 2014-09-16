package com.tdil.thalamus.android.car;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.home.ActivityHomeAlarmDashboard;
import com.tdil.thalamus.android.places.LocarRestClientObserver;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.prevent.PhoneNumbersBean;
import com.tdil.thalamus.android.rest.model.prevent.PositionHistoryCollection;
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
	
	protected static void openChangePhoneNumbersDialog(PhoneNumbersBean phones, final ActivityCars activityCars) {
		final Dialog dialog = new Dialog(activityCars);
		dialog.setContentView(R.layout.vehicle_change_phones_dialog);
		dialog.setTitle("Cambio de telefonos");
	
		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.vehiclePhonesDomainTextView);
		text.setText(activityCars.getSelectedVehicle().getDescription());
	
		final EditText code1 = (EditText)dialog.findViewById(R.id.countryCode1EditText);
		final EditText number1 = (EditText)dialog.findViewById(R.id.number1EditText);
		String alert = phones.getAlert();
		code1.setText("54");
		if (alert != null && alert.contains("-")) {
			String arr[] = alert.split("-");
			if (arr.length > 0) {
				code1.setText(arr[0]);
				number1.setText(arr[1]);
			}
		}
		final EditText code2 = (EditText)dialog.findViewById(R.id.countryCode2EditText);
		final EditText number2 = (EditText)dialog.findViewById(R.id.number2EditText);
		String crash = phones.getAlert();
		code2.setText("54");
		if (crash != null && crash.contains("-")) {
			String arr[] = crash.split("-");
			if (arr.length > 0) {
				code2.setText(arr[0]);
				number2.setText(arr[1]);
			}
		}
		
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.vehicleChangePhonesButtonCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		Button dialogOkButton = (Button) dialog.findViewById(R.id.vehicleChangePhonesButtonOK);
		dialogOkButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PhoneNumbersBean phoneNumbersBean = new PhoneNumbersBean();
				phoneNumbersBean.setAlert(code1.getText().toString() + "-" + number1.getText().toString());
				phoneNumbersBean.setCrash(code2.getText().toString() + "-" + number2.getText().toString());
				Gson gson = new Gson();
				String update = gson.toJson(phoneNumbersBean);
				new RESTClientTask(activityCars, HttpMethod.POST, getPostNumbersObserver(dialog, activityCars), RESTConstants.POST_VEHICLE_PHONES, new RestParams(
						RESTConstants.P_VEHICLE, activityCars.getSelectedVehicle().getId()),update).execute((Void) null);
			}
		});
		dialog.show();
	}
	
	protected static void openSelectHistoricPathDialog(final ActivityCars activityCars) {
		final Dialog dialog = new Dialog(activityCars);
		dialog.setContentView(R.layout.vehicle_select_historic_path_dialog);
		dialog.setTitle("Elija una opcion");
	
		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.vehiclePathDomainTextView);
		text.setText(activityCars.getSelectedVehicle().getDescription());
		
		final RadioButton lastHour = (RadioButton)dialog.findViewById(R.id.pathLastHourRadioButton);
		lastHour.setChecked(true);
		
		final RadioButton today = (RadioButton)dialog.findViewById(R.id.pathTodayRadioButton);
		final RadioButton yesterday = (RadioButton)dialog.findViewById(R.id.pathYesterdayRadioButton);
		final RadioButton lastWeek = (RadioButton)dialog.findViewById(R.id.pathLastWeekRadioButton);
		
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.vehiclePathButtonCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		Button dialogOkButton = (Button) dialog.findViewById(R.id.vehiclePathButtonOK);
		dialogOkButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
				Date from = null;
				Date to = null;
				if (lastHour.isChecked()) {
					Calendar cal = Calendar.getInstance();
					to = cal.getTime();
					cal.add(Calendar.HOUR, -1);
					from = cal.getTime();
				}
				if (today.isChecked()) {
					Calendar cal = Calendar.getInstance();
					to = cal.getTime();
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					from = cal.getTime();
				}
				if (yesterday.isChecked()) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					to = cal.getTime();
					cal.add(Calendar.DATE, - 1);
					from = cal.getTime();
				}
				if (lastWeek.isChecked()) {
					Calendar cal = Calendar.getInstance();
					to = cal.getTime();
					cal.add(Calendar.DATE, - 7);
					from = cal.getTime();
				}
				String dateFrom = dateFormat.format(from);
				String dateTo = dateFormat.format(to);
				
				new RESTClientTask(activityCars, HttpMethod.GET, getPathObserver(dialog, activityCars), RESTConstants.GET_VEHICLE_PATH, new RestParams(
						RESTConstants.P_VEHICLE, activityCars.getSelectedVehicle().getId())
						.put(RESTConstants.P_DATE_START, dateFrom)
						.put(RESTConstants.P_DATE_END, dateTo),null).execute((Void) null);
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
	
	public static IRestClientObserver getPostNumbersObserver(final Dialog dialog, final ActivityCars activityCars) {
		return new LocarRestClientObserver(activityCars) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				// TODO validar la respuesta
				dialog.dismiss();
				Context context = activityCars.getApplicationContext();
				CharSequence text = "Se han modificado los telefonos";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		};
	}
	
	public static IRestClientObserver getPathObserver(final Dialog dialog, final ActivityCars activityCars) {
		return new LocarRestClientObserver(activityCars) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				Gson gson = new Gson();
				// validar la respuesta
				PositionHistoryCollection pos = gson.fromJson(restClientTask.getResult(), PositionHistoryCollection.class);
				Intent intent = new Intent(activity.getBaseContext(), ActivityCarsPathHistory.class);
				intent.putExtra(ActivityCarsPathHistory.Position_History_Collection, pos);
				activity.startActivityForResult(intent, ActivityCars.REQUEST_PATH);
				dialog.dismiss();
				/*Context context = activityCars.getApplicationContext();
				CharSequence text = "Se han modificado los telefonos";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();*/
			}
		};
	}

}

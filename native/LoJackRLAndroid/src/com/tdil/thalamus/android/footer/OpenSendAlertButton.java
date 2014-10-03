package com.tdil.thalamus.android.footer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ApplicationConfig;
import com.tdil.thalamus.android.ContextRestClientObserver;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.rest.model.RESTResponse;

public class OpenSendAlertButton extends Button {

	public OpenSendAlertButton(final Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnClickListener(new OpenSendAlertButtonOnClickListener(context));
	}
	
	public OpenSendAlertButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(new OpenSendAlertButtonOnClickListener(context));
	}

	public OpenSendAlertButton(final Context context) {
		super(context);
		setOnClickListener(new OpenSendAlertButtonOnClickListener(context));
	}
	
	static class OpenSendAlertButtonOnClickListener implements View.OnClickListener {
		private Context context;

		public OpenSendAlertButtonOnClickListener(Context context) {
			super();
			this.context = context;
		}
		
		@Override
		public void onClick(View view) {
			new RESTClientTaskOpt<AlarmCollection>(context, HttpMethod.GET, getOpenSendAlertDialog(context),
					RESTConstants.ALARMS, null, null, AlarmCollection.class).execute((Void) null);
		}
		
	}

	public static IRestClientObserver getOpenSendAlertDialog(final Context context) {
		return new ContextRestClientObserver(context) {
			
			@Override
			public void sucess(IRestClientTask restClientTask) {
				AlarmCollection pos = ((RESTClientTaskOpt<AlarmCollection>) restClientTask).getCastedResult();
				openSendAlertDialog(activity, pos);
			}
		};
	}
	
	private static void openSendAlertDialog(final Context activity, AlarmCollection pos) {
		final Dialog dialog = new Dialog(activity);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.alert_center_dialog);
		View call = dialog.findViewById(R.id.alertCenterCallButton);
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String uri = "tel:" + ApplicationConfig.ALERT_CENTER_PHONE;
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
					activity.startActivity(callIntent);
				} catch (Exception e) {
					Toast.makeText(activity, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
				}
			}
		});
		LinearLayout vehiclesLayout = (LinearLayout) dialog.findViewById(R.id.alertCenterButtonsContainer);
		for (Alarm alarm : pos.getAlarms()) {
			final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.vehicle_button, null);
			Button vehicleButton = (Button) layout.findViewById(R.id.vehicleButton);
			vehicleButton.setText(alarm.getDescription());
			vehicleButton.setOnClickListener(new SendPanicSignalListener(alarm, activity, dialog));
			vehiclesLayout.addView(layout);
		}
		Button dialogCancelButton = (Button) dialog.findViewById(R.id.alertCenterCancel);
		dialogCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	private static class SendPanicSignalListener implements OnClickListener {
		private Alarm alarm;
		private Context activity;
		private Dialog dialog;

		SendPanicSignalListener(Alarm alarm, Context activity, Dialog dialog) {
			this.alarm = alarm;
			this.activity = activity;
			this.dialog = dialog;
		}

		@Override
		public void onClick(View arg0) {
			new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.GET, getPostSendAlertDialog(activity, dialog),
					RESTConstants.SEND_PANIC_ALARM, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(alarm.getIdEntidad())), null,
					RESTResponse.class).execute((Void) null);
		}
	}
	
	public static IRestClientObserver getPostSendAlertDialog(final Context activity, final Dialog dialog) {
		return new ContextRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>) restClientTask).getCastedResult();
				if (response.getOk()) {
					new AlertDialog.Builder(activity).setIcon(R.drawable.ic_launcher).setTitle("Alarms")
							.setMessage("Se ha enviado la señal de panico").setPositiveButton("OK", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									dialog.dismiss();
								}
							}).show();
				} else {
					error(restClientTask);
				}
			}
		};
	}
}

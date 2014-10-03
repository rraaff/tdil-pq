package com.tdil.thalamus.android.footer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ApplicationConfig;

public class OpenAssistCenterButton extends Button {

	public OpenAssistCenterButton(final Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnClickListener(new OpenAssistCenterButtonClickListener(context));
	}

	public OpenAssistCenterButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(new OpenAssistCenterButtonClickListener(context));
	}

	public OpenAssistCenterButton(Context context) {
		super(context);
		setOnClickListener(new OpenAssistCenterButtonClickListener(context));
	}

	static class OpenAssistCenterButtonClickListener implements View.OnClickListener {
		private Context context;

		public OpenAssistCenterButtonClickListener(Context context) {
			super();
			this.context = context;
		}
		
		@Override
		public void onClick(View view) {
			final Dialog dialog = new Dialog(context);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.assist_center_dialog);
			// dialog.setTitle("Seleccione un vehiculo");
			View homeAssistCallButton = dialog.findViewById(R.id.homeAssistCallButton);
			homeAssistCallButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						String uri = "tel:" + ApplicationConfig.HOME_ASSIST_CENTER_PHONE;
						Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
						context.startActivity(callIntent);
					} catch (Exception e) {
						Toast.makeText(context, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
					}
				}
			});
			View carAssistCallButton = dialog.findViewById(R.id.carAssistCallButton);
			carAssistCallButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						String uri = "tel:" + ApplicationConfig.CAR_ASSIST_CENTER_PHONE;
						Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
						context.startActivity(callIntent);
					} catch (Exception e) {
						Toast.makeText(context, "Ha ocurrido un error realizando la llamada...", Toast.LENGTH_LONG).show();
					}
				}
			});
			
			Button dialogCancelButton = (Button) dialog.findViewById(R.id.assistCenterCancel);
			dialogCancelButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			dialog.show();
		}
	}
	
}

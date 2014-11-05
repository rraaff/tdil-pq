package com.tdil.thalamus.android.car.parkedmode;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ActivityRestClientObserver;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeHistoryLogBean;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;

public class ParkedModeStatusListAdapter extends AbstractListAdapter<ParkedModeStatus, ParkedModeStatusViewHolder> {

	private Activity activity;
	private boolean ignore = false;
	
	public ParkedModeStatusListAdapter(Activity a,
			ArrayList<ParkedModeStatus> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
	}

	@Override
	protected void fillViewHolder(ParkedModeStatusViewHolder holder,
			ParkedModeStatus iter) {
		holder.domain.setText(iter.getDomain());
		if ("ON".equals(iter.getStatus())) {
			holder.status.setText("ACTIVADO");
			holder.status.setTextColor(activity.getResources().getColor(R.color.lst_itm_on));
		} else {
			holder.status.setText("DESACTIVADO");
			holder.status.setTextColor(activity.getResources().getColor(R.color.lst_itm_off));
		}
		holder.pmGeoRef.setText(iter.getGeoRef());
		if ("ON".equals(iter.getStatus())) {
			holder.pmStatusSwitch.setChecked(true);
		} else {
			holder.pmStatusSwitch.setChecked(false);
		}
		holder.pmStatusSwitch.setOnCheckedChangeListener(new ToggleParkedModeListener(activity, holder.pmStatusSwitch, iter));
		holder.pmGoToPosition.setOnClickListener(new GoPosition(activity, iter));
		holder.goDetail.setOnClickListener(new GoDashboard(activity, iter));
	}

	@Override
	protected ParkedModeStatusViewHolder createViewHolder(View vi) {
		ParkedModeStatusViewHolder holder = new ParkedModeStatusViewHolder();
		holder.domain = (TextView)vi.findViewById(R.id.pmDomainTextView);
		holder.status = (TextView)vi.findViewById(R.id.pmStatus);
		holder.pmGeoRef = (TextView)vi.findViewById(R.id.pmGeoRef);
		holder.pmStatusSwitch = (ToggleButton)vi.findViewById(R.id.pmStatusSwitch);
		holder.pmGoToPosition = (View)vi.findViewById(R.id.pmGoToPosition);
		holder.goDetail = (View)vi.findViewById(R.id.pmGoToDashboard);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.parked_mode_list_item;
	}
	
	private static class ToggleParkedModeListener implements OnCheckedChangeListener {
		private Activity activity;
		private ToggleButton toggleButton;
		private ParkedModeStatus parkedModeStatus;
		private boolean ignore = false;
		
		ToggleParkedModeListener(Activity activity, ToggleButton toggleButton, ParkedModeStatus parkedModeStatus) {
			this.activity = activity;
			this.toggleButton = toggleButton;
			this.parkedModeStatus = parkedModeStatus;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
			boolean isChecked) {
			if (!ignore) {
				ignore = true;
				if ("ON".equalsIgnoreCase(this.parkedModeStatus.getStatus())) {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostDeactivatePM(activity, toggleButton, this), RESTConstants.POST_DEACTIVATE_PM, 
			    			new RestParams(RESTConstants.P_VEHICLE, this.parkedModeStatus.getVehicleID())
			    				,null,RESTResponse.class)
			    				.executeSerial((Void) null);
				} else {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostActivatePM(activity, toggleButton, this), RESTConstants.POST_ACTIVATE_PM, 
			    			new RestParams(RESTConstants.P_VEHICLE, this.parkedModeStatus.getVehicleID())
			    				,null,RESTResponse.class)
			    				.executeSerial((Void) null); 
				}
			}
		}
	}
    public static IRestClientObserver getPostDeactivatePM(final Activity activity, final ToggleButton toggleButton, final ToggleParkedModeListener parkedModeListener) {
    	return new ActivityRestClientObserver(activity) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					toggleButton.setChecked(false);
					parkedModeListener.parkedModeStatus.setStatus("OFF");
				} else {
					toggleButton.setChecked(true);
					parkedModeListener.parkedModeStatus.setStatus("ON");
					error(restClientTask);
				}
				parkedModeListener.ignore = false;
			}
			@Override
			public void error(IRestClientTask task) {
				super.error(task);
				toggleButton.setChecked(true);
				parkedModeListener.parkedModeStatus.setStatus("ON");
				parkedModeListener.ignore = false;
			}
		};
    }
    
    public static IRestClientObserver getPostActivatePM(final Activity activity, final ToggleButton toggleButton, final ToggleParkedModeListener parkedModeListener) {
    	return new ActivityRestClientObserver(activity) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					toggleButton.setChecked(true);
					parkedModeListener.parkedModeStatus.setStatus("ON");
				} else {
					toggleButton.setChecked(false);
					parkedModeListener.parkedModeStatus.setStatus("OFF");
					error(restClientTask);
				}
				parkedModeListener.ignore = false;
			}
			@Override
			public void error(IRestClientTask task) {
				super.error(task);
				toggleButton.setChecked(false);
				parkedModeListener.parkedModeStatus.setStatus("OFF");
				parkedModeListener.ignore = false;
			}
		};
    }
	
	private static class GoPosition implements OnClickListener {
		
		private Activity activity;
		private ParkedModeStatus parkedModeStatus;
		
		GoPosition(Activity activity, ParkedModeStatus parkedModeStatus) {
			this.activity = activity;
			this.parkedModeStatus = parkedModeStatus;
		}

		@Override
		public void onClick(View arg0) {
			int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
			if (googlePlayServicesAvailable != ConnectionResult.SUCCESS) {
	            // Handle the case here
	        	new AlertDialog.Builder(activity)
	            .setIcon(R.drawable.ic_launcher)
	            .setTitle("Lojack")
	            .setMessage("Para acceder a esta funcionalidad debe tener instalado Google Play (code: " + String.valueOf(googlePlayServicesAvailable) + ")")
	            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        /* User clicked OK so do some stuff */
	                        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:lojack.real.life"));
	                    }
	            })
	            .show();
	        } else {
				ParkedModeHistoryLogBean parkedModeHistoryLogBean = new ParkedModeHistoryLogBean();
				parkedModeHistoryLogBean.setIdaccion(4);
				parkedModeHistoryLogBean.setFecha("");
				parkedModeHistoryLogBean.setLatitud(parkedModeStatus.getLatitude());
				parkedModeHistoryLogBean.setLongitud(parkedModeStatus.getLongitude());
				
				Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeMapView.class);
				intent.putExtra(ActivityParkedModeMapView.POSITION, parkedModeHistoryLogBean);
				activity.startActivity(intent);
				activity.finish();
	        }
		}
	}

	private static class GoDashboard implements OnClickListener {
		
		private Activity activity;
		private ParkedModeStatus parkedModeStatus;
		
		GoDashboard(Activity activity, ParkedModeStatus parkedModeStatus) {
			this.activity = activity;
			this.parkedModeStatus = parkedModeStatus;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeVehicleHome.class);
			intent.putExtra(ActivityParkedModeVehicleHome.VEHICLE, parkedModeStatus);
			activity.startActivity(intent);
			activity.finish();
		}
	}
}

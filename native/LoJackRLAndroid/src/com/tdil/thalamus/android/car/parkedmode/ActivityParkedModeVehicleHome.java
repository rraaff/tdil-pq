package com.tdil.thalamus.android.car.parkedmode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ActivityRestClientObserver;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityParkedModeVehicleHome extends LoJackWithProductMenuActivity {

	public static final String VEHICLE = "VEHICLE";
	private ParkedModeStatus parkedModeStatus;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_home);
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		parkedModeStatus = (ParkedModeStatus)extras.getSerializable(VEHICLE);
		
		((TextView)findViewById(R.id.pmDetailDomainTextView)).setText(parkedModeStatus.getDomain());
		TextView status = (TextView)findViewById(R.id.pmDetailStatusTextView);
		ToggleButton statusSwitch = (ToggleButton)findViewById(R.id.pmStatusSwitch);
		if ("ON".equals(parkedModeStatus.getStatus())) {
			status.setText("ACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
			statusSwitch.setChecked(true);
		} else {
			status.setText("DESACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
			statusSwitch.setChecked(false);
		}
		statusSwitch.setOnCheckedChangeListener(new ToggleParkedModeListener(this, statusSwitch, parkedModeStatus));
		
		((View)findViewById(R.id.pmVehicleGoConfig)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParkedModeRestFacade.goParkedModeConfigActivity(ActivityParkedModeVehicleHome.this, parkedModeStatus);
			}
		});
		
		((View)findViewById(R.id.pmVehicleGoHistory)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParkedModeRestFacade.goParkedModeHistoryActivity(ActivityParkedModeVehicleHome.this, parkedModeStatus);
			}
		});
		
		((View)findViewById(R.id.pmVehicleHomeBack)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
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
			    				.execute((Void) null);
				} else {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostActivatePM(activity, toggleButton, this), RESTConstants.POST_ACTIVATE_PM, 
			    			new RestParams(RESTConstants.P_VEHICLE, this.parkedModeStatus.getVehicleID())
			    				,null,RESTResponse.class)
			    				.execute((Void) null); 
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
				parkedModeListener.ignore = false;
			}
		};
    }
}
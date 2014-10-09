package com.tdil.thalamus.android.car.parkedmode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ActivityRestClientObserver;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeConfiguration;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityParkedModeVehicleDetail extends LoJackWithProductMenuActivity {

	public static final String CONFIG = "CONFIG";
	private ParkedModeConfiguration parkedModeConfiguration;
	private RadioButton always;
	private ToggleButton statusSwitch;
	private boolean ignore = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_detail);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		parkedModeConfiguration = (ParkedModeConfiguration)extras.getSerializable(CONFIG);
		
		((TextView)findViewById(R.id.pmDetailDomainTextView)).setText(parkedModeConfiguration.getDomain());
		TextView status = (TextView)findViewById(R.id.pmDetailStatusTextView);
		statusSwitch = (ToggleButton)findViewById(R.id.pmStatusSwitch);
		if ("ON".equals(parkedModeConfiguration.getStatus())) {
			status.setText("ACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
			statusSwitch.setChecked(true);
		} else {
			status.setText("DESACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
			statusSwitch.setChecked(false);
		}
		statusSwitch.setOnCheckedChangeListener(new ToggleParkedModeListener(this));
		ignore = false;
		
		((TextView)findViewById(R.id.pmStatusPhoneTextView)).setText(parkedModeConfiguration.getPhone());
		always = ((RadioButton)findViewById(R.id.pmStatusAlwaysRadioButton));
		RadioButton withoutContact = ((RadioButton)findViewById(R.id.pmStatusWithoutContactRadioButton));
		if (parkedModeConfiguration.isAlways()) {
			always.setChecked(true);
			withoutContact.setChecked(false);
		} else {
			always.setChecked(false);
			withoutContact.setChecked(true);
		}
		
		((View)findViewById(R.id.pmVehicleDetailSave)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		
		((View)findViewById(R.id.pmVehicleDetailBack)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
    }
    
    private static class ToggleParkedModeListener implements OnCheckedChangeListener {
		private ActivityParkedModeVehicleDetail activity;
		
		ToggleParkedModeListener(ActivityParkedModeVehicleDetail activity) {
			this.activity = activity;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!activity.ignore) {
				activity.ignore = true;
				if ("ON".equalsIgnoreCase(activity.parkedModeConfiguration.getStatus())) {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, this.activity.getPostDeactivatePM(), RESTConstants.POST_DEACTIVATE_PM, 
			    			new RestParams(RESTConstants.P_VEHICLE, activity.parkedModeConfiguration.getVehicleID())
			    				,null,RESTResponse.class)
			    				.execute((Void) null);
				} else {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, this.activity.getPostActivatePM(), RESTConstants.POST_ACTIVATE_PM, 
			    			new RestParams(RESTConstants.P_VEHICLE, activity.parkedModeConfiguration.getVehicleID())
			    				,null,RESTResponse.class)
			    				.execute((Void) null);
				}
			}
		}
	}
    public IRestClientObserver getPostDeactivatePM() {
    	return new ActivityRestClientObserver(this) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					ActivityParkedModeVehicleDetail.this.parkedModeConfiguration.setStatus("OFF");
					ActivityParkedModeVehicleDetail.this.statusSwitch.setChecked(false);
					ActivityParkedModeVehicleDetail.this.ignore = false;
				} else {
					ActivityParkedModeVehicleDetail.this.parkedModeConfiguration.setStatus("ON");
					ActivityParkedModeVehicleDetail.this.statusSwitch.setChecked(true);
					error(restClientTask);
				}
			}
			@Override
			public void error(IRestClientTask task) {
				super.error(task);
				ActivityParkedModeVehicleDetail.this.ignore = false;
			}
		};
    }
    
    public IRestClientObserver getPostActivatePM() {
    	return new ActivityRestClientObserver(this) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					ActivityParkedModeVehicleDetail.this.parkedModeConfiguration.setStatus("ON");
					ActivityParkedModeVehicleDetail.this.statusSwitch.setChecked(true);
					ActivityParkedModeVehicleDetail.this.ignore = false;
				} else {
					ActivityParkedModeVehicleDetail.this.parkedModeConfiguration.setStatus("OFF");
					ActivityParkedModeVehicleDetail.this.statusSwitch.setChecked(false);
					error(restClientTask);
				}
			}
			@Override
			public void error(IRestClientTask task) {
				super.error(task);
				ActivityParkedModeVehicleDetail.this.ignore = false;
			}
		};
    }
    
    protected void save() {
    	new RESTClientTaskOpt<RESTResponse>(this, HttpMethod.POST, getPostSaveConfObserver(), RESTConstants.POST_PM_VEHICLE_CONF, 
    			new RestParams(RESTConstants.P_VEHICLE, parkedModeConfiguration.getVehicleID())
    				.put(RESTConstants.P_PM_PHONE, "-").put(RESTConstants.P_PM_TYPE, always.isChecked() ? "0" : "1"),null,RESTResponse.class)
    				.execute((Void) null);
		
	}


	private IRestClientObserver getPostSaveConfObserver() {
		return new ActivityRestClientObserver(this) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					new AlertDialog.Builder(activity)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Modo estacionado")
		               .setMessage("Se ha guardado la configuracion")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   dialog.dismiss();
//		                    	   activity.finish();
		                       }
		               }).show();
				} else {
					error(restClientTask);
				}
			}
		};
	}
	
}
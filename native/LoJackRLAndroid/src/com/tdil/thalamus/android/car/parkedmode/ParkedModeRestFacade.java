package com.tdil.thalamus.android.car.parkedmode;

import android.content.Intent;

import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeConfiguration;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeHistoryLogBeanCollection;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatusCollection;

public class ParkedModeRestFacade {
	
	public static void startParkedModeStatusActivity(LoJackActivity activity) {
		new RESTClientTaskOpt<ParkedModeStatusCollection>(activity, HttpMethod.GET, getParkedModeObserver(activity), RESTConstants.GET_PM_VEHICLES, null,null, 
				ParkedModeStatusCollection.class).execute((Void) null);
	}
	
	public static void goParkedModeConfigActivity(LoJackActivity activity, ParkedModeStatus parkedModeStatus) {
		new RESTClientTaskOpt<ParkedModeConfiguration>(activity, HttpMethod.GET, getGoParkedModeConfigObserver(activity), 
				RESTConstants.GET_PM_VEHICLE_CONF, 
				new RestParams(
						RESTConstants.P_VEHICLE, parkedModeStatus.getVehicleID())
						.put(RESTConstants.P_DOMAIN, parkedModeStatus.getDomain()),null, 
				ParkedModeConfiguration.class).execute((Void) null);
	}
	
	public static void goParkedModeHistoryActivity(LoJackActivity activity, ParkedModeStatus parkedModeStatus) {
		new RESTClientTaskOpt<ParkedModeHistoryLogBeanCollection>(activity, HttpMethod.GET, getParkedModeHistoryObserver(activity), 
				RESTConstants.GET_PM_VEHICLE_LOG, 
				new RestParams(
						RESTConstants.P_VEHICLE, parkedModeStatus.getVehicleID()).
						put(RESTConstants.P_PM_RECORDS, "15"),
				null, 
				ParkedModeHistoryLogBeanCollection.class).execute((Void) null);
	}
	
	public static IRestClientObserver getGoParkedModeConfigObserver(LoJackActivity activity) {
		return new ParkedModeRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				ParkedModeConfiguration pos = ((RESTClientTaskOpt<ParkedModeConfiguration>)restClientTask).getCastedResult();
				Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeVehicleDetail.class);
				intent.putExtra(ActivityParkedModeVehicleDetail.CONFIG, pos);
				activity.startActivity(intent);
				activity.finish();
			}
		};
	}

	public static IRestClientObserver getParkedModeObserver(LoJackActivity activity) {
		return new ParkedModeRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				ParkedModeStatusCollection pos = ((RESTClientTaskOpt<ParkedModeStatusCollection>)restClientTask).getCastedResult();
				Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeVehicles.class);
				intent.putExtra(ActivityParkedModeVehicles.VEHICLES_LIST, pos);
				activity.startActivity(intent);
			}
		};
	}
	
	public static IRestClientObserver getParkedModeHistoryObserver(LoJackActivity activity) {
		return new ParkedModeRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				ParkedModeHistoryLogBeanCollection pos = ((RESTClientTaskOpt<ParkedModeHistoryLogBeanCollection>)restClientTask).getCastedResult();
				Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeVehicleHistory.class);
				intent.putExtra(ActivityParkedModeVehicleHistory.HISTORY_LIST, pos);
				activity.startActivity(intent);
			}
		};
	}
}

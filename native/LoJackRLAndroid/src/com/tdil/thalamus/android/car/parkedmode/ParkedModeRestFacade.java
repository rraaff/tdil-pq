package com.tdil.thalamus.android.car.parkedmode;

import android.content.Intent;

import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatusCollection;

public class ParkedModeRestFacade {
	
	public static void startParkedModeStatusActivity(LoJackActivity activity) {
		new RESTClientTaskOpt<ParkedModeStatusCollection>(activity, HttpMethod.GET, getParkedModeObserver(activity), RESTConstants.GET_PM_VEHICLES, null,null, 
				ParkedModeStatusCollection.class).execute((Void) null);
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
}

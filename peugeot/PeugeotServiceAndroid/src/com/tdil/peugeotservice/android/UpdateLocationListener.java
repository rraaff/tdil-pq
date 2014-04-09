package com.tdil.peugeotservice.android;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.tdil.peugeotservice.android.rest.model.AlertResponseBean;

public class UpdateLocationListener implements LocationListener {
	private final AlertResponseBean resp;
	private final LocationManager lm;
	private final Activity activity;
	private boolean alreadySent = false;
	

	public UpdateLocationListener(AlertResponseBean resp,
			LocationManager lm, Activity activity) {
		this.resp = resp;
		this.lm = lm;
		this.activity = activity;
	}

	public void onLocationChanged(Location location) {
		if (!alreadySent) {
			alreadySent = true;
			double updatedLongitude = location.getLongitude();
			double updatedLatitude = location.getLatitude();
			destroy();
			ServicesDashboardActivity.updateAlertLocation(activity, resp, updatedLatitude, updatedLongitude, false);
		}
	}

	public void destroy() {
		lm.removeUpdates(this);
	}

	public void onProviderDisabled(String provider) {}

	public void onProviderEnabled(String provider) {}

	public void onStatusChanged(String provider, int status,
	        Bundle extras) {}
}
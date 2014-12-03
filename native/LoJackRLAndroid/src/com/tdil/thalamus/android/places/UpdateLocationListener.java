package com.tdil.thalamus.android.places;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class UpdateLocationListener implements LocationListener {
	private final LocationManager lm;
	private final ActivityPlaces activity;
	private boolean alreadySent = false;
	

	public UpdateLocationListener(LocationManager lm, ActivityPlaces activity) {
		this.lm = lm;
		this.activity = activity;
	}

	public void onLocationChanged(Location location) {
		if (!alreadySent) {
			alreadySent = true;
			double updatedLongitude = location.getLongitude();
			double updatedLatitude = location.getLatitude();
			destroy();
			activity.centerAtPosition(updatedLatitude, updatedLongitude);
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